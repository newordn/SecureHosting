package com.neword.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public  @Data class User {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String country;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles;
}
