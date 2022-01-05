package com.blog.blogex.EmbeddedType;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id@GeneratedValue
    private Long id;
    private String name;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                    column=@Column(name = "WORK_STREET"))
    })
    private Address workAddress;
    @Embedded
    private Period period;

    public User(String name, Address homeAddress, Address workAddress, Period period) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.period = period;
    }

    public User() {

    }
}
