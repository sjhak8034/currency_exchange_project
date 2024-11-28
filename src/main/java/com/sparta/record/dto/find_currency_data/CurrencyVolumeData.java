package com.sparta.record.dto.find_currency_data;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyVolumeData {
    private final String currencyName;
    private final Long count;
    private final BigDecimal volume;
    public CurrencyVolumeData(String currencyName, Long count, BigDecimal volume) {
        this.currencyName = currencyName;
        this.count = count;
        this.volume = volume;
    }
}
