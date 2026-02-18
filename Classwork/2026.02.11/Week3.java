/*class selectionSort {
    public static void selectionSort(int[] arr) {
            for (int i = 0; i < arr.length -1; i++) {
                int minIndex = i;
                
                // Search unsorted for smallest index
                for ( int j = i + 1; j < arr.length; j++) {
                    if ( arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }

                // Swap minIndex with whatever first item unsorted is arr[i]
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
    }

    public static void main(String[] args) {
        int[] data = {51,2,88,74,73,2,5,18};
        for (int i : data) {
            System.out.print(i + " ");
        }
        selectionSort(data);
        System.out.println();
        for (int i : data) {
            System.out.print(i + " ");
        }
    }
} */

public class insertionSort {

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int element = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > element) {
                arr[j+1] = arr[j];
                j--;
            }

            arr[j+1] = element;
        }
    
    }

    public static void main(String[] args) {
        int[] data = {51,2,88,74,73,5,18,24,26,52,190};

        for (int i : data) {
            System.out.print(i + " ");
        }

        insertionSort(data);

        System.out.println();
        for (int i : data) {
            System.out.print(i + " ");
        }
    }
}