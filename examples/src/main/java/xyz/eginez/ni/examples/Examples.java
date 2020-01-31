package xyz.eginez.ni.examples;

import java.lang.annotation.Annotation;
import java.net.URL;

public class Examples {
    public static void main(String[] args) throws Exception{
        //loadXmlResources();
        loadPackagesInfo();
        //showAnnotations();
    }

    public static void loadXmlResources() throws Exception {
        final URL resource = ClassLoader.getSystemClassLoader()
                .getResource("com/sun/org/apache/xml/internal/serializer/output_xml.properties");
        System.out.println(resource);
    }

    public static void showAnnotations() throws Exception {
        final Class<?> aClass = Class.forName("xyz.eginez.ni.examples.jdk11.ProofOfWork");
        System.out.println("Pow: " + aClass.getAnnotations().length);
    }

    public static void loadPackagesInfo() throws Exception {
        final Package aPackage = Examples.class.getPackage();
        System.out.println("Package: " + aPackage);
        System.out.println("Ann: "+ aPackage.getAnnotations());
        for(Annotation a : aPackage.getAnnotations()) {
            System.out.println("Ann: " + a.toString());
        }
    }

}
