package io.mybag.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	// JWT 서명에 사용할 비밀 키 (String을 Key 객체로 변환)
	// Secret 키를 db에 저장하고있던지 해서 불러오는 식으로 나중에 구현
	private static final Key SECRET_KEY = Keys.hmacShaKeyFor("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes());

	// 토큰 생성 메서드
	public String createToken(String username) {
		Date expiryDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000); // 만료 시간 (30분 후)

        return Jwts.builder()
        		.setSubject(username)// 토큰의 사용자 정보 설정
                .setIssuedAt(new Date()) // 토큰 발급 시간 설정
                .setExpiration(expiryDate) // 만료 시간 설정
                .signWith(SECRET_KEY)
                .compact(); // JWT 토큰 생성
	}
	// 토큰에서 유저 정보 추출 메서드
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()  // parserBuilder() 사용
                .setSigningKey(SECRET_KEY) // 서명 검증을 위한 키 설정
                .build() // JwtParser 객체 생성
                .parseClaimsJws(token) // JWT 파싱
                .getBody(); // Payload 추출
        return claims.getSubject(); // 사용자 정보 반환
    }

	// 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()  // parserBuilder() 사용
                .setSigningKey(SECRET_KEY) // 서명 검증을 위한 키 설정
                .build() // JwtParser 객체 생성
                .parseClaimsJws(token); // 토큰 파싱 (만약 만료되었으면 예외 발생)
            return true; // 유효한 토큰
        } catch (Exception e) {
            return false; // 유효하지 않은 토큰
        }
    }

}
