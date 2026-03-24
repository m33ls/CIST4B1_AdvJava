import java.util.ArrayList;

class Songs {
    ArrayList<Song> songs;
    String title;
    Songs() {
        songs = new ArrayList<>();
        title = "Unknown";
    }

    public void add (Song song) {
        songs.add(song);
    }
    
    // Quicksort by song name
    public void SortSongName() {
        if (songs.size () > 1) {
            quickSort(0, songs.size() -1);
        }
    }

    // recursive method
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex -1);
            quickSort(pivotIndex +1, high);
        }
    }

    private int partition(int low, int high) {
        String pivot = songs.get(high).Songname;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (songs.get(j).Songname.compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(i,j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Song temp = songs.get(i);
        songs.set(i, songs.get(j));
        songs.set(j, temp);
    }
}

class Album extends Songs {
    Album() {
        super();
    }

    public void setTitle() {
        if (songs.size() > 0) {
            title = songs.get(0).Album;
        }
    }
}
class Playlist extends Songs {
    Playlist() {
        super();
    }

    public void setTitle() {
        if (songs.size() > 0) {
            title = songs.get(0).path.toString();
        }
    }
}

