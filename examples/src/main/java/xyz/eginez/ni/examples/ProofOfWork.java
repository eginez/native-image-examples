package xyz.eginez.ni.examples;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.Duration;

public class ProofOfWork {

    private final int maxDuration;
    private final MessageDigest digest;
    private final int target;

    public ProofOfWork(String algo, int maxDuration, int target) throws NoSuchAlgorithmException {
         digest = MessageDigest.getInstance(algo);
         this.maxDuration = maxDuration;
         this.target = target;
    }

    public long run(String message) throws Exception {
        Instant start = Instant.now();
        int i = 0;
        while (Duration.between(start, Instant.now()).toMillis() < maxDuration) {
            String m = String.format("%s%d", message, i);
            byte[] hash = computeHash(m.getBytes(StandardCharsets.UTF_8));
            if (reachedTarget(hash)) {
                return i;
            }
            i++;
        }
        throw new IllegalStateException("Could not find nonce on alloted time");
    }



    public static void main(String[] args) throws Exception {
        System.out.println("starting test");
        final ProofOfWork proofOfWork = new ProofOfWork("SHA-256", 5 * 1000, 2);
        final long nonce = proofOfWork.run("Some long message here");
        System.out.println(String.format("found nonce %d", nonce));
    }

    public byte[] computeHash(byte[] message) {
        return digest.digest(message);
    }

    public boolean reachedTarget(byte[] hash) {
        for (int i = 0; i < target; i++) {
            if (hash[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
