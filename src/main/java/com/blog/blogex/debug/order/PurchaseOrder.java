package com.blog.blogex.debug.order;


import com.blog.blogex.debug.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class PurchaseOrder {

    @Id@GeneratedValue
    private Long id;

    @Column
    private Long buyerId;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    @Column
    private Status status;

    public void cancelOrder(){
        status = Status.CANCEL;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public static PurchaseOrder createOrder(Long buyerId){
        return PurchaseOrder.builder()
                .buyerId(buyerId)
                .status(Status.ORDER_COMPLETE)
                .build();
    }
    @Builder
    private PurchaseOrder(Long buyerId, Status status) {
        this.buyerId = buyerId;
        this.status = status;
    }

    private enum Status {
        ORDER_COMPLETE("주문완료"),
        SHIPPING("배송중"),
        DELIVERY_COMPLETED("배송완료"),
        CANCEL("취소");

        private String viewName;

        Status(String viewName) {
            this.viewName = viewName;
        }

        public String getViewName() {
            return viewName;
        }
    }
}
