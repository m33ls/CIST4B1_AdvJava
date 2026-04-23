
class DuplicateChecker {
    public static boolean hasDuplicates(int[] arr, int min, int max) {
        boolean[] seen = new boolean[max - min + 1];
        for (int n : arr) {
            int idx = n - min;
            if (seen[idx]) {
                return true;

            }
            seen[idx] = true;

        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,5,3,2,6};
        int[] arr2 = {1,2,3,5,4,6,7};
        System.out.println(hasDuplicates(arr1, 1, 10));
        System.out.println(hasDuplicates(arr2, 1, 10));
    }
}
