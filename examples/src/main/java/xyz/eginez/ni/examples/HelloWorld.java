package xyz.eginez.ni.examples;

import java.lang.reflect.Method;
import java.time.ZoneId;
import java.util.TimeZone;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        final Method getSystemTimeZoneID = TimeZone.class.getDeclaredMethod("getSystemTimeZoneID", String.class);
        getSystemTimeZoneID.setAccessible(true);
        System.out.println(getSystemTimeZoneID.invoke(null,"IN-GZ"));
        System.out.println(TimeZone.getDefault().getID());
        System.out.println(ZoneId.of("America/New_York").getId());
    }
}
