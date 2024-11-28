package com.sparta.admin.dto.save_currency;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class SaveCurrencyRequestDto {
    private final String currencyName;
    private final String currencySymbol;
    @Size(min = 0)
    private final BigDecimal exchangeRate;
    private SaveCurrencyRequestDto(String currencyName, String currencySymbol, BigDecimal exchangeRate) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.exchangeRate = exchangeRate;
    }
}
