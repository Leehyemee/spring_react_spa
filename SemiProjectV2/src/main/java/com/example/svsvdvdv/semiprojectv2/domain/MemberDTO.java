package com.example.svsvdvdv.semiprojectv2.domain;

import lombok.Builder;
import lombok.Data;

@Data   // setter, getter, toStirng 자동 생성
@Builder    // 롬복에 있는 빌더
public class MemberDTO {        // DB전송 객체

    private String userid;
    private String userpwd;
    private String name;
    private String email;

}
