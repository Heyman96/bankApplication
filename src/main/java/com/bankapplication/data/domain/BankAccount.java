package com.bankapplication.data.domain;

import com.bankapplication.data.domain.enums.Currency;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class BankAccount {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal moneyAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // В таблицах в БД будут отображаться строчные значения элементов ENUM`а, а не их целочисленные индексы
    private Currency currency; // - Валюта

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn
    private Client client;

}
