package com.sparta.currency.dto.findall;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class FindAllCurrencyResponseDto {
    private final Long currencyId;
    private final String currencyName;
    private final String currencySymbol;
    private final BigDecimal exchangeRate;
}
