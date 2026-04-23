import java.util.LinkedList;

public class ChainingHashTable {
    private LinkedList<String>[] table;
    private int capacity;

    public ChainingHashTable(int capacity) {
        table = new LinkedList[capacity];
        this.capacity = capacity;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void insert(String key, String value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
            table[index].add(value);
        } else if (!(table[index]).contains(value)) {
            table[index].add(value);
        }
    }

    public LinkedList<String> search(String key) {
        int index = hash(key);
        return table[index];
    }

    public void remove(String key) {
        int index = hash(key);

        if (table[index] != null) {
            if (table[index].size() > 1) {
                table[index].removeFirst();
            } else {
                table[index] = new LinkedList<>();
            }
        }
    }
}