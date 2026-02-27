/*
 * Merge Sort
 */

import java.util.Random;

public class merge_sort {
    public static void main(String[] args) {
        // Runs per test case
        int n = 1;
        int size = 10;

        // Iterate over test cases
        System.out.println("Random Cases");
        for (int i = 0; i < n; i++) {
            int[] rand_arr = gen_random(size);
            for (int num : rand_arr)
                System.out.print(num + " ");
            System.out.println();
            test(rand_arr,n);
        }
        System.out.println("Worst Cases");
        for (int i = 0; i < n; i++) {
            int[] rand_arr = gen_worst(size);
            for (int num : rand_arr)
                System.out.print(num + " "); 
            System.out.println();
            test(rand_arr,n);
        }
    }

    public static int[] gen_random(int size) {
        int[] arr = new int[size];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(0, 100);
        }
        return arr;
    }

    public static int[] gen_worst(int size) {
        // Generate random
        int[] arr = new int[size];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(0, 100);
        }

        // Sort
        // Split alternating, flip
        return arr;
    }

    public static void test(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            long start_t = System.nanoTime(); 

            // Merge sort test case
            merge_sort(arr); 

            long end_t = System.nanoTime();
            // Log time
            System.out.println("Sorted in " + (end_t - start_t) + " ns."); 
        }
    }

    public static void merge_sort(int[] arr) {
        // Split into sub arrays recursively
        int n = arr.length;
        if (n < 2) {
            return;
        }
        // Integer division to find midpoint
        int middle = n/2;
        
        // Split
        int[] left = new int[middle];
        int[] right = new int[n - middle];
        for (int i = 0; i < middle; i++) { // Populate left array
            left[i] = arr[i];
        }
        for (int i = middle; i < n; i++) { // Populate right
            right[i - middle] = arr[i]; // Since it starts at middle,
        }                               // sub middle from right to start from 0

        merge_sort(left); // Recurse over left
        merge_sort(right); // Recurse over right

        merge();

    }

}