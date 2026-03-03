public class char_count {

    public static void main(String[] args) {
        System.out.println("Count: " + count_occurences("banana", 'a'));
        System.out.println("Count: " + count_occurences("recursion", 'r'));
        System.out.println("Count: " + count_occurences("hello", 'z'));
        System.out.println("Count: " + count_occurences("", 'a'));
    }

    public static int count_occurences(String str, char ch) {
        // Base case
        if (str.length() == 0) {
            return 0;
        }

        // Recursive case
        int count = 0;
        // Check current
        if (str.charAt(0) == ch) {
            count++;
        }
        
        // Recurse over each substring, ignoring the first char
        count += count_occurences(str.substring(1), ch);

        return count;
    }
}

/*
The base case of this function is a string of length 0, as it decreases the length
by one with a substring with each call.
The recursive case is to then check the first index and call the function again, 
this time over the substring of str[1, n].
And, the count is incremented each time the function sees the specified char.
*/