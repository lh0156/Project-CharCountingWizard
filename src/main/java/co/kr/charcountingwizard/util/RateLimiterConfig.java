package co.kr.charcountingwizard.util;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.Bandwidth;

import java.time.Duration;

public class RateLimiterConfig {

    private static final Bucket bucket = createBucket();

    public static boolean tryConsume() {
        return bucket.tryConsume(1);
    }

    private static Bucket createBucket() {
        // 초당 10건 방지
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
        return Bucket4j.builder()
                       .addLimit(limit)
                       .build();
    }
}