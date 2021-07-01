
import java.util.HashMap;
import java.util.Scanner;

public class HashMapDemo {
    
    public static void main(String[] args) {
        // First create a hashmap with an initial size of 10 and
        // the default load factor
        HashMap < Integer, String > employees = new HashMap < Integer, String > (10);
        // Add several employees objects to the map using
        // their id as the key
        employees.put(10, "Joe");
        employees.put(49, "Andy");
        employees.put(91, "Greg");
        employees.put(70, "Kiki");
        employees.put(99, "Antoinette");
        System.out.print("Added Joe, Andy, Greg, Kiki, ");
        System.out.println("and Antoinette to the map.");
        // Output everything in the map
        System.out.println("The map contains:");
        for (Integer key: employees.keySet())
            System.out.println(key + " : " + employees.get(key));
        System.out.println();
        // Ask the user to type a name. If found in the map,
        // print it out.
        Scanner keyboard = new Scanner(System.in);
        int id;
        do {
            System.out.print("\nEnter an id to look up in the map. ");
            System.out.println("Enter -1 to quit.");
            id = keyboard.nextInt();
            if (employees.containsKey(id)) {
                String e = employees.get(id);
                System.out.println("ID found: " + e.toString());
            } else if (id != -1)
                System.out.println("ID not found.");
        } while (id != -1);
    }
}