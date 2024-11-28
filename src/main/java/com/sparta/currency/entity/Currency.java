package com.sparta.currency.entity;

import com.sparta.common.entity.TimeBase;
import com.sparta.record.entity.ExchangeRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

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
    @Size(min = 0)
    private BigDecimal exchangeRate;

    @OneToMany(mappedBy = "currency")
    private List<ExchangeRecord> exchangeRecords;

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public Currency() {}
}
