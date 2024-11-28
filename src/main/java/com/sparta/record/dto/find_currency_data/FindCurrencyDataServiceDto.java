package com.sparta.record.dto.find_currency_data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindCurrencyDataServiceDto {
    private final int page;
    private final int size;
}
