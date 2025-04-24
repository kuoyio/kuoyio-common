package cn.kuoyio.common.util;

import cn.kuoyio.common.domain.exception.JwtVerifyException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.Map;


public class JwtUtil {
    private static final long DEFAULT_EXPIRATION_TIME = (long) 60 * 60;

    public static String generate(String secret, String subject, Map<String, Object> claims) {
        return generate(secret, DEFAULT_EXPIRATION_TIME, subject, claims);
    }

    public static String generate(String secret, long expirationTime, String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)), Jwts.SIG.HS256)
                .compact();
    }

    public static void verify(String secret, String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new JwtVerifyException("Token已过期");
        } catch (UnsupportedJwtException e) {
            throw new JwtVerifyException("不支持的 JWT");
        } catch (MalformedJwtException e) {
            throw new JwtVerifyException("JWT 格式错误");
        } catch (Exception e) {
            throw new JwtVerifyException("JWT 验证失败");
        }
    }
}
