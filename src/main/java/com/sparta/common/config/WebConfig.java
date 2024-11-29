package com.sparta.common.config;


import com.sparta.common.filter.AdminFilter;
import com.sparta.common.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration을 사용해 필터를 전체 URL에 적용하고 필터의 순서를 설정
@Configuration //이 어노테이션을 주석 처리해놓으면 로그인 필터 수행안됨. 프로젝트 진행의 편의를 위해 일단은 이렇게 두고 진행
public class WebConfig {

    private final AdminFilter adminFilter;
    @Autowired
    public WebConfig(AdminFilter adminFilter) {
        this.adminFilter = adminFilter;
    }

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter()); // Filter 등록
        filterRegistrationBean.setOrder(1); // Filter 순서 1 설정
        filterRegistrationBean.setFilter(adminFilter);
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*"); // 전체 URL에 Filter 적용

        return filterRegistrationBean;
    }


}
