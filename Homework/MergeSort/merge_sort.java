/*
`7MMM.     ,MMF'                                      .M"""bgd                    mm    
  MMMb    dPMM                                       ,MI    "Y                    MM    
  M YM   ,M MM  .gP"Ya `7Mb,od8 .P"Ybmmm .gP"Ya      `MMb.      ,pW"Wq.`7Mb,od8 mmMMmm  
  M  Mb  M' MM ,M'   Yb  MM' "':MI  I8  ,M'   Yb       `YMMNq. 6W'   `Wb MM' "'   MM    
  M  YM.P'  MM 8M""""""  MM     WmmmP"  8M""""""     .     `MM 8M     M8 MM       MM    
  M  `YM'   MM YM.    ,  MM    8M       YM.    ,     Mb     dM YA.   ,A9 MM       MM    
.JML. `'  .JMML.`Mbmmd'.JMML.   YMMMMMb  `Mbmmd'     P"Ybmmd"   `Ybmd9'.JMML.     `Mbmo 
                               6'     dP                                                
                               Ybmmmd'                                       ( Amelia )
 */

import java.util.Random;

public class merge_sort {
    public static void main(String[] args) {
        // Arr size
        int size = 100;

        // Iterate over test cases
        System.out.println("Random Cases");
        int[] rand_arr = gen_random(size);
        for (int num : rand_arr)
            System.out.print(num + " ");
        System.out.println();
        test(rand_arr);
        for (int num : rand_arr)
            System.out.print(num + " ");
        System.out.println();
        System.out.println();
            

        System.out.println("Worst Cases");
        int[] worst_arr = gen_worst(size);
        for (int num : worst_arr)
            System.out.print(num + " "); 
        System.out.println();
        test(worst_arr);
        for (int num : worst_arr)
            System.out.print(num + " ");
        System.out.println();
        System.out.println();    
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
        int[] arr = gen_random(size);
        
        merge_sort(arr); // Start with a sorted array
        arr = worsinate(arr, 0, size);

        // For the worst case, the numbers should be interwoven
        // like merge sort does, but the opposite way around, so
        // that they are as mixed up as possible based on the way
        // merge sort splits and weaves.
        return arr;
    }

    public static int[] worsinate(int[] arr, int start, int length) {
        if (length <= 1)
            return null;

        int middle = length / 2;

        int[] tmp = new int[length];
        int idx = 0;

        // All even idxs
        for (int i = 0; i < length; i+=2 ) {
            tmp[idx] = arr[start + i];
            idx++;
        }

        // All odd idx
        for ( int i = 1; i < length; i+=2) {
            tmp[idx] = arr[start + i];
            idx++;
        }

        // Copy from tmp -> arr
        for (int i = 1; i < length; i++) {
            arr[start + i] = tmp[i];
        }

        // Recurse L + R
        worsinate(arr, start, middle);
        worsinate(arr, start + middle, length - middle);
        return arr;
    }

    public static void test(int[] arr) {
        int total = 0;
        for (int j = 0; j < 100; j++) {
                long start_t = System.nanoTime(); 

                // Merge sort test case
                merge_sort(arr); 

                long end_t = System.nanoTime();
                // Log time
                total += (end_t - start_t);
                // System.out.println("Sorted in " + (end_t - start_t) + " ns."); 
        }
        System.out.println("Average of 100 runs: " + (total / 100));
    }

    public static void merge_sort(int[] arr) { // Really a split function but it sounds
                                               // better when calling from test &c. lol
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

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0; // left index
        int j = 0; // right index
        int k = 0; // arr index
        while (i < left.length && j < right.length) { // both not empty
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                k++;
                i++;
            } else {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
        
        while (i < left.length) {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            k++;
            j++;
        }
        
    }

}