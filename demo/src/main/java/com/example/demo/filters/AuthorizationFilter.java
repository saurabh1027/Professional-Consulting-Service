package com.example.demo.filters;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.BasicUserDetailsService;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
	
    @Autowired
    BasicUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username="";
		String password="";
//		1. Get Authorization Header
		String authorizationHeader = request.getHeader("Authorization").substring(6);
		Decoder decoder = Base64.getDecoder();
		String creds = new String(decoder.decode(authorizationHeader));
//		2. Fetch Username & Password from header
		if(authorizationHeader!=null) {
			username = creds.substring(0, creds.indexOf(':'));
			password = creds.substring(creds.indexOf(':')+1);
		}
		if(username!=null && !username.trim().equals("") && SecurityContextHolder.getContext().getAuthentication()==null) {
//			3. Validate Username & Password with database users
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			boolean bool = userDetails.getPassword().trim().equals(password.trim());
//			4. If user found then proceed else unauthorized access
			if(bool && userDetails!=null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                	.setDetails(new WebAuthenticationDetailsSource()
                	.buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                throw new BadCredentialsException("Bad Credentials!");
            }
		}else {
			throw new UsernameNotFoundException("username not found!");
		}
		filterChain.doFilter(request, response);
	}

}
