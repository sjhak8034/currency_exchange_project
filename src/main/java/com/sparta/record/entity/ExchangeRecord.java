package com.sparta.record.entity;

import com.sparta.common.entity.TimeBase;
import com.sparta.exchange.entity.Currency;
import com.sparta.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "exchange_record")
public class ExchangeRecord extends TimeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false, updatable = false)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column
    private BigDecimal beforeAmount;

    @Column
    private BigDecimal afterAmount;

    @Column
    private Boolean isCancelled = Boolean.FALSE;

    public ExchangeRecord(Currency currency, User user, BigDecimal beforeAmount, BigDecimal afterAmount) {
        this.currency = currency;
        this.user = user;
        this.beforeAmount = beforeAmount;
        this.afterAmount = afterAmount;
    }

    public void cancel() {
        isCancelled = Boolean.TRUE;
    }

    public ExchangeRecord() {}

}
