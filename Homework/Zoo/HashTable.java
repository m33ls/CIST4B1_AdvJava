public class HashTable {
    private HTEntry[] table;
    private int size;
    private double load_factor; // size / table.length

    public HashTable(int capacity) {
        table = new HTEntry[capacity];
        size = 0;
        load_factor = 0;
    }

    // Resize when load factor is greater than 0.7
    // Create new array
    // Rehash every item from our old array to our new array
    public void resize(int newSize) {
        HTEntry[] old_table = table;
        table = new HTEntry[newSize];
        for (HTEntry entry : old_table) {
            if ( entry != null) {
                put(entry.key, entry.value);
            }
        }
        load_factor = (double) size / newSize;
    }

    private void put(String key, Object value) {
        // check that we have room to add
        if (load_factor >= 0.7) {
            resize(size * 2);
        }
        if (value.equals("DELETED")) {
            System.err.println("Don't enter DELETED");
            return;
        }
        // get the hash index
        int idx = hash(key);
        int idx2 = hash2(key);
        int i = 0;
        int start_idx = idx;

        // check index
        // loop until null or end (wrap), stop at start idx
        while (table[idx] != null) {
            // if we find key again, overwrite value at that position
            if (table[idx].key.equals(key)) {
                table[idx].value = value;
                return;
            }
            idx = (start_idx + i * idx2) % table.length;
            i++;
            if (idx == start_idx) {
                System.err.println("No empty slot");
                return;
            }
        }

        table[idx] = new HTEntry(key, value);
        size++;
        load_factor = (double) size / table.length;
    }

    // add
    public void put(String key, String species, int careLevel) {
        // check that we have room to add
        if (load_factor >= 0.7) {
            resize(table.length * 2);
        }
        /*if (value.equals("DELETED")) {
            System.err.println("Don't enter DELETED");
            return;
        }*/
        // get the hash index
        int idx = hash(key);
        //int idx2 = hash2(key);
        int i = 0;
        int start_idx = idx;

        // check index
        // loop until null or end (wrap), stop at start idx
        while (table[idx] != null && !table[idx].value.equals("DELETED")) {
            // if we find key again, overwrite value at that position
            if (table[idx].key.equals(key)) {
                table[idx].value = new Animal(species, key, careLevel);
                return;
            }
            idx = (idx + 1) % table.length; //(start_idx + i * idx2) % table.length;
            i++;
            if (idx == start_idx) {
                System.err.println("No empty slot");
            }
        }

        // insert into table, my new item
        table[idx] = new HTEntry(key, new Animal(species, key, careLevel));
        size++;
        load_factor = (double) size / table.length;
    }
    // lookup
        // get hash index based on key
        // loop while key not found and not null
        // if key found; ret val
        // if null; ret failed`
    public Animal get(String key) {
        int idx = hash(key);
        int start_idx = idx;

        while(table[idx].key != null) {
            if (!table[idx].value.equals("DELETED") && table[idx].key.equals(key)) {
                return (Animal) table[idx].value;
            }

            idx = (idx + 1) % table.length;

            if (idx == start_idx) {
                return null;
            }
        }
        return null;
    }

    // remove
    // search for the item to remove
    // when found, change value to "DELETED", break, decrement size
    public void remove(String key) {
        int idx = hash(key);
        int start_idx = idx;

        while(table[idx].key != null) {
            if (!table[idx].value.equals("DELETED") && table[idx].key.equals(key)) {
                table[idx].value = "DELETED";
                size--;
                load_factor = size / table.length;
                return;
            }

            idx = (idx + 1) % table.length;

            if (idx == start_idx) {
                break;
            }
        }
    }
    
    

    private int hash(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue += key.charAt(i);
        }
        return Math.abs(hashValue % table.length);
        // return key.hashCode() % table.length;
    }

    private int hash2(String key) {
        return 1 + Math.abs(key.hashCode() % table.length - 1);
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

class Animal {
    String name;
    String species;
    int careLevel;

    public Animal(String species, int careLevel) {
        this.species = species;
        this.careLevel = careLevel;
        this.name = "Unnamed";
    }

    public Animal(String species, String name, int careLevel) {
        this.species = species;
        this.careLevel = careLevel;
        this.name = name;
    }

    @Override
    public String toString() {
        return species + " " + name + " has a care level of " + careLevel + ".";
    }
}