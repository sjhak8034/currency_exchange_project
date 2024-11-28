package com.sparta.currency.controller;

import com.sparta.currency.dto.cancel.CancelExchangeResponseDto;
import com.sparta.currency.dto.cancel.CancelExchangeServiceDto;
import com.sparta.currency.dto.exchange.ExchangeRequestDto;
import com.sparta.currency.dto.exchange.ExchangeResponseDto;
import com.sparta.currency.dto.exchange.ExchangeServiceDto;
import com.sparta.currency.service.CurrencyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency/api/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @PostMapping("{currencyId}/exchange")
    public ResponseEntity<ExchangeResponseDto> exchange (@PathVariable("currencyId") Long currencyId
            , @RequestBody @Valid ExchangeRequestDto body, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ExchangeResponseDto responseDto = currencyService.exchange(new ExchangeServiceDto(currencyId,body.getBeforeAmount()
                ,(long)session.getAttribute("userId")));
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("{currencyId}/exchange")
    public ResponseEntity<CancelExchangeResponseDto> cancelExchange (@PathVariable("currencyId") Long currencyId
            , HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        CancelExchangeServiceDto serviceDto = new CancelExchangeServiceDto(currencyId, (Long)session.getAttribute("userId"));
        return ResponseEntity.ok(currencyService.cancel(serviceDto));
    }


}
