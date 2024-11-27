package com.sparta.user.entity;

import com.sparta.common.entity.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "currency")
public class Currency extends TimeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String currencyName;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column(nullable = false)
    private BigDecimal exchangeRate;

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public Currency() {}
}
