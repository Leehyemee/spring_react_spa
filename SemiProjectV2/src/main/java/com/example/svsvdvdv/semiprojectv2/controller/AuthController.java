package com.example.svsvdvdv.semiprojectv2.controller;

import com.example.svsvdvdv.semiprojectv2.domain.Member;
import com.example.svsvdvdv.semiprojectv2.domain.MemberDTO;
import com.example.svsvdvdv.semiprojectv2.domain.User;
import com.example.svsvdvdv.semiprojectv2.service.MemberService;
import com.example.svsvdvdv.semiprojectv2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 교차 출처 리소스 공유 CORS
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> joinok(@RequestBody User user) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원 정보 : {}", user);

        try {
            // 정상 처리시 상태코드 200으로 응답
            userService.newUser(user);
            response = ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginok(@RequestBody MemberDTO member) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 로그인 정보 : {}", member);

        try {
            Member loginUser = memberService.loginMember(member);
            // 세션 처리
            response = ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}