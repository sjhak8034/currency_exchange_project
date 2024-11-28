package com.sparta.admin.dto.save_currency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class SaveCurrencyRequestDto {
    private final String currencyName;
    private final String currencySymbol;

    @DecimalMin(value = "0.0", inclusive = false)
    private final BigDecimal exchangeRate;
    private SaveCurrencyRequestDto(String currencyName, String currencySymbol, BigDecimal exchangeRate) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.exchangeRate = exchangeRate;
    }
}
