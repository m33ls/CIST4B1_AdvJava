import java.util.PriorityQueue;
import java.util.Scanner;

class Patient implements Comparable<Patient> {
    public String name;
    public int severity;

    Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    @Override
    public int compareTo(Patient alt) {
        return this.severity - alt.severity;
    }
}

class Triage {
    public static void main(String[] args) {
        PriorityQueue<Patient> patients = new PriorityQueue<>();
        Scanner s = new Scanner(System.in);
        for (int i=0; i<10; 1++) {
            // Describe patient
            System.out.print("What is the patients name? ");
            String name = s.next();
            System.out.println();
            System.out.print("What is the severity of their symptoms? ");
            int severity = s.nextInt();
            System.out.println();
            patient = new Patient(name, severity);
            patients.add(patient);
        }
        // Output order
        System.out.println("Order:");
        for (Patient p : patients.toArray()) {
            System.out.print(p.name + " ");
        }
    }
}