
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // [weight, index]
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        // [finishTime, weight, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                int c = Long.compare(a[0], b[0]);
                if (c != 0) return c;

                c = Long.compare(a[1], b[1]);
                if (c != 0) return c;

                return Long.compare(a[2], b[2]);
            }
        );

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {

            time = Math.max(time, j);

            // Release all servers completed by current time
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();

                free.offer(new int[]{
                    (int) s[1],
                    (int) s[2]
                });
            }

            // Wait until a server becomes available
            if (free.isEmpty()) {

                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] s = busy.poll();

                    free.offer(new int[]{
                        (int) s[1],
                        (int) s[2]
                    });
                }
            }

            int[] server = free.poll();

            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            busy.offer(new long[]{
                time + tasks[j],
                weight,
                index
            });
        }

        return ans;
    }
}
