package uom.cp;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    private Test() {
    }

    public static long test(IList list, int nThreads, int mMember, int mDelete, int mInsert, int bound, int iteration, boolean allowStats) {

        ExecutorService service = Executors.newFixedThreadPool(nThreads);

        // calculate time for all threads to complete m iterations altogether
        long start = System.currentTimeMillis();

        for (int threadId = 1; threadId <= nThreads; threadId++) {
            int finalThreadId = threadId;

            service.execute(() -> {

                List<Integer> operation = new LinkedList<>(Arrays.asList(0, 1, 2));

                // Within this thread I'm going to run a random operation on the list
                int memberCount = 0, deleteCount = 0, insertCount = 0;
                for (int j = 0; j < mMember + mDelete + mInsert; j++) {

                    // if 0 -> member(), 1 -> delete(), 2 -> insert()
                    int randVal = Util.getRandomInt(bound);

                    if (operation.get(0) == 0 && memberCount < mMember) {
                        // can return true / false -> doesn't matter
                        list.member(randVal);
                        memberCount++;
                        if (memberCount >= mMember) operation.remove((Integer) 0);

                    } else if (operation.get(0) == 1 && deleteCount < mDelete) {
                        // can return true / false -> doesn't matter
                        list.delete(randVal);
                        deleteCount++;
                        if (deleteCount >= mDelete) operation.remove((Integer) 1);

                    } else if (operation.get(0) == 2 && insertCount < mInsert) {
                        // increment only upon successful insertion
                        if (list.insert(randVal)) {
                            insertCount++;
                            if (insertCount >= mInsert) operation.remove((Integer) 2);
                        }
                    }
                    Collections.shuffle(operation, Util.getSecureRandom());
                }

                //if (allowStats) {
                //    System.out.printf("\tThread %d has completed execution.\n", finalThreadId);
                //    System.out.printf("\tmember ops = %d, delete ops = %d, insert ops = %d\n\n", memberCount, deleteCount, insertCount);
                //}
            });
        }

        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ignored) {

        }
        long end = System.currentTimeMillis();

        long totalTime = end - start;
        if (allowStats){
            System.out.printf("\tTest complete for %s => iteration: %d, total time taken: %d ms\n",
                    list.getClass().getSimpleName(),
                    iteration, totalTime);
        }

        return totalTime;
    }
}
