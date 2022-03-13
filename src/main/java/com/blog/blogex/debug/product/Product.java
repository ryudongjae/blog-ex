package com.blog.blogex.debug.product;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id@GeneratedValue
    private Long id;

    @Column
    private long amount;

    @Column
    private String name;

    @Builder
    public Product(long amount, String name) {
        this.amount = amount;
        this.name = name;
    }
}
