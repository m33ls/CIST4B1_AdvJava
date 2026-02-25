
public class MergeSort {
    public static void mergeSort(int[] arr) {
        // Base Case
        // Array size <= 1
        if (arr.length <= 1) {
            return;
        } 

        // Split
        // Get the middle with integer division ( round down )
        // Create left (0 -> mid) and right (mid -> array.length -1) arrays

        // Recursive Case
        mergeSort(left);
        mergeSort(right);

        // Conquer
        // Iterate through left and right using two seperate counters
        // Weave them into arr, and each time we add from left or right, increment its counter
        // Stop when both counters are the size of their respective arrays
    }
}
