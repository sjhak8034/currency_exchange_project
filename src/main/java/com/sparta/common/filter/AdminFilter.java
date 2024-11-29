package com.sparta.common.filter;

import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
@Slf4j
@Component
public class AdminFilter implements Filter {

    private final UserRepository userRepository;
    @Autowired
    public AdminFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 인증을 하지 않아도될 URL Path 배열
    // 회원가입, 로그인, 로그아웃, 로그인 안한 상태의 게시물 조회는 인증을 할 필요가 없다.
    private static final String[] BLACKLIST = {"/currency/api/admin/*"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        log.info("admin filter 실행");


        if (isBlackList(requestURI)) {


            HttpSession session = httpRequest.getSession(false);
            User user = userRepository.findByIdOrElseThrow((long)session.getAttribute("userId"));
            if (user.getRole() == User.Role.ROLE_ADMIN){
                chain.doFilter(request, response);
            }
            else {
                return;
            }
        }


        chain.doFilter(request, response);
    }


    private boolean isBlackList(String requestURI) {

        return PatternMatchUtils.simpleMatch(BLACKLIST, requestURI);
    }
}