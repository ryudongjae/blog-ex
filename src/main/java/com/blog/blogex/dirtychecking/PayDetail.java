package com.blog.blogex.dirtychecking;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PayDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private long amount;

    @ManyToOne
    @JoinColumn(name = "pay_id")
    private Payment pay;

    public PayDetail(String type, long amount) {
        this.type = type;
        this.amount = amount;
    }

    public void setPay(Payment pay) {
        this.pay = pay;
    }

}
