package com.ramsuvegatech.stationstock.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtils {

	private String secret = "mysecretkeymysecretkeymysecretkey12";

	public String generateToken(String email) {
		try {
			Key key = Keys.hmacShaKeyFor(secret.getBytes());

			return Jwts.builder().setSubject(email).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 86400000))
					.signWith(key, SignatureAlgorithm.HS256).compact();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}