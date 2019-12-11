import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  StaticInit {
    public static ProofOfWork work;

    static {
        ClassLoader c1 = ClassLoader.getSystemClassLoader();
        ClassLoader c2 = StaticInit.class.getClassLoader();
        for(ClassLoader cl : Arrays.asList(c1, c2)) {
            try {
                final StringBuilder builder = new StringBuilder();
                findParent(cl, new ArrayList<>()).forEach(c -> builder.append( c.toString() + " -> "));
                final String message = String.format("Attempting to load with classLoaders: %s", builder.toString());
                System.out.println(message);
                //ProofOfWork is class available in the classpath specified in the command line
                Class<?> clz = cl.loadClass("ProofOfWork");
                System.out.println("Successfully loaded" +
                        "" +
                        " class with " + cl.getClass().getName());
                work = (ProofOfWork) clz.getConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("Can not load class with: " + c1.getClass().getName() + " because " + e);
            }
        }

    }

    public static List<ClassLoader> findParent(ClassLoader cl, List<ClassLoader> acc) {
        if (cl == null) {
            return acc;
        }
        acc.add(cl);
        return findParent(cl.getParent(), acc);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(work);
    }

}