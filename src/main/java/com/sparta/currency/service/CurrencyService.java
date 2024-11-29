package com.sparta.currency.service;

import com.sparta.currency.dto.cancel.CancelExchangeResponseDto;
import com.sparta.currency.dto.cancel.CancelExchangeServiceDto;
import com.sparta.currency.dto.exchange.ExchangeResponseDto;
import com.sparta.currency.dto.exchange.ExchangeServiceDto;

import com.sparta.currency.dto.findall.FindAllCurrencyResponseDto;
import com.sparta.currency.entity.Currency;
import com.sparta.currency.repository.CurrencyRepository;
import com.sparta.record.entity.ExchangeRecord;
import com.sparta.record.repository.ExchangeRecordRepository;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final ExchangeRecordRepository exchangeRecordRepository;

    /**
     * 환전 기능의 서비스 메소드
     * @param dto
     * @return
     */
    public ExchangeResponseDto exchange(ExchangeServiceDto dto) {
        Currency currency = currencyRepository.findByIdOrElseThrow(dto.getCurrencyId());
        User user = userRepository.findByIdOrElseThrow(dto.getUserId());
        // 환전 로직
        BigDecimal afterAmount = dto.getBeforeAmount().divide(currency.getExchangeRate(),2,BigDecimal.ROUND_HALF_UP);
        ExchangeRecord exchangeRecord = new ExchangeRecord(currency,user,dto.getBeforeAmount(), afterAmount);
        exchangeRecordRepository.save(exchangeRecord);
        return new ExchangeResponseDto(exchangeRecord.getId(),currency.getSymbol(),exchangeRecord.getAfterAmount());
    }

    /**
     * 환전 취소의 서비스 메소드
     * @param dto
     * @return
     */
    @Transactional
    public CancelExchangeResponseDto cancel(CancelExchangeServiceDto dto) {
        User user = userRepository.findByIdOrElseThrow(dto.getUserId());
        ExchangeRecord exchangeRecord = exchangeRecordRepository.findByIdOrElseThrow(dto.getExchangeRecordId());
        // 유저 본인의 거래인지 확인
        if(!Objects.equals(exchangeRecord.getUser().getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "자신의 거래만 취소할 수 있습니다");
        }
        // 이미 취소된 거래인지 확인
        if(exchangeRecord.getIsCancelled()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 취소된 거래입니다");
        }
        exchangeRecord.cancel();
        exchangeRecordRepository.save(exchangeRecord);
        return new CancelExchangeResponseDto(exchangeRecord.getId());
    }

    /**
     * 환율 조회 기능을 하는 서비스 메소드
     * @return
     */
    public List<FindAllCurrencyResponseDto> findAllCurrency() {
        return currencyRepository.findAllCurrencyData();

    }

}
