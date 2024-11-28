package com.sparta.record.service;

import com.sparta.currency.repository.CurrencyRepository;
import com.sparta.record.dto.find_currency_data.CurrencyVolumeData;
import com.sparta.record.dto.find_amount.FindAmountResponseDto;
import com.sparta.record.dto.find_amount.FindAmountServiceDto;
import com.sparta.record.dto.find_currency_data.FindCurrencyDataResponseDto;
import com.sparta.record.dto.find_currency_data.FindCurrencyDataServiceDto;
import com.sparta.record.repository.ExchangeRecordRepository;
import com.sparta.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRecordService {
    private final ExchangeRecordRepository exchangeRecordRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public FindAmountResponseDto findAmount(FindAmountServiceDto dto) {
        List<Object[]> countAndAmount = exchangeRecordRepository.findAmountByUserId(dto.getUserId());
        if (countAndAmount == null || countAndAmount.size() == 0) {
            return new FindAmountResponseDto(0L, BigDecimal.valueOf(0));
        }
        return new FindAmountResponseDto((Long) countAndAmount.get(0)[0], (BigDecimal) countAndAmount.get(0)[1]);
    }

    public FindCurrencyDataResponseDto findCurrencyData(FindCurrencyDataServiceDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), Sort.Direction.DESC, "r.currency.id");
        Page<CurrencyVolumeData> data = exchangeRecordRepository.findAmountByCurrency(pageable);

        //페이지 정보 객체를 생성
        FindCurrencyDataResponseDto.PageInfo pageInfo = new FindCurrencyDataResponseDto.PageInfo(
                data.getNumber() + 1,
                data.getSize(),
                (int) data.getTotalElements(),
                data.getTotalPages()
        );
        return new FindCurrencyDataResponseDto(data.getContent(), pageInfo);
    }
}



