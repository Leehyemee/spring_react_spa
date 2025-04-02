package com.example.svsvdvdv.semiprojectv2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/myinfo")
    public ResponseEntity<?> myinfo(Authentication authentication) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        // 로그인 인증이 성공했다면
        if (authentication!= null && authentication.isAuthenticated()) {
            //인증 완료된 사용자 정보(아이디)를 가져옴
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Map<String, String> loginUser = Map.of(
                    "loginUser", "abc123"
            );

            response = ResponseEntity.ok().body(loginUser);
        }else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패!! - 로그인하세요!!");
        }

        return response;
    }
}
