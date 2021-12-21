package com.blog.blogex.dirtychecking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EntityManagerFactory entityManagerFactory;

    public void updateNative(Long id, String trade){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction ef = em.getTransaction();
        ef.begin();
        Payment payment = em.find(Payment.class, id);
        payment.changeTradeNumber(trade);
        ef.commit();
    }

    @Transactional
    public void update(Long id,String tradeNumber){
        Payment payment = paymentRepository.getOne(id);
        payment.changeTradeNumber(tradeNumber);
    }
}
