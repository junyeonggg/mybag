package io.mybag.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
    // JWT 서명에 사용할 비밀 키 (String을 Key 객체로 변환)
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("\"\\\\FL%|n_Pn[Q9eJU`:k?d'6$?P;b9\\\"y:\\\"\"".getBytes());

    // 토큰 생성 메서드
    public String createToken(String username) {
        Date expiryDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);  // 만료 시간 (30분 후)

        return Jwts.builder()
                .claim("sub", username)  // subject(사용자 정보) 설정
                .claim("iat", new Date())  // 발급 시간 설정 (issuedAt)
                .claim("exp", expiryDate)  // 만료 시간 설정 (expiration)
                .signWith(SECRET_KEY)  // 서명 (SECRET_KEY는 SecretKey 객체)
                .compact();  // 최종 토큰 생성
    }
	
	
	
	// 토큰 유효성 검증 메서드
	public boolean validateToken(String token) {
		return false;
	}
	
	
	// 토큰에서 유저 정보 추출 메서드
	public String getUsernameFromToken(String token) {
		return "";
	}
	
}
