public class Week3 {
    public static void main(String[] args) {
        long [] times = new long[5];

        for (int i = 0; i < 5; i++) {
            // Current time in nanoseconds
            long start_t = System.nanoTime(); 

            // Function to test
            int x = 0;
            for (int j = 0; j < 10000; j++) {
                x=j;
            }

            // Time after to compare
            long end_t = System.nanoTime();

            System.out.println("10,000 iterations took " + (end_t - start_t + " nanoseconds."));
            times[i] = end_t - start_t;
        }
        System.out.println("Average time was " + (times[0] + times[0] + times[0] + times[0] + times[0]) / 5 + " nanoseconds.");
    }
}
