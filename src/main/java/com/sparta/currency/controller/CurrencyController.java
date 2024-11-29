package com.sparta.currency.controller;

import com.sparta.currency.dto.cancel.CancelExchangeResponseDto;
import com.sparta.currency.dto.cancel.CancelExchangeServiceDto;
import com.sparta.currency.dto.exchange.ExchangeRequestDto;
import com.sparta.currency.dto.exchange.ExchangeResponseDto;
import com.sparta.currency.dto.exchange.ExchangeServiceDto;
import com.sparta.currency.dto.findall.FindAllCurrencyResponseDto;
import com.sparta.currency.service.CurrencyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency/api/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    /**
     * 환전 기능의 엔드포인트
     * @param currencyId  환전할 화폐 식별자
     * @param body 유저가 환전을 하기위해 담은 정보
     * @param request 유저의 로그인 정보 이를 통해 식별자를 가져온다
     * @return
     */
    @PostMapping("{currencyId}/exchange")
    public ResponseEntity<ExchangeResponseDto> exchange (@PathVariable("currencyId") Long currencyId
            , @RequestBody @Valid ExchangeRequestDto body, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ExchangeResponseDto responseDto = currencyService.exchange(new ExchangeServiceDto(currencyId,body.getBeforeAmount()
                ,(long)session.getAttribute("userId")));
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 환전 취소 기능의 엔드포인트
     * @param currencyId 화폐 식별자
     * @param request 유저 로그인 정보
     * @return
     */
    @PutMapping("{currencyId}/exchange")
    public ResponseEntity<CancelExchangeResponseDto> cancelExchange (@PathVariable("currencyId") Long currencyId
            , HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        CancelExchangeServiceDto serviceDto = new CancelExchangeServiceDto(currencyId, (Long)session.getAttribute("userId"));
        return ResponseEntity.ok(currencyService.cancel(serviceDto));
    }

    /**
     * 모든 환율 정보를 조회하는 메소드
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<FindAllCurrencyResponseDto>> findAllCurrencies () {
        return ResponseEntity.ok(currencyService.findAllCurrency());
    }


}
