package smartresources;

import java.util.*;

public class Arraylist {
    public static void main(String[] args){
        ArrayList<String> countryList = new ArrayList<String>();
        
        // add method implementation
        countryList.add("america");
        countryList.add("australia");
        countryList.add("india");
        countryList.add("pakistan");
        countryList.add("bhutan");
        countryList.add("phillippines");
        countryList.add("russia");
        
        // iterator in arraylist
        System.out.println("Printing the country names using iterator: ");
        Iterator<String> itr = countryList.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println();
        
        // size function
        System.out.print("The size of the countryList is: "+countryList.size());
        System.out.println("\n");
        
        // get function
        System.out.println("The country at index location 2 is "+countryList.get(2));
        System.out.println("The country at index location 3 is "+countryList.get(3));
        System.out.println("The country at index location 5 is "+countryList.get(5));
        System.out.println();
        
        // remove function
        countryList.remove(0);
        System.out.println("countryList after removing the element at index 0: ");
        System.out.println(countryList.toString());
        System.out.println();
        
        // add function
        countryList.add("tanzania");
        System.out.println("countryList after adding a country: ");
        System.out.println(countryList.toString());
        System.out.println();
        
        // set function
        countryList.set(3,"madagascar");
        System.out.println("countryList after setting a country at the desired index location: ");
        System.out.println(countryList.toString());
        System.out.println();
        
        // indexOf function
        System.out.println("Index of 'madagascar': "+countryList.indexOf("madagascar"));
        System.out.println();
    }
}
