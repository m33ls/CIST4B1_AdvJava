public class VerticalNumbers {
    final static int number = 1234;

    public static void writeVertical(int n) {
        if (n < 0) {
            System.out.println("-");
            n *= -1;
        }
        if (n < 10) {
            System.out.println(n);
            return;
        }
        writeVertical(n / 10);
        System.out.println(n % 10);
        }

    public static void main(String[] args) {
        writeVertical(number);
    }
}
