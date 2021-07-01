import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Mai {

    private static int i = 0;
    private static boolean loop = false;

    public static void main(String args[]) {

        int a = 40 / 30;
        System.err.println(a);

        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        int N = in.nextInt();

        int total = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int nextValue = in.nextInt();
            if (nextValue <= L){
                queue.add(nextValue);
                total +=nextValue;
            }
        }
        Queue<Integer> first = new LinkedList<>(queue);
        System.err.println(total);

        long res;
        if(L >= total) res = (long) total * C;
        else res = simRides(queue,L,C,first);

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(res);
    }

    private static long simRides(Queue<Integer> queue, int space, int times, Queue<Integer> first) {
        long res = 0;
        int i = 0;

        while (times > i) {
            System.err.println(times);
            int o = oneRide(queue,space);
            res += o;
            i++;
        }

        return res;
    }

    private static int oneRide(Queue<Integer> queue, int space) {
        int res = 0;

        while (space > 0 && queue.peek() <= space) {
            int n = queue.poll();
            queue.add(n);
            res += n;
            space -= n;
            i++;
        }

        return res;
    }
}

/*
3
100
4
3
2
3
2
 */


