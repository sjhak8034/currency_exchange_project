package com.sparta.exchange.entity;

import com.sparta.common.entity.TimeBase;
import com.sparta.record.entity.ExchangeRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
