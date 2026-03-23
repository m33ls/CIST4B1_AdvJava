import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class Interface {
    LinkedListQueue<Song> queue;
    LinkedListStack<Song> history;
    String base_dir = "Homework/MusicLibrary/Music/";
    String dir;

    Interface() {
        cd();
    }

    // Change Directory
    // O(1)
    private void cd(Strinnextg dir) {
        this.dir += dir + "/";
    }

    private void cd() {
        this.dir = base_dir;
    }

    // List
    // O(n)
    private void list_files() {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            stream
            .map(Path::getFileName)
            .map(Path::toString)
            .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get full file name from segment 
    // (Search through file list)
    // O(n)
    private String get_full(String segment) {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
            .filter(file -> !Files.isDirectory(file))
            .map(Path::getFileName)
            .map(Path::toString)
            .filter(name -> name.toLowerCase().contains(segment.toLowerCase()))
            .findFirst()
            .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Print Metadata
    // O(1)
    private void get_info(String path) {
        System.out.println("Info");
        System.out.println("Path: " + dir + path);
        Song song = new Song(Path.of( dir + path));
        song.printMetadata();
    }

    private void queue() {}
    private void play() { // 'play' and dequeue
    }
    private void previous() {}
    private void skip() { // dequeue
    }

    // TEST
    public static void main(String[] args) {
        Interface ui = new Interface();

        ui.list_files();
        ui.get_info("Common-People.mp3");
        ui.cd("MyPlaylist");
        ui.list_files();
        ui.get_info(ui.get_full("Good"));
        ui.cd();
        ui.cd("Crystal Castles - Crystal Castles");
        ui.list_files();
    }
}
