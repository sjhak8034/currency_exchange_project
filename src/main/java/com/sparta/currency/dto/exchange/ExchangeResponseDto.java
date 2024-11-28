package com.sparta.currency.dto.exchange;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ExchangeResponseDto {
    private final Long exchangeRecordId;
    private final String symbol;
    private final BigDecimal afterAmount;
}
