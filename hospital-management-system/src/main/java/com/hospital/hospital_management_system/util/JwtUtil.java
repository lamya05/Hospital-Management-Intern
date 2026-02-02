package com.hospital.hospital_management_system.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys; // Idhu pudhusa add pannanum
import org.springframework.stereotype.Component;
import java.security.Key; // Idhu thevai
import java.util.Date;

@Component
public class JwtUtil {
    // Secret key kandippa 32 characters-avadhu irukkanum
    private String secret = "hospital_management_system_secret_key_123"; 
    private Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) 
                .signWith(key, SignatureAlgorithm.HS256) // Ippo yellow line varaadhu
                .compact();
    }
}
