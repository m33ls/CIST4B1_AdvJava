import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

class Interface {
    LinkedListQueue<Song> queue;
    LinkedListStack<Song> history;
    String base_dir = "Music/";
    String dir;

    Interface() {
        queue = new LinkedListQueue<>();
        history = new LinkedListStack<>();
        cd(null);
    }

    // Change Directory
    // O(1)
    private void cd(String dir) {
        if (dir == null || this.dir == null) {
            this.dir = base_dir;
        } else {
            String path = get_full(dir);
            this.dir = Paths.get(this.dir).resolve(path).toString() + "/";
        }
    }

    // Print help text
    private void help() {
        System.out.println("Available commands include: \ncd (relative directory); change directory, partial names will be autocompleted\nls; list files\nqueue | q; enqueue folder or song, names will be autocompleted\nplay; play song\nskip | s; skip song\nprevious | prev | p; play previous song\ninfo (relative file); display metadata of file\ncurrent; display current metadata\nhelp | h; display this dialogue");
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
    private void get_info(Song song) {
        System.out.println("Info");
        song.printMetadata();
    }

    // Queue song, album, or playlist
    // O(1)
    private void queue(String path) {
        path = get_full(path);
        
        if (path != null) {
            File file = Paths.get(dir).resolve(path).toFile();
            if (file.exists() && file.isDirectory()) {
                // Check if album or playlist
                // Iterate over all files to check if song.album same
                // Create new Songs
                // Sort
                // Queue all in songs
                Songs dir = new Songs();
                File[] files = file.listFiles();

                for(File f : files) {
                    if (f.isFile() && f.getName().toLowerCase().endsWith(".mp3")) {
                        dir.add(new Song(f.toPath()));
                    }
                }

                dir.SortSongName();
                for (Song s : dir.songs) {
                    queue.enqueue(s);
                    System.out.println("Queued: " + s.Songname);
                }
            } else if (file.exists()) {
                Song song = new Song(Path.of(dir + path));
                queue.enqueue(song);
                System.out.println("Queued: " + song.Songname);
            } else {
                System.out.println("File does not exist.");
            }
        }
    }

    // Play, dequeue, push to history
    // O(1)
    private void play() { 
        Song song = queue.dequeue();
        if (song == null) {
            System.out.println("No queued songs.");
        } else {
            System.out.println("Now playing " + song.Songname + " by " + song.Artist);
            history.push(song);
        }
    }

    // Go back one song
    // O(1)
    private void previous() {
        Song song = history.pop();
        if (song == null) {
            System.out.println("No previous songs.");
        } else {
            System.out.println("Now playing " + song.Songname + " by " + song.Artist);
            // history.push(song);
        }
    }

    // Skip forward one song, without playing
    // O(1)
    private void skip() { // dequeue
        Song song = queue.dequeue();
        System.out.println("Skipped " + song.Songname + " by " + song.Artist);
        history.push(song);
    }

    public static void main(String[] args) {
        Interface ui = new Interface();

        /*
        // Test Inputs
        ui.list_files();
        ui.get_info("Common-People.mp3");
        ui.cd("MyPlaylist");
        ui.list_files();
        ui.get_info(ui.get_full("Good"));
        ui.cd();
        ui.play();
        ui.queue("Crystal Castles - Crystal Castles");
        ui.previous();
        ui.play();
        ui.play();
        ui.previous();
        ui.previous();
        */

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("                      __ __                            ______");       
        System.out.println(".---.-.--------.-----|  |__.---.-.-----.--------.-----|__    .-----.");
        System.out.println("|  _  |        |  -__|  |  |  _  |__ --|        |  _  |__    |__ --|");
        System.out.println("|___._|__|__|__|_____|__|__|___._|_____|__|__|__|   __|______|_____|");
        System.out.println("                                                |__|                ");
        System.out.println("                             Type 'help' for options, 'exit' to quit");
        while (true) {
            System.out.print("$ ");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye :(");
                break;
            }

            String[] inputs = input.split(" ", 2);
            String function = inputs[0];
            String arguments;
            if (inputs.length > 1) {
                arguments = inputs[1];
            } else {
                arguments = "";
            }

            switch (function.toLowerCase()) {
                case "cd" -> {
                    if (!arguments.isEmpty()) {
                        ui.cd(arguments);
                    } else {
                        ui.cd(null);
                    }
                }
                case "info" -> {
                    if (!arguments.isEmpty()) {
                        ui.get_info(ui.get_full(arguments));
                    }
                }
                case "queue" -> {
                    if (!arguments.isEmpty()) {
                        ui.queue(arguments);
                    }
                }
                case "q" -> {
                    if (!arguments.isEmpty()) {
                        ui.queue(arguments);
                    }
                }
                case "ls"       -> ui.list_files();
                case "current"  -> ui.get_info(ui.queue.peek());
                case "play"     -> ui.play();
                case "previous" -> ui.previous();
                case "prev"     -> ui.previous();
                case "p"        -> ui.previous();
                case "skip"     -> ui.skip();
                case "next"     -> ui.skip();
                case "s"        -> ui.skip();
                case "help"     -> ui.help();
                case "h"        -> ui.help();
                default         -> System.out.println("Unknown command. Typo? \nEnter 'help' for help.");
            }
        }
    }
    
}
