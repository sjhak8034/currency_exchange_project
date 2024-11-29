package com.sparta.admin.controller;

import com.sparta.admin.dto.hard_delete_user.HardDeleteUserRequestDto;
import com.sparta.admin.dto.hard_delete_user.HardDeleteUserServiceDto;
import com.sparta.admin.dto.save_currency.SaveCurrencyRequestDto;
import com.sparta.admin.dto.save_currency.SaveCurrencyServiceDto;
import com.sparta.admin.service.AdminService;
import com.sparta.currency.entity.Currency;
import com.sparta.currency.service.CurrencyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    /**
     * 화폐를 추가하는 기능의 엔드포인트
     * @param body 화폐 정보
     * @param request session 정보를 가져옴
     * @return
     */
    @PostMapping("/currencies")
    public ResponseEntity<Void> saveCurrency(@RequestBody @Valid SaveCurrencyRequestDto body, HttpServletRequest request) {
       adminService.saveCurrency(new SaveCurrencyServiceDto(body.getCurrencyName(),body.getCurrencySymbol(),body.getExchangeRate()));
       return ResponseEntity.ok().build();
    }

    /**
     * 유저를 hard delete 하는 기능의 엔드포인트
     * @param body 삭제하기 위한 유저의 식별자
     * @param request session 정보를 가져옴
     * @return
     */
    @DeleteMapping("/users")
    public ResponseEntity<Void> hardDeleteUser(@RequestBody @Valid HardDeleteUserRequestDto body, HttpServletRequest request) {
        adminService.hardDeleteUser(new HardDeleteUserServiceDto(body.getUserId()));
        return ResponseEntity.ok().build();
    }
}
