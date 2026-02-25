public class QuickSort {

    public static int partition(int[] arr, int low, int high) {
        // last element pivot
        int pivot = arr[high];
        // divide pointer i one below low bound
        int i = low -1;

        // loop
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        // i will be in pivots spot, swap
        int tmp = arr[i +1];
        arr[i +1] = arr[high];
        arr[high] = tmp;

        // return pivot loc so it can be excluded
        return i + 1;

    }

    public static void quick_sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quick_sort(arr, low, pivot - 1);
            quick_sort(arr, pivot + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] data = {5,2,6,4,7,10,3,6};

        System.out.print("Unsorted: ");
        for (int n : data) {
            System.out.print(n + " ");
        }
        System.out.println();
        
        quick_sort(data, 0, data.length - 1);

        System.out.print("Sorted: ");
        for (int n : data) {
            System.out.print(n + " ");
        }
    }
}
