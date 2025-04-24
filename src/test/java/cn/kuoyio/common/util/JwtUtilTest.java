package cn.kuoyio.common.util;

import cn.kuoyio.common.domain.exception.JwtVerifyException;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeAll;
import java.util.concurrent.*;

public class JwtUtilTest {

    private static String validSecret;
    private static String invalidSecret;
    private static final String subject = "testUser";
    private static Map<String, Object> claims;

    @BeforeAll
    public static void setup() {
        byte[] secretBytes = new byte[32];
        new java.util.Random().nextBytes(secretBytes);
        validSecret = Base64.getEncoder().encodeToString(secretBytes);

        invalidSecret = Base64.getEncoder().encodeToString("short".getBytes());

        claims = new HashMap<>();
        claims.put("role", "admin");
        claims.put("userId", 12345);
    }

    @Test
    public void given_valid_parameters_when_generate_token_then_return_non_empty_string() {
        String token = JwtUtil.generate(validSecret, subject, claims);
        Assertions.assertNotNull(token, "生成的token不应为null");
        Assertions.assertFalse(token.isEmpty(), "生成的token不应为空字符串");
    }

    @Test
    public void given_valid_parameters_when_generate_token_with_custom_expiration_then_return_valid_token() {
        long expirationTime = 30 * 60 * 1000;
        String token = JwtUtil.generate(validSecret, expirationTime, subject, claims);

        Assertions.assertDoesNotThrow(() -> JwtUtil.verify(validSecret, token),
                "生成的token应该能通过验证");
    }

    @Test
    public void given_valid_token_when_verify_then_no_exception_thrown() {
        String token = JwtUtil.generate(validSecret, subject, claims);
        Assertions.assertDoesNotThrow(() -> JwtUtil.verify(validSecret, token),
                "有效token验证时不应抛出异常");
    }

    @Test
    public void given_invalid_secret_when_verify_token_then_throw_security_exception() {
        String token = JwtUtil.generate(validSecret, subject, claims);
        Assertions.assertThrows(JwtVerifyException.class,
                () -> JwtUtil.verify(invalidSecret, token),
                "使用错误密钥验证时应抛出安全异常");
    }

    @Test
    public void given_expired_token_when_verify_then_throw_expired_exception() {
        String token = Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis() - 10000))
                .expiration(new Date(System.currentTimeMillis() - 5000))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(validSecret)), Jwts.SIG.HS256)
                .compact();

        Assertions.assertThrows(JwtVerifyException.class,
                () -> JwtUtil.verify(validSecret, token),
                "过期token验证时应抛出过期异常");
    }

    @Test
    public void given_malformed_token_when_verify_then_throw_malformed_exception() {
        String malformedToken = "invalid.token.string";
        Assertions.assertThrows(JwtVerifyException.class,
                () -> JwtUtil.verify(validSecret, malformedToken),
                "格式错误token验证时应抛出格式异常");
    }

    @Test
    public void given_token_without_signature_when_verify_then_throw_security_exception() {
        String unsignedToken = Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();

        Assertions.assertThrows(JwtVerifyException.class,
                () -> JwtUtil.verify(validSecret, unsignedToken),
                "未签名token验证时应抛出安全异常");
    }

    @Test
    public void given_multiple_threads_generating_tokens_concurrently_then_all_tokens_should_be_valid()
            throws InterruptedException, ExecutionException {

        int threadCount = 10;
        int tokensPerThread = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Future<?>[] futures = new Future[threadCount];

        for (int i = 0; i < threadCount; i++) {
            futures[i] = executorService.submit(() -> {
                try {
                    for (int j = 0; j < tokensPerThread; j++) {
                        String token = JwtUtil.generate(validSecret, subject, claims);
                        Assertions.assertDoesNotThrow(
                                () -> JwtUtil.verify(validSecret, token),
                                "并发生成的token应该都能通过验证");
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        for (Future<?> future : futures) {
            future.get();
        }

        executorService.shutdown();
    }

    @Test
    public void given_jwt_util_when_generate_10k_tokens_then_complete_within_5_seconds() {
        int count = 10000;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            JwtUtil.generate(validSecret, subject, claims);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.printf("生成 %d 个JWT耗时: %d ms, 平均每个JWT生成时间: %.3f ms%n",
                count, duration, (double) duration / count);

        Assertions.assertTrue(duration < 5000, "生成1万个JWT应该在5秒内完成");
    }

    @Test
    public void given_null_claims_when_generate_token_then_return_valid_token() {
        Assertions.assertDoesNotThrow(
                () -> JwtUtil.generate(validSecret, subject, null),
                "null claims应该被允许");
    }
}
