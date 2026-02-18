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
import java.text.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class SalesRecords {
    public static void main(String[] args) throws Exception{
        // Open file
        Path path = Path.of("data.csv");

        int rows = 100;
        int tests = 5;

        
        for (int i = 0; i < tests; i++) {
            try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                gen_data(bw, rows);
            }
        }
        
        Random rand = new Random();
        for (int i = 0; i < tests; i++) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                int id = rand.nextInt(1,rows);
                search_id(br, id);
            }
        }
        for (int i = 0; i < tests; i++) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                check_duplicates(br);
            }
        }
        for (int i = 0; i < tests; i++) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                retrieve_latest(br);
            }
        }
        for (int i = 0; i < tests; i++) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                compute_revenue(br);
            }
        }

        
    }

    public static void gen_data(BufferedWriter bw, int n) throws Exception {
        long start_t = System.nanoTime(); 

        bw.write("sale_id,sale_date,amount,product\n");
        
        Random rand = new Random();
        DecimalFormat df = new DecimalFormat("###,###.##");
        String[] items = {"Doohickey", "Thingamajig", "Whatsit", "Gadget"};

        // Loop n times, appending lines of random data
        // Not expected to be efficient, as it's generating lots of random numbers
        // This is on purpose
        
        for (int i = 1; i <= n; i++) {
            bw.append(i + "," + rand.nextInt(1969,2026) + "-" + rand.nextInt(1,12) + "-" + rand.nextInt(1,28) + "," + df.format(Math.random() * 100) + "," + items[rand.nextInt(0,3)] + "\n");
        }

        // Add duplicate to test duplicate check
        bw.append(rand.nextInt(1,n) + "," + rand.nextInt(1969,2026) + "-" + rand.nextInt(1,12) + "-" + rand.nextInt(1,28) + "," + df.format(Math.random() * 100) + "," + items[rand.nextInt(0,3)] + "\n");

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

    public static Set<Integer> check_duplicates(BufferedReader br) throws Exception {
        long start_t = System.nanoTime();

        // Check duplicates
        // Trying something new with HashSets, because it should be faster than
        // e.g. making an array and checking over and over

        Set<Integer> duplicates = new HashSet<>();
        Set<Integer> seen = new HashSet<>();

        String headers = br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            int id = Integer.parseInt(cols[0]);
            if (!seen.add(id)) {
                duplicates.add(id);
                System.out.println("Duplicate: " + id);
            }
        }

        long end_t = System.nanoTime();
        System.out.println("Checked duplicates in " + (end_t - start_t) + "ns.");
        return duplicates;
    }

    public static void retrieve_latest(BufferedReader br) throws Exception{
        long start_t = System.nanoTime();

        // Retrieve latest by date
        String headers = br.readLine();
        double total = 0;

        String line;
        
        String latest = "";
        String[] latest_date = {"0","0","0"};

        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            String[] date = cols[1].split("-");
            if (Integer.parseInt(date[0]) > Integer.parseInt(latest_date[0])) {
                latest_date[0] = date[0];
                latest_date[1] = date[1];
                latest_date[2] = date[2];
                latest = line;   
            } else if (Integer.parseInt(date[0]) == Integer.parseInt(latest_date[0]) && Integer.parseInt(date[1]) > Integer.parseInt(latest_date[1])) {
                latest_date[0] = date[0];
                latest_date[1] = date[1];
                latest_date[2] = date[2];
                latest = line;
            } else if (Integer.parseInt(date[0]) == Integer.parseInt(latest_date[0]) && Integer.parseInt(date[1]) == Integer.parseInt(latest_date[1]) && Integer.parseInt(date[2]) > Integer.parseInt(latest_date[2])) {
                latest_date[0] = date[0];
                latest_date[1] = date[1];
                latest_date[2] = date[2];
                latest = line;
            }
            
        }

        long end_t = System.nanoTime();
        String[] latest_cols = latest.split(",");
        System.out.println("Retrieved latest [ ID: " + latest_cols[0] + ", Date: " + latest_cols[1] + ", Cost: $" + latest_cols[2] + ", Item: " + latest_cols[3] + " ] in " + (end_t - start_t) + "ns.");
    }

    public static double compute_revenue(BufferedReader br) throws Exception {
        long start_t = System.nanoTime();

        // Compute revenue
        String headers = br.readLine();
        double total = 0;

        String line;
        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            double amount = Double.parseDouble(cols[2]);
            total += amount;
        }

        DecimalFormat df = new DecimalFormat("###,###.##");

        long end_t = System.nanoTime();
        System.out.println("Computed revenue of $" + df.format(total) + " in " + (end_t - start_t) + "ns.");
        return total;
    }
}

/* Writeup
 * 
 */