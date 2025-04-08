package com.example.svsvdvdv.semiprojectv2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "users3")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String userpwd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // 이메일 인증
    @Column
    private String enabled = "false";

    @Column
    private String verifycode;

    @Column
    private String role = "USER";

    // insert, update시 해당 컬럼 제외
    @CreationTimestamp
    //@Column(insertable = false, updatable = false)
    private LocalDateTime regdate;

    @Transient  // 엔티티 컬럼과 무관한 변수로 선언. **테이블의 컬럼과 상관없는 dto 형태
    @JsonProperty("g-recaptcha-response")
    private String gRecaptchaResponse;

}
