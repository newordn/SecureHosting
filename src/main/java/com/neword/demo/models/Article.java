package com.neword.demo.models;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public  @Data class Article {
    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    private Date buyDate= new Date();
    private String validityPeriod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    public Article()
    {

    }
    public Article(String name, Double price, Date date, String buyDate) {
    }
}
