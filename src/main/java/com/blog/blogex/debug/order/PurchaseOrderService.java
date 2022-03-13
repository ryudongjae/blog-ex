package com.blog.blogex.debug.order;


import com.blog.blogex.debug.product.Product;
import com.blog.blogex.debug.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;

    public Long order(Long buyerId , List<Long>productIds){
        PurchaseOrder order = purchaseOrderRepository.save(PurchaseOrder.createOrder(buyerId));

        for (Long productId : productIds) {
            Product product = productRepository.findById(productId).orElseThrow();
            order.addProduct(product);
        }
        return order.getId();
    }

}
