## MYSQL_Partition

    파티션은 크기가 큰 테이블을 여러 개로 분할하는 기능이다.  크기가 큰 테이블에 쿼리를 수행할 때, 인덱스를 사용하더라도 테이블의 크기가 매우 크다면 MYSQL에 부하가 걸릴것이다. 이런 문제를 줄이고 성능  향상을 위해. 사용하는 기능이다.  

### Partition을 사용하는 이유  

인덱스는 SELECT 성능을 위해 사용하긴 하지만 오로지 SELECT를 위해 사용하는 것은 아니다. UPDATE,DELETE,INSERT를 위해서도 사용된다.데이터를 변경하는 쿼리를 실행하면 인덱스의 변경을 위한 추가적인 작업이 발생하지만 UPDATE 나 DELETE 를 위해서는 해당 데이터를 검색하려면 인덱스가 필수적이다.  
그러나 인덱스가 커지게 된다면 SELECT의 성능 저하는 당연한것이고 INSERT나 UPDATE,DELETE 작업도 마찬가지로 느려진다.  
테이블에서 데이터를 파티셔닝 할 수 있다면 효과적으로 데이터 처리가 가능하고 성능을 개선할 수 있다.  

크기가 큰 테이블을 파티셔닝하지 않고 사용하는 것과 작은 파티션으로 나누어 크기를 줄였을 때 인덱스가 어떤식으로 사용되는지 아래 그림을 보자.  

http://blog.skby.net/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-%ED%85%8C%EC%9D%B4%EB%B8%94-%ED%8C%8C%ED%8B%B0%EC%85%94%EB%8B%9D-table-partitioning/ 

왼쪽 그림처럼  파티셔닝을 하지 않고 그냥 사용하면 인덱스도 매우 커지고 물리적인 메모리 공간도 많이 필요해서 효율적이지 못하다.  
그러나 오른쪽처럼 작은 파티션으로 나누어 사용한다면 물리적 메모리를 효율적으로 사용할 수 있고 성능적인 측면에서도 파티셔닝 하지 않은 것보다 효율적으로 사용할 수 있다.  


### MYSQL Partition 내부 동작. 

REATE TABLE rt_blog ( 

id INT NOT NULL,  
writeDate DATETIME NOT NULL,  
...  
PRIMARY KEY(id) 
  
)  

PARTITION BY RANGE ( YEAR(writeDate) ) ( 

PARTITION p2020 VALUES LESS THAN (2021),  
PARTITION p2021 VALUES LESS THAN (2022),  
PARTITION p2022 VALUES LESS THAN MAXVALUE 

);  
동작 방식을 확인하기 위해 임시로 테이블을 생성했다.  

이 테이블에서 writeDate에서 연도는 파티션을 하기위한 파티션 키로서 해당 레코드가 어느 파티션에 저장될지를 결정하는 역할을 한다.  

[PartitionTable INSERT]  
INSERT 쿼리가 실행되는 MYSQL서버는 INSERT되는 컬럼 값 중에서 파티션 키인 writeDate컬럼 값을 이용해 파티션 표현식을 평가하고, 그 결과에 따라 저장될 파티션을 결정한다.    

INSERT되는 데이터가 파티션이 결정되면 그 후에는 일반적인 과정과 동일하게 진행된다.    

[PartitionTable UPDATE]  
UPDATE 쿼리를 실행하려면 변경 대상 데이터가 어느 파티션에 저장되어있는지 찾아야 한다. 이때 UPDATE쿼리의 WHERE조건에 파티션  키 컬럼이 조건으로 존재하면 그 값을 통해  데이터가 저장된 파티션에서 빠르게 해당 데이터를 검색할 수 있다.    

만약 MYSQL에서 파티션 키 이외의 컬럼만 변경될 떄는 파티션이 적용되지 않은 일반 테이블과 마찬가지고 컬럼 값만 변경한다. 그러나 파티션 키 컬럼이 변경될 때는 해당 파티션에서 데이터를 삭제한 후 해당 데이터를 새로운 파티션으로 복사한다. 그 다음 파티션 키 컬럼을 변경 시켜준다.    

[PartitionTable SELECT]  
가장 중요한 SELECT 쿼리 성능이다. 파티션 테이블을 검색할 때 아래 조건에 따라 성능에 영향을 크게 준다.    

#### SQL이 수행되기 위해 파티션 테이블을 검색할 때 성능에 크게 영향을 미치는 조건
* WHERE 절의 조건으로 검색해야 할 파티션을 선택 가능한가
* WHERE 절의 조건이 인덱스를 효율적으로 사용할 수 있는가

첫번째 조건의 결과에 의해 두번째 선택사항의 작업이 달라질 수도 있다.
그리고 두 조건이 모두 성립할 때 성능적으로 가장 효율적으로 처리될 수 있다. 

두 조건이 성립되면 파티션의 개수와 상관없이 검색을 위해 필요한 파티션의 인덱스만 스캔한다.


### MYSQL_Partition 형식

1 . Range
범위를 기반으로 파티션을 나누는 형식이다.  
Range Partition은 날씨 기반 데이터가 누적되고 날짜에 따라 분석 삭제할 경우, 범위 기반으로 데이터를 여러 파티션에 균등하게 나눌 수 있는 경우, 대량의 과거 데이터 삭제 같은 경우에 사용한다.  
Range Partition에서 null은 어떤 값 보다 작은 값으로 취급되기 때문에 컬럼에 null인 데이터가 insert 된다면 가장 작은 값을 저장하는 파티션에 저장된다.  

아래는 연도를 범위를 기준으로 파티션을 생성한 것이다.    
CREATE TABLE test (
    id INT NOT NULL,  
    lname VARCHAR(30),  
    datt DATE NOT NULL DEFAULT '2000-01-01',  
    separated DATE NOT NULL DEFAULT '9999-12-31',  
    store_id INT NOT NULL  
) PARTITION BY RANGE (YEAR(datt)) (  
    PARTITION p0 VALUES LESS THAN (2020) ,  
    PARTITION p1 VALUES LESS THAN (2021) ,  
    PARTITION p2 VALUES LESS THAN (2022) ,  
    PARTITION p3 VALUES LESS THAN MAXVALUE  
    );  

Partition 추가    
ALTER TABLE test ADD PARTITION(PARTITION p4 VALUES LESS THAN (2023));  
  
Partition 삭제 
ALTER TABLE test DROP PARTITION p4;

Partition 병합  
ALTER TABLE test    
REORGANIZE PARTITION p2,p3 INTO ( 
PARTITION p23 VALUES LESS THAN (2020)  
);  
그리고 날짜 컬럼에 대한 Range 파티션 적용시 YEAR(),TO_DAYS()함수만 사용하길 권장한다. 두 함수는 MYSQL 서버 내부적으로 파티션 프루닝 처리가 되어 성능상의 문제가 발생하지 않지만 그 외의 함수는 파티션 프루닝이 제대로 작동하지 않을 수도 있기 때문에 지양하길 권장한다.  
2 . List  

List Partition은 RangePartition과 비슷하고,코드나 카테고리 등 특정 값을 기반으로 파티션을 나눈다.  
List Partition은 파티션 키 값이 코드 값이나 카테고리와 같이 고정 값일 경우에 사용하고 파티션 키 값을 기준으로 레코드 건수가 균일하고 검색 조건에 파티션 키가 자주 사용되는 경우에 사용한다.  
List는 Range와 다르게 null을 명시할 수 있지만, MAXVALUE는 지정할 수 없다.  

CREATE TABLE test (  
    id INT NOT NULL,  
    name VARCHAR(30),  
    datt DATE NOT NULL DEFAULT '2000-01-01',  
    separated DATE NOT NULL DEFAULT '9999-12-31',  
    job_code INT NOT NULL,  
    store_id INT NOT NULL 
) PARTITION BY LIST (job_code) (  
    PARTITION p0 VALUES IN (2) ,  
    PARTITION p1 VALUES IN (1,9) ,  
    PARTITION p2 VALUES IN (3,6,7) ,  
    PARTITION p3 VALUES IN (4,5,8, NULL)  
    );  
    
Partition 추가.    
ALTER TABLE test ADD PARTITION(PARTITION p4 VALUES IN (3,10));   
 
Partition 삭제  
ALTER TABLE test DROP PARTITION p4;  

Partition 병합     
ALTER TABLE test   
REORGANIZE PARTITION p2,p3 INTO (     
PARTITION p23  VALUES IN (3,6,7,4,5,8,NULL)    
);


3 . Hash  
Hash Partition은 Hash 함수에 의해 레코드가 저장될 파티션을 결정하는 방식이다.  
Hash Partition은 테이블의 모든 레코드가 비슷한 사용빈도를 보이지만 너무 커서 파티션이 필요한 경우 사용된다.  
CREATE TABLE test (  
    id INT NOT NULL,  
    name VARCHAR(30),  
    datt DATE NOT NULL DEFAULT '2000-01-01',  
    separated DATE NOT NULL DEFAULT '9999-12-31',  
    job_code INT NOT NULL,  
    store_id INT NOT NULL  
) PARTITION BY HASH (id)  
PARTITIONS 4;  

Partition 추가  
파티션의 갯수로 MOD 연산한 결과에 따라 각 레코드를 저장할 파티션을 결정하므로 새로이 파티션이 추가될 경우 파티션에 저장된 모든 레코드는 재배치 되어야 하므로 많은 부하가 발생한다.  
//파티션 이름을 부여 
ALTER TABLE test ADD PARTITION(PARTITION p5 ENGINE = INNODB);  

Partition 삭제 

파티션 키 값을 이용하여 데이터를 각 파티션으로 분산한 것이므로 각 파티션에 저장된 레코드의 부류를 사용자가 예측할 수 없기에 해시나 키를 이용한 파티션에서는 파티션 단위의 삭제는 불가능하다.

Partition 분할 ,Partition 병합

분할은 불가능 하고 추가만 가능하다. 병합 기능도 제공하지 않지만 갯수를 줄이는 것은 가능하다.

4 . Key

MD5() 함수를 이용한 Hash 값을 기반으로 파티션을 나누고 Hash Partition과 거의 동일하다.
Key Partition은 선정된 파티션 키값에 대하여 내부적으로 MD5()를 이용하여 해시값을 계산하고, 그 값에 MOD를 적용하여 저장할 파티션을 결정한다.

