/*
 *              __       __          ___             __       
 * .-----.-----|__.-----|  |_.-----.'  _.-----.---.-|  .-----.
 * |  _  |  _  |  |     |   _|  _  |   _|__ --|  _  |  |  -__|
 * |   __|_____|__|__|__|____|_____|__| |_____|___._|__|_____|
 * |__|                                                       
 *                        (POS, which stands for nothing else)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

class SalesRecords {
    public static void main(String[] args) throws Exception{
        // Open file
        Path path = Path.of("data.csv");

        int rows = 100;
        int tests = 1;

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (int i = 0; i < tests; i++) {
                gen_data(bw, rows);
            }
        }

        try (BufferedReader br = Files.newBufferedReader(path)) {
            Random rand = new Random();
            for (int i = 0; i < tests; i++) {
                int id = rand.nextInt(1,rows);
                search_id(br, id);
            }
            for (int i = 0; i < tests; i++) {
                check_duplicates(br);
            }
            for (int i = 0; i < tests; i++) {
                retrieve_latest(br);
            }
            for (int i = 0; i < tests; i++) {
                compute_revenue(br);
            }

        }
    }

    public static void gen_data(BufferedWriter bw, int n) throws Exception {
        long start_t = System.nanoTime(); 

        bw.write("sale_id,sale_date,amount,product\n");
        
        Random rand = new Random();
        String[] items = {"Doohickey", "Thingamajig", "Whatsit", "Gadget"};

        // Loop n times, appending lines of random data
        // Not expected to be efficient, as it's generating lots of random numbers
        // This is on purpose
        for (int i = 1; i <= n; i++) {
            bw.append(i + "," + rand.nextInt(1969,2026) + "-" + rand.nextInt(1,12) + "-" + rand.nextInt(1,28) + "," + (int) ((Math.random() * 9000) + 100) / 100.0 + "," + items[rand.nextInt(0,3)] + "\n");
        }

        long end_t = System.nanoTime();
        System.out.println("Wrote " + n + " items in " + (end_t - start_t) + "ns.");
    }

    public static void search_id(BufferedReader br, int id) {
        //System.out.println("Searching for id " + id);
        long start_t = System.nanoTime();

        // Binary Search

        long end_t = System.nanoTime();
        System.out.println("Found id " + id + " at ROW " + " in " + (end_t - start_t) + "ns.");
    }

    public static void check_duplicates(BufferedReader br) {
        long start_t = System.nanoTime();

        // Check duplicates

        long end_t = System.nanoTime();
        System.out.println("Checked duplicates in " + (end_t - start_t) + "ns.");
    }

    public static void retrieve_latest(BufferedReader br) {
        long start_t = System.nanoTime();

        // Retrieve latest

        long end_t = System.nanoTime();
        System.out.println("Retrieved latest in " + (end_t - start_t) + "ns.");
    }

    public static void compute_revenue(BufferedReader br) {
        long start_t = System.nanoTime();

        // Compute revenue

        long end_t = System.nanoTime();
        System.out.println("Computed reveneue in " + (end_t - start_t) + "ns.");
    }
}

/* Writeup
 * 
 */