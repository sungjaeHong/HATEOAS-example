package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by sungjae.hong on 2017. 8. 24..
 */
@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    @GeneratedValue
    private int accountNo;

    private String accountType;

    private int relationKey;

    private Long totalAmount;

    private Long pointAmount;

    private String creator;

    private Date createdAt;

    private String updater;

    private Date updatedAt;

}
