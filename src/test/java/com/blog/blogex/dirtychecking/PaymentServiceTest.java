package com.blog.blogex.dirtychecking;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentService paymentService;

    @AfterEach
    public void deleteAll(){
        paymentRepository.deleteAll();
    }


    @Test
    @DisplayName("엔티티 메니저로 확인")
    void find_entityManager()throws Exception{
        //given
        Payment pay = paymentRepository.save(new Payment("test1",  100L));

        //when
        String updateTradeNo = "test2";
        paymentService.updateNative(pay.getId(), updateTradeNo);

        //then
        Payment saved = paymentRepository.findAll().get(0);
        Assertions.assertThat(saved.getTradeNumber()).isEqualTo(updateTradeNo);


    }

    @Test
    @DisplayName("SpringDataJpa로_확인")
    void find_jpa() {
        //given
        Payment pay = paymentRepository.save(new Payment("test1",  100L));

        //when
        String updateTradeNo = "test2";
        paymentService.update(pay.getId(), updateTradeNo);

        //then
        Payment saved = paymentRepository.findAll().get(0);
        Assertions.assertThat(saved.getTradeNumber()).isEqualTo(updateTradeNo);
    }
}