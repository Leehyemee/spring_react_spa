package com.example.svsvdvdv.semiprojectv2.controller;

import com.example.svsvdvdv.semiprojectv2.domain.KakaoTokenResponse;
import com.example.svsvdvdv.semiprojectv2.domain.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/oauth/kakao")
@CrossOrigin(origins = {"http://localhost:5173", "http://172.30.1.25:3000"})
public class KakaoController {

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.redirect.uri}")
    private String redirectUri;

    @Value("${kakao.redirect.logout.uri}")
    private String redirectLogoutUri;

    private final RestTemplate restTemplate;
    private static String AccessToken = null;

    // 카카오 로그인 - 로그인 후 인가 코드 받기
    @GetMapping("/login")
    public String kakaoLogin() {
        String authorizeUrl = "https://kauth.kakao.com/oauth/authorize";
        String params = "?client_id=%s&redirect_uri=%s&response_type=code";
        String kakaoUrl = String.format(authorizeUrl+params, clientId, redirectUri);

        return "redirect:"+kakaoUrl;
    }

    // 카카오 인증 후 redirect 엔드포인트 - 인가 코드를 이용해서 액세스토큰 받기
    @GetMapping("/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam String code) {
        // 1단계 : 인가 코드 출력
        log.info("인가 코드: {}", code);

        // 2단계: 액세스토큰 요청
        String authorizeUrl = "https://kauth.kakao.com/oauth/token";
        String params = "?client_id=%s&redirect_uri=%s&code=%s&grant_type=authorization_code";
        String kakaoUrl = String.format(authorizeUrl + params, clientId, redirectUri, code);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HTTP 요청 엔티티 생성
        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            // POST 요청으로 토큰 받기
            ResponseEntity<KakaoTokenResponse> response = restTemplate.postForEntity(
                    kakaoUrl,
                    request,
                    KakaoTokenResponse.class
            );

            // 응답에서 액세스토큰 추출
            KakaoTokenResponse tokenResponse = response.getBody();
            if (tokenResponse != null) {
                AccessToken = tokenResponse.getAccess_token();
                log.info("Access token: {}", AccessToken);
            }

            // 사용자 정보 가져오기
            KakaoUserInfo kakaoUserInfo = getUserInfo(AccessToken);
            log.info("kakaoUserInfo: {}", kakaoUserInfo);

            return ResponseEntity.ok().body(kakaoUserInfo.getProperties().getNickname());
        } catch (Exception e) {
            log.error("Error getting token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting token: " + e.getMessage());
        }

    }

    // 액세스토큰으로 사용자정보 알아내기
    private KakaoUserInfo getUserInfo(String accessToken) {
        // 사용자 정보 요청을 위한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization", "Bearer " + accessToken);

        // HTTP 요청 엔티티 생성
        HttpEntity<String> request = new HttpEntity<>(headers);

        // GET 요청으로 사용자 정보 받기
        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                KakaoUserInfo.class
        );

        return response.getBody();
    }

    // 카카오 로그아웃
    @GetMapping("/logout")
    // public ResponseEntity<String> kakaoLogout() {
    public String kakaoLogout() {
        // 카카오가 발급한 액세스 토큰 무효화 - 재로그인 시 아이디/비번 다시 입력 없음
        String logoutUrl = "https://kapi.kakao.com/v1/user/logout";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization", "Bearer " + AccessToken);

        System.out.println("Using token: " + AccessToken);
        System.out.println("Authorization header: " + headers.get("Authorization"));

        // HTTP 요청 엔티티 생성
        HttpEntity<String> request = new HttpEntity<>(headers);

        // GET 요청으로 사용자 정보 받기
        ResponseEntity<String> response = restTemplate.exchange(
                logoutUrl, HttpMethod.POST,
                request, String.class
        );

        log.info("Logout Response: {}", response.getStatusCode());

        // return ResponseEntity.ok("로그아웃 성공!!");

        // 완전한 로그아웃 - 재로그인 시 아이디/비번 다시 입력 필요!
        logoutUrl = "https://kauth.kakao.com/oauth/logout";
        String params = String.format("?client_id=%s&logout_redirect_uri=%s", clientId, redirectLogoutUri);

        return "redirect:" + logoutUrl + params;
    }
}

