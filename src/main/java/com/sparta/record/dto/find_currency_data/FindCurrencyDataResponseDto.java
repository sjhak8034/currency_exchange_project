package com.sparta.record.dto.find_currency_data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindCurrencyDataResponseDto {
    private final List<CurrencyVolumeData> data;
    private final PageInfo pageInfo;

    @Getter
    @AllArgsConstructor
    public static class PageInfo {
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
