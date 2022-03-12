우선 ON은 조인 조건을 정의하는 데 사용되어야 하고 WHERE은 데이터를 필터링하는데 사용하여야 한다. ON과 WHERE처럼 목적에 따른 절로 분할하면 쿼리를 가독성 높게 작성할 수 있고 INNER JOIN 이외의 JOIN 유형을 사용할 때 잘못된 데이터가 결과값으로 나오는 것도 방지할 수 있다.

ON과 WHERE은 데이터 결합과 데이터 필터링에 사용된다.

## 데이터 결합

데이터의 결합을 위해 ON과 WHERE 두 절을 모두 사용하는 방법은 두 테이블이 조인되는 조건을 정의하는 것이다.

아래 예제를 보자

School_Student Table

| NAME | AGE |
| --- | --- |
| KIM | 22 |
| KANG | 23 |

Academy_Student Table

| NAME | AGE |
| --- | --- |
| KANG | 23 |
| HA | 24 |

위 두 테이블에서 학교와 학원을 모두 다니는 학생을 가져오고 싶다 가정을 해보자. 데이터가 2개씩뿐이기 때문에 답이 바로 보이지만 데이터가 많아지면 JOIN으로 해결을 해야한다.

이제 쿼리를 작성해보자

```sql
SQL1)

SELECT * 
FROM School_Student
JOIN Academy_Student
ON School_Student.NAME= Academy_Student.NAME;

SQL2)
SELECT * 
FROM School_Student
JOIN Academy_Student
WHERE School_Student.NAME= Academy_Student.NAME;

SQL3)
SELECT * 
FROM SCHOOL_Student,ACADEMY_Student
WHERE School_Student.NAME= Academy_Student.NAME;

```

| School_Student.NAME | School_Student.AGE | Academy_Student.NAME | Academy_Student.AGE |
| --- | --- | --- | --- |
| KANG | 24 | KANG | 24 |

SQL1과 SQL2는 명시적 JOIN 유형이고 SQL3은 암시적인 JOIN 유형이다. 명시적 JOIN은 ON절에서 JOIN 유형과 JOIN조건을 지정하여 데이터를 조인하는 방법을 명시적으로 알려준다. 암시적인 JOIN은 JOIN 유형을 지정하지 않으며 WHERE절을 사용하여 JOIN조건을 정의한다.

## 가독성

위와 같은 쿼리문들은 쿼리문의 진행 상황을 얼마나 이해하기 쉬운가에서 차이가 생긴다.

SQL1에서는 FROM과  JOIN절에서 조인되는 테이블을 어렵지 않게 찾을 수 있다. ON절에서도 조인 조건을 명확하게 볼 수 있다.

SQL2는 명확해 보이지만 WHERE절은 일반 적으로 JOIN이 아니라 데이터를 필터링 하는 데 사용되기 때문에 이중으로 사용할 수 있다.

SQL3는 암시적 JOIN이다. 대부분의 경우 암시적 JOIN은 INNER JOIN으로 작동한다. 그러나 쿼리문 WHERE절에서 JOINING하는 것은 일반적인 목적이 아니기때문에 혼동될 수 있다.

WHERE절은 데이터를 필터링하는데 가장 자주 사용된다. 따라서 데이터 JOIN 방법을 정의하는 데 사용하는 것외에도 WHERE절에 더 많은 필터링 조건이 추가되면 가독성이 급격하게 떨어지게  된다.

```sql
SELECT *
FROM School_Student,Academy_Student
WHERE School_Student.NAME = Academy_Student.NAME AND ( School_Student.NAME = 'KANG' OR Academy_Student.AGE = 24)

SELECT *
FROM School_Student
JOIN Academy_Student
ON School_Student.NAME = Academy_Student.NAME
WHERE School_Student.NAME = 'KANG' OR Academy_Student.AGE = 24;
```

위 예제를 보면 첫번째 쿼리가 전체적인 쿼리 사이즈는 작지만 두번째 쿼리보다 가독성이 떨어지고 이해하기 쉽지 않다.


