package com.sparta.common.config;

import com.sparta.currency.entity.Currency;
import com.sparta.currency.repository.CurrencyRepository;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){

        Currency currencyUSD = new Currency("USD", BigDecimal.valueOf(1390.5), "$");
        Currency currencyJPY = new Currency("JPY", BigDecimal.valueOf(9.2), "JP¥");
        Currency currencyEUR = new Currency("EUR", BigDecimal.valueOf(1472.11), "€");
        Currency currencyCNY = new Currency("CNY", BigDecimal.valueOf(192.5), "CN¥");

        currencyRepository.save(currencyUSD);
        currencyRepository.save(currencyJPY);
        currencyRepository.save(currencyEUR);
        currencyRepository.save(currencyCNY);

        User admin = new User("admin","admin@admin.com",passwordEncoder.encode("Admin@!"));
        userRepository.save(admin);
    }
}
