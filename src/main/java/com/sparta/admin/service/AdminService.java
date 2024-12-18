package com.sparta.admin.service;

import com.sparta.admin.dto.hard_delete_user.HardDeleteUserServiceDto;
import com.sparta.admin.dto.save_currency.SaveCurrencyServiceDto;
import com.sparta.currency.entity.Currency;
import com.sparta.currency.repository.CurrencyRepository;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    /**
     * 유저를 hard delete 하는 기능의 서비스 메소드
     * @param dto
     */
    public void hardDeleteUser(HardDeleteUserServiceDto dto) {
        User user = userRepository.findByIdOrElseThrow(dto.getUserId());
        userRepository.delete(user);
    }

    /**
     * 화폐를 저장하는 기능의 서비스 메소드
     * @param dto
     */
    public void saveCurrency(SaveCurrencyServiceDto dto) {
        Currency currency = new Currency(dto.getCurrencyName(), dto.getExchangeRate().setScale(2, RoundingMode.HALF_UP),dto.getCurrencySymbol());
        currencyRepository.save(currency);
    }
}
