package com.sparta.currency.dto.cancel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CancelExchangeServiceDto {
    private final Long exchangeRecordId;
    private final Long userId;
}
