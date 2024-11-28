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

    @PostMapping("/currencies")
    public ResponseEntity<Void> saveCurrency(@RequestBody @Valid SaveCurrencyRequestDto body, HttpServletRequest request) {
       adminService.saveCurrency(new SaveCurrencyServiceDto(body.getCurrencyName(),body.getCurrencySymbol(),body.getExchangeRate()));
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> hardDeleteUser(@RequestBody @Valid HardDeleteUserRequestDto body, HttpServletRequest request) {
        adminService.hardDeleteUser(new HardDeleteUserServiceDto(body.getUserId()));
        return ResponseEntity.ok().build();
    }
}
