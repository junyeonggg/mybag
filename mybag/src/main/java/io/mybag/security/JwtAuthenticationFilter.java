package io.mybag.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 1. 헤더 추출
		String header = request.getHeader("Authorization");

		// 2. Bearer로 시작하는지 검증
		if (header != null && header.startsWith("Bearer ")) {

			// 3. "Bearer "을 제거하고 토큰만 추출
			String token = header.substring(7);

			// 4. 유효한 토큰인지 확인
			if (jwtTokenProvider.validateToken(token)) {

				// 5. 토큰에서 username 추출
				String username = jwtTokenProvider.getUsernameFromToken(token);

				// 6. UsernamePasswordAuthenticationToken 생성
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						username, null, null);
				
				//7. SecurityContextHolder의 context에 인증을 저장한다.
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		//8. 다음 필터체인으로 넘긴다.
		filterChain.doFilter(request, response);
	}
}
