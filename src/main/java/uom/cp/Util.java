package uom.cp;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Util {

    private static final Random secureRandom = new SecureRandom();

    private Util() {
    }

    /**
     * Generating unique values
     *
     * @param list  the given list
     * @param size  numbers of random numbers to be generated / the size of given list
     * @param bound see {@link #getRandomInt(int)}
     */
    public static boolean populateList(IList list, int size, int bound) {
        if (bound < size) {
            System.out.printf("CANNOT populate list of size %d with unique values in range [0, %d]\n", size, bound - 1);
            return false;
        }
        IntStream
                .generate(() -> getRandomInt(bound))
                .distinct()
                .limit(size)
                .forEach(list::insert);
        return true;
    }

    /**
     * I used {@link SecureRandom} class to make sure the randomness is closer to truly random values
     *
     * @param bound upper bound of each generated random number (exclusive)
     * @return a random number
     */
    public static int getRandomInt(int bound) {
        return secureRandom.nextInt(bound);
    }

    /**
     * @return the object itself
     */
    public static Random getSecureRandom() {
        return secureRandom;
    }

    /**
     * <code>args[]</code> should be of following order. Any arg that doesn't comply to the rules or
     * specified <code>null</code> will be stored as its default value
     * <ol>
     *     <li> <code>n</code> -> size of generated list (int)</li>
     *
     *     <li> <code>m</code> -> number of different operations
     *     <ol>
     *         <li>Member()</li>
     *         <li>Insert()</li>
     *         <li>Delete()</li>
     *     </ol>
     *     to execute on the given list <b>altogether</b>
     *     </li>
     *
     *  <li>
     *         <code>bound</code> -> upper bound (exclusive) for each value in the list (int)
     * <p>
     *         upper bound should be <= 2**16 - 1 -> I'm not doing any validation for these
     *     </li>
     *
     *     <li> <code>nThreads</code> -> number of threads to be used for parallel operations</li>
     *     <li> <code>testRuns</code> -> number of times to repeat the test to get average and deviation</li>
     * </ol>
     */
    public static void sanitizeArgs(String[] args) {

        addToEnv(args, 0, "n", 1000);
        addToEnv(args, 1, "m", 10_000);

        addToEnv(args, 2, "mMember", 0.5);
        addToEnv(args, 3, "mInsert", 0.25);
        addToEnv(args, 4, "mDelete", 0.25);

        addToEnv(args, 5, "bound", (int) Math.pow(2, 16) - 1);
        addToEnv(args, 6, "nThreads", Runtime.getRuntime().availableProcessors());
        addToEnv(args, 7, "testRuns", 10);
        addToEnv(args, 8, "allowStats", false);

        System.out.println("\nSystem properties: ");
        System.out.printf("%s : %s\n", "os.name", System.getProperty("os.name"));
        System.out.printf("%s : %s\n", "os.arch", System.getProperty("os.arch"));
        System.out.printf("%s : %s\n", "java.vm.name", System.getProperty("java.vm.name"));
        System.out.printf("%s : %s\n", "java.vm.version", System.getProperty("java.vm.version"));
        System.out.println();
    }

    private static void addToEnv(String[] args, int argPos, String name, int defaultVal) {
        int val = defaultVal;
        try {
            if (args[argPos].equals("null")) throw new NumberFormatException("empty value");
            val = Integer.parseInt(args[argPos]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

        } finally {
            System.setProperty(name, String.valueOf(val));
            System.out.printf("%s : %d\n", name, val);
        }
    }

    private static void addToEnv(String[] args, int argPos, String name, double defaultVal) {
        double val = defaultVal;
        try {
            if (args[argPos].equals("null")) throw new NumberFormatException("empty value");
            val = Double.parseDouble(args[argPos]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

        } finally {
            System.setProperty(name, String.valueOf(val));
            System.out.printf("%s : %.3f\n", name, val);
        }
    }

    private static void addToEnv(String[] args, int argPos, String name, boolean defaultVal) {
        boolean val = defaultVal;
        try {
            if (args[argPos].equals("null")) throw new NumberFormatException("empty value");
            val = Boolean.parseBoolean(args[argPos]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {

        } finally {
            System.setProperty(name, String.valueOf(val));
            System.out.printf("%s : %s\n", name, val);
        }
    }
}
