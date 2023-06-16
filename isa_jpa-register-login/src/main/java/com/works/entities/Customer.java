package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;


@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String surname;

    @Column(unique = true)
    private String email;

    private String password;
    @Positive
    private Integer age;
    private Boolean status;


}
