package uom.cp;

import uom.cp.impl.ParallelMutexLinkedList;
import uom.cp.impl.ParallelReadWriteLinkedList;
import uom.cp.impl.SerialLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Util.sanitizeArgs(args);

        //define parameters
        int n = Integer.parseInt(System.getProperty("n"));
        int m = Integer.parseInt(System.getProperty("m"));
        int nThreads = Integer.parseInt(System.getProperty("nThreads"));
        int bound = Integer.parseInt(System.getProperty("bound"));
        int testRuns = Integer.parseInt(System.getProperty("testRuns"));
        boolean allowStats = Boolean.parseBoolean(System.getProperty("allowStats"));

        int mMember = (int) (m * Double.parseDouble(System.getProperty("mMember"))) / nThreads;
        int mInsert = (int) (m * Double.parseDouble(System.getProperty("mInsert"))) / nThreads;
        int mDelete = (int) (m * Double.parseDouble(System.getProperty("mDelete"))) / nThreads;

        List<IList> lists = new ArrayList<>(3);
        if (nThreads == 1) lists.add(new SerialLinkedList());
        lists.add(new ParallelMutexLinkedList());
        lists.add(new ParallelReadWriteLinkedList());

        // for each list, I'm going to run tests for `testRuns` number of times
        for (IList list : lists) {

            System.out.printf("Initializing %s for testing\n", list.getClass().getSimpleName());

            long[] times = new long[testRuns];

            for (int i = 1; i <= testRuns; i++) {

                // first populate the list with n values
                if (!Util.populateList(list, n, bound)) {
                    System.out.printf("Failed populating %s. Cannot continue further execution. Try changing input values\n", list.getClass().getSimpleName());
                    return;
                }

                times[i - 1] = Test.test(list, nThreads, mMember, mDelete, mInsert, bound, i, allowStats);

                // when running test runs, I noticed abnormal time consumptions for 7-10th iterations.
                // I suspect this is because of the garbage collector in java
                // So I try to for gc, however it is not guaranteed
                System.gc();
            }

            System.out.printf("%s => average_time: %.3f ms, deviation: %.3f ms\n\n",
                    list.getClass().getSimpleName(),
                    mean(times),
                    std(times));
        }
    }

    private static double std(long[] array) {
        final double mean = mean(array);
        final double sum = Arrays.stream(array).mapToDouble(val -> Math.pow((val - mean), 2)).sum();

        // I'm calculating std deviation for sample of elements
        // hence divide by (total_num_of_elements - 1)
        // because the values are just a sample and not a population
        return Math.sqrt(sum / (array.length - 1));
    }

    private static double mean(long[] array) {
        //noinspection OptionalGetWithoutIsPresent
        return Arrays.stream(array).average().getAsDouble();
    }
}
