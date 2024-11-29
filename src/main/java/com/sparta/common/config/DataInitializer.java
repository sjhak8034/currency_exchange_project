package com.sparta.common.config;

import com.sparta.currency.entity.Currency;
import com.sparta.currency.repository.CurrencyRepository;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<HttpSession> sessions;

    /**
     * 어플리케이션이 실행할때 동작하는 메소드
     */
    @PostConstruct
    public void init(){
        // 화폐를 등록
        Currency currencyUSD = new Currency("USD", BigDecimal.valueOf(1390.5), "$");
        Currency currencyJPY = new Currency("JPY", BigDecimal.valueOf(9.2), "JP¥");
        Currency currencyEUR = new Currency("EUR", BigDecimal.valueOf(1472.11), "€");
        Currency currencyCNY = new Currency("CNY", BigDecimal.valueOf(192.5), "CN¥");

        currencyRepository.save(currencyUSD);
        currencyRepository.save(currencyJPY);
        currencyRepository.save(currencyEUR);
        currencyRepository.save(currencyCNY);

        // 관리자 등록
        User admin = new User("admin","admin@admin.com",passwordEncoder.encode("Admin@!"));
        userRepository.save(admin);
    }


}
