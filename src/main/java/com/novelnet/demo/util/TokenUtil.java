package com.novelnet.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class TokenUtil {
    // Base64 编码密钥（长度 >= 32，推荐更长）
    private static final String SECRET_KEY = "M1dNczNjdXJFS2lEMm5wQVRKck1ZelN1TjRzV3BJV0R=";
    private static final SecretKey SECRET = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    // Token 过期时间（1天）
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    /**
     * 生成 Token（带用户 ID）
     */
    public static String makeToken(Map<String, Object> claim) {
        return Jwts.builder()
                .setClaims(claim)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 Token（带异常处理）
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("Token 已过期！");
        } catch (MalformedJwtException e) {
            System.out.println("Token 格式错误！");
        } catch (SignatureException e) {
            System.out.println("Token 签名无效！");
        } catch (Exception e) {
            System.out.println("Token 解析异常：" + e.getMessage());
        }
        return null;
    }
}
