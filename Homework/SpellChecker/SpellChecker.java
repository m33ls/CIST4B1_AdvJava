
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpellChecker {

    public static void main(String[] args) {

        AVLTree dictionary = new AVLTree();

        String filename = "words.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                String cleaned = line.toLowerCase().replaceAll("[^a-z]", "");
                if (!cleaned.isEmpty()) {
                    dictionary.insert(cleaned);
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // The Bell Jar by Sylvia Plath; Chapter 11, Page 128
        String document = "I saw the dayz of da year stretching ahead like uh series of bright, white boxes, and sepaating one box from another was sleep, like a blck shade. Only for me, the long pespective of shades that set off one box from the next day hadsuddenly snapped up, and I could see day ater day after day glaring ahead of me like a whte, broad, infinitely dsolate avenue.";

        WordSet misspelled = new WordSet();

        String[] words = document.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");

        for (String word : words) {
            if (!dictionary.search(word)) {
                misspelled.add(word);
            }
        }

        // Output
        System.out.println("AVL Tree (Inorder with Balance)");
        dictionary.printTree();

        System.out.println();
        misspelled.print();
    }
}

class WordSet {

    String[] words = new String[100];
    int size = 0;

    boolean contains(String word) {
        for (int i = 0; i < size; i++) {
            if (words[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    void add(String word) {
        if (!contains(word)) {
            words[size++] = word;
        }
    }

    void print() {
        // Bubble Sort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (words[j].compareTo(words[j + 1]) > 0) {
                    String temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }

        System.out.println("Misspelled Words:");
        for (int i = 0; i < size; i++) {
            System.out.println(words[i]);
        }
    }
}
