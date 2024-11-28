package com.sparta.record.dto.find_amount;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class FindAmountResponseDto {
    private final Long count;
    private final BigDecimal amount;
}
