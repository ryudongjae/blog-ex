package com.blog.blogex.dirtychecking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tradeNumber;
    private Long amount;

    @OneToMany(mappedBy = "pay")
    private List<PayDetail> details = new ArrayList<>();

    public Payment(String tradeNumber, Long amount) {
        this.tradeNumber = tradeNumber;
        this.amount = amount;
    }

    public void addDetail(PayDetail detail){
        this.details.add(detail);
        detail.setPay(this);
    }

    public void changeTradeNumber(String tradeNumber){
        this.tradeNumber = tradeNumber;
    }
}
