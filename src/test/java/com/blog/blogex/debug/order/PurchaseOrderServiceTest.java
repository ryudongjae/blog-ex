package com.blog.blogex.debug.order;

import com.blog.blogex.debug.product.Product;
import com.blog.blogex.debug.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseOrderServiceTest {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("주문")
    void order()throws Exception{
        //given
        Long buyerId = 10L;
        Long productId1 = productRepository.save(Product.builder()
                        .amount(1000L)
                        .name("K2")
                        .build())
                        .getId();
        Long productId2 = productRepository.save(Product.builder()
                        .amount(1001L)
                        .name("K2")
                        .build())
                .getId();



        //then
        Long order = purchaseOrderService.order(buyerId, Arrays.asList(productId1, productId2));

        org.assertj.core.api.Assertions.assertThat(order).isEqualTo(10L);

    }
}