package com.bankapplication.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal moneyAmount = new BigDecimal("10000");

    @Column(nullable = false)
    private String currency; // - Валюта

}
