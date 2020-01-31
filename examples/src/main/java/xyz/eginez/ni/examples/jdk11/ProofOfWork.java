package xyz.eginez.ni.examples.jdk11;

import java.security.MessageDigest;
import java.time.Instant;
import java.time.Duration;

@Deprecated
public class ProofOfWork {

    public static void main(String[] args) throws Exception {
        System.out.println("starting test");
        int maxDuration = 5 * 1000;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String message = "some message";
        int i = 0;
        Instant start = Instant.now();
        while (Duration.between(start, Instant.now()).toMillis() < maxDuration) {
            String m = String.format("%s%d", message, i);
            digest.digest(m.getBytes("UTF-8"));
            i++;
        }
        Instant stop = Instant.now();
        long timeElapsed = Duration.between(start, stop).toMillis();
        System.out.println(String.format("Took %d ms", timeElapsed));
        System.out.println("Success");
    }
}
