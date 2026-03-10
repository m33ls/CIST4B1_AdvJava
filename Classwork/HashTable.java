public class HashTable {
    private HTEntry[] table;
    private int size;

    public HashTable(int capacity) {
        table = new HTEntry[capacity];
        size = 0;
    }

    // add
    public void put(String key, Object value) {
        // check that we have room to add
        if (size == table.length) {
            System.err.println("HashTable full.");
            return;
        }
        if (value.equals("DELETED")) {
            System.err.println("Don't enter DELETED");
            return;
        }
        // get the hash index
        int idx = hash(key);
        int start_idx = idx;

        // check index
        // loop until null or end (wrap), stop at start idx
        while (table[idx] != null && !table[idx].equals("DELETED")) {
            // if we find key again, overwrite value at that position
            if (table[idx].key.equals(key)) {
                table[idx].value = value;
                return;
            }
            idx = (idx + 1) % table.length;
            if (idx == start_idx) {
                System.err.println("No empty slot");
            }
        }

        // insert into table, my new item
        table[idx] = new HTEntry(key, value);
        size++;
    }
    // lookup
        // get hash index based on key
        // loop while key not found and not null
        // if key found; ret val
        // if null; ret failed
    // remove

    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue += key.charAt(i);
        }
        return Math.abs(hashValue % table.length);
        // return key.hashCode() % table.length;
    }
}

class HTEntry {
    String key;
    Object value;

    public HTEntry(String k, Object v) {
        key = k;
        value = v;
    }
}