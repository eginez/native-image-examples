package xyz.eginez.ni.examples;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

public class ProofOfWorkTest {
    @Test
    public void testHash() throws Exception {
        final ProofOfWork proofOfWork = new ProofOfWork("SHA-256", 5000, 1);
        final byte[] hash = proofOfWork.computeHash("hello".getBytes(StandardCharsets.UTF_8));
        Assert.assertNotNull(hash);
        Assert.assertTrue(hash.length > 0);
    }

    @Test
    public void testProofWork() throws Exception {
        final ProofOfWork proofOfWork = new ProofOfWork("SHA-256", 5000, 2);

        final LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(2)
                .recordStats()
                .build(new CacheLoader<String, Long>() {
                           @Override
                           public Long load(String key) throws Exception {
                               return proofOfWork.run(key);
                           }
                       }
                );

        for (int i = 0; i < 10; i++) {
            final Instant start = Instant.now();
            cache.get("hello");
            final Duration total = Duration.between(start, Instant.now());
            Assert.assertTrue( total.toMillis() <= 5000);
        }
    }
}
