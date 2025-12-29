package com.moc.pro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 비밀번호 암호화 설정
 * BCryptPasswordEncoder를 Spring Bean으로 등록
 */
@Configuration
public class PasswordConfig {
    
    /**
     * BCryptPasswordEncoder Bean 등록
     * @return BCryptPasswordEncoder 인스턴스
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
