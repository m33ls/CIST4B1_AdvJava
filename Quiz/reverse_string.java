public class reverse_string {

    public static void main(String[] args) {
        String str = "Test String";
        System.out.println("String: " + str);
        str = reverse_string(str);
        System.out.println("Reversed string: " + str);
    }

    public static String reverse_string(String str) {
        // Base case
        if (str.length() == 0) {
            return str;
        }

        // Recursive case
        return reverse_string(str.substring(1)) + str.charAt(0);
    }

}

/*
The base case is for an empty string, for which it will just return the empty string
The recursive case is to recurse for each substring of everything after the first character,
appending the first character to the end

Because the input of reverse_string is decreasing by one char with each call, it'll eventually
reach the base case of length 0 and stop recursing
And, it'll still work for an empty string as an edge case
 */

