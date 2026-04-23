import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Song {
    private static final Map<Integer, String> GENRE_MAP = new HashMap<>();
    
    // Only [0-20] for the sake of brevity
    static {
        GENRE_MAP.put(0, "Blues");
        GENRE_MAP.put(1, "Classic Rock");
        GENRE_MAP.put(2, "Country");
        GENRE_MAP.put(3, "Dance");
        GENRE_MAP.put(4, "Disco");
        GENRE_MAP.put(5, "Funk");
        GENRE_MAP.put(6, "Grunge");
        GENRE_MAP.put(7, "Hip-Hop");
        GENRE_MAP.put(8, "Jazz");
        GENRE_MAP.put(9, "Metal");
        GENRE_MAP.put(10, "New Age");
        GENRE_MAP.put(11, "Oldies");
        GENRE_MAP.put(12, "Other");
        GENRE_MAP.put(13, "Pop");
        GENRE_MAP.put(14, "R&B");
        GENRE_MAP.put(15, "Rap");
        GENRE_MAP.put(16, "Reggae");
        GENRE_MAP.put(17, "Rock");
        GENRE_MAP.put(18, "Techno");
        GENRE_MAP.put(19, "Industrial");
        GENRE_MAP.put(20, "Alternative");
        // . . .
    }
    
    Path path;

    // ID3v1 Tags       Length  Offsets
    String Songname; // 30      3-32
    String Artist;   // 30      33-62
    String Album;    // 30      63-92
    String Year;     // 4       93-96
    String Comment;  // 30      97-126
    int genre_code;  // 1       127
    String Genre;    // Map from table
    // ID3v2 Breaks this implementation as its more complex,
    // but this saves us from importing a library

    Song(Path p) {
        path = p;
        populateMetadata();
    }

    private void populateMetadata() {
        // Read last 128 bytes of file and parse
        /* 
        FORMAT
        Field      Length    Offsets
        Tag        3         0-2      <- Tells us if metadata
        Songname   30        3-32     <- Strings, format
        Artist     30        33-62
        Album      30        63-92
        Year       4         93-96
        Comment    30        97-126
        Genre      1         127      <- Int to map to list
        */

        try (RandomAccessFile file = new RandomAccessFile(path.toFile(), "r")) {
            if (file.length() > 128) {
                file.seek(file.length() - 128);

                byte[] buffer = new byte[128];
                file.readFully(buffer);

                String tag = readString(buffer, 0, 3);
                if (tag.equals("TAG")) {
                    Songname = readString(buffer, 3, 30);
                    Artist   = readString(buffer, 33, 30);
                    Album    = readString(buffer, 63, 30);
                    Year     = readString(buffer, 93, 4);
                    Comment  = readString(buffer, 97, 30);
                    genre_code = buffer[127] & 0xFF;
                    Genre = mapGenre(this.genre_code);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bytes to String
    private String readString(byte[] buffer, int offset, int length) {
        return new String(buffer, offset, length, StandardCharsets.ISO_8859_1)
            .replaceAll("\u0000", "")
            .trim();
    }

    // Genre code to readable genre via hash map
    private String mapGenre(int genre_code) {
        return GENRE_MAP.getOrDefault(genre_code, "Unknown");
    }

    public void printMetadata() {
        System.out.println("Song Name: " + Songname);
        System.out.println("Artist: " + Artist);
        System.out.println("Album: " + Album);
        System.out.println("Year: " + Year);
        System.out.println("Comment: " + Comment);
        System.out.println("Genre: " + Genre);
    }
}