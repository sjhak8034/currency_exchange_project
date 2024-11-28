package com.sparta.currency.repository;

import com.sparta.currency.dto.findall.FindAllCurrencyResponseDto;
import com.sparta.currency.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    default Currency findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Currency not found")
        );
    }

    @Query(value = "select new com.sparta.currency.dto.findall.FindAllCurrencyResponseDto(c.id,c.currencyName,c.symbol,c.exchangeRate) from Currency c")
    List<FindAllCurrencyResponseDto> findAllCurrencyData();
}
