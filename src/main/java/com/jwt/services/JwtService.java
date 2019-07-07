package com.jwt.services;

import com.jwt.pojo.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    @Value("${jwt.expire.hours}")
    private long expireHours;

    @Value("${jwt.token.secret}")
    private String plainSecret;
    private String encodedSecret;

    @PostConstruct
    protected void init() {
        if (StringUtils.isEmpty(plainSecret))
            throw new IllegalArgumentException("JWT secret cannot be null or empty.");

        this.encodedSecret = Base64
                .getEncoder()
                .encodeToString(this.plainSecret.getBytes());
    }

    private Date getExpirationTime() {
        Date now = new Date();
        long expireInMillis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMillis + now.getTime());
    }

    public JwtUser getUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();

        String username = (String) claims.get("username");
        String password = (String) claims.get("password");
        JwtUser securityUser = new JwtUser();
        securityUser.setUsername(username);
        securityUser.setPassword(password);
        return securityUser;
    }


    public String getToken(JwtUser jwtUser) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .claim("username", jwtUser.getUsername())
                .claim("password", jwtUser.getPassword()) //TODO hash pass
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }
}
