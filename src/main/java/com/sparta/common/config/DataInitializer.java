package com.sparta.common.config;

import com.sparta.currency.dto.exchange.ExchangeServiceDto;
import com.sparta.currency.entity.Currency;
import com.sparta.currency.repository.CurrencyRepository;
import com.sparta.currency.service.CurrencyService;
import com.sparta.record.entity.ExchangeRecord;
import com.sparta.record.repository.ExchangeRecordRepository;
import com.sparta.user.dto.signup.SignUpServiceDto;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import com.sparta.user.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrencyService currencyService;
    private final UserService userService;
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
        admin.admin();
        userRepository.save(admin);
//        for(int i = 0; i < 100; i++ ){
//            userService.save(new SignUpServiceDto("user"+i+"@user.com","User@!12345"+i,"user"+i));
//        }
//        for(User user : userRepository.findAll()){
//            for(int i = 0; i < 1000; i++ ){
//                currencyService.exchange(new ExchangeServiceDto(1L,BigDecimal.valueOf(312*i),user.getId()));
//                currencyService.exchange(new ExchangeServiceDto(2L,BigDecimal.valueOf(312*i),user.getId()));
//                currencyService.exchange(new ExchangeServiceDto(3L,BigDecimal.valueOf(312*i),user.getId()));
//                currencyService.exchange(new ExchangeServiceDto(4L,BigDecimal.valueOf(312*i),user.getId()));
//            }
//        }

    }


}
