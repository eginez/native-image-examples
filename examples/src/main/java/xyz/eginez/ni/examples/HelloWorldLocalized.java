package xyz.eginez.ni.examples;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloWorldLocalized {
    private final Locale locale;

    public String getString(String key, Locale locale) {
        final ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        final String localizedString =  bundle.getString(key);
        return localizedString;
    }

    public String getString(String key) {
        return getString(key, this.locale);
    }

    public HelloWorldLocalized(Locale locale) {
        this.locale = locale;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.language"));
        System.out.println(String.format("Default locale is: %s", Locale.getDefault()));
        final HelloWorldLocalized pp = new HelloWorldLocalized(null);
        System.out.println(Arrays.toString(Locale.getAvailableLocales()));
        for (Locale l : Locale.getAvailableLocales()) {
            System.out.println(pp.getString("hello", l));
        }
    }
}
