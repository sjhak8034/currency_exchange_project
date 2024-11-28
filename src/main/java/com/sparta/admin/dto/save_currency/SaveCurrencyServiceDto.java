package com.sparta.admin.dto.save_currency;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Getter
@RequiredArgsConstructor
public class SaveCurrencyServiceDto {
    private final String currencyName;
    private final String currencySymbol;
    private final BigDecimal exchangeRate;
}
