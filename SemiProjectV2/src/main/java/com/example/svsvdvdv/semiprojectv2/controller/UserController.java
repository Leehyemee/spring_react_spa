package com.example.svsvdvdv.semiprojectv2.controller;

import com.example.svsvdvdv.semiprojectv2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:5173", "http://172.30.1.25:3000"})
@Slf4j
@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    // 나중에 포스트 형식으로 보안 강화해서 해보기
    @GetMapping("/verifyCode/{userid}/{email}/{code}")
    public ResponseEntity verifyCode(@PathVariable String userid,
                     @PathVariable String email, @PathVariable String code) {
        ResponseEntity<?> response = ResponseEntity.ok().build();

        if (userService.verifyEmail(userid, email, code)) {
            response = ResponseEntity.ok().body("이메일 인증이 완료되었습니다!!");
        }else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("이메일 인증 실패!! - 코드를 다시 확인하세요!!");
        }

        return response;
    }
}
