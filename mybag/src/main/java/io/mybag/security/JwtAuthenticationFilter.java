package io.mybag.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		if (requestURI.startsWith("/api/") && !requestURI.equals("/api/auth/login")) {
			String header = request.getHeader("Authorization"); //헤더 추출
			if (header != null && header.startsWith("Bearer ")) { // 헤더에 오류가 없으면 진행
				String token = header.substring(7);
				if (jwtTokenProvider.validateToken(token)) { // 토큰 유효성 검사
					String username = jwtTokenProvider.getUsernameFromToken(token);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							username, null, null);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}else {
					// 토큰에 문제가 있으면
					UsernamePasswordAuthenticationToken unAuthentication = new UsernamePasswordAuthenticationToken(null,null,null);
					SecurityContextHolder.getContext().setAuthentication(unAuthentication);
				}
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Authorization header is missing");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}
