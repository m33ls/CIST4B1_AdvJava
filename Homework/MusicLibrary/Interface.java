import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class Interface {
    Queue<Song> queue;
    String base_dir = "Homework/MusicLibrary/Music/";
    String dir;

    Interface() {
        cd();
    }

    // Change Directory
    private void cd(String dir) {
        this.dir += dir + "/";
    }

    private void cd() {
        this.dir = base_dir;
    }

    // List
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

    // Print Metadata
    private void get_info(String path) {
        System.out.println("Info");
        System.out.println("Path: " + dir + path);
        Song song = new Song(Path.of( dir + path));
        song.printMetadata();
    }

    // TEST
    public static void main(String[] args) {
        Interface ui = new Interface();

        ui.list_files();
        ui.get_info("Common-People.mp3");
        ui.cd("MyPlaylist");
        ui.list_files();
        ui.get_info("19 - Good Ol' Days.mp3");
        ui.cd();
        ui.cd("Crystal Castles - Crystal Castles");
        ui.list_files();
    }
}
