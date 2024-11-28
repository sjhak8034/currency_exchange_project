package com.sparta.currency.dto.exchange;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ExchangeServiceDto {
    private final Long currencyId;
    private final BigDecimal beforeAmount;
    private final Long userId;
}
