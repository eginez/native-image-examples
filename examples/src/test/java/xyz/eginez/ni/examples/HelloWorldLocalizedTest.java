package xyz.eginez.ni.examples;

import org.junit.Test;

import java.util.Locale;

public class HelloWorldLocalizedTest {

    @Test
    public void testLocal() {
        final HelloWorldLocalized localized = new HelloWorldLocalized(Locale.getDefault());
        localized.getString("hello world");
    }
}
