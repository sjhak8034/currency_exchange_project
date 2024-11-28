package com.sparta.currency.dto.exchange;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {
    private final BigDecimal beforeAmount;

    private ExchangeRequestDto(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    private ExchangeRequestDto() {
        this.beforeAmount = BigDecimal.ZERO;
    }
}
