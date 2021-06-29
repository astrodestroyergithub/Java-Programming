package sortingtechniques;

import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        int n,temp;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of numbers in the array: ");
        n=sc.nextInt();
        int[] arr=new int[n];
        System.out.println("Enter "+n+" elements: ");
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        insertionSort(arr);
        //sorting array using insertion sort    
        System.out.println("After Insertion Sort");    
        for(int i:arr){    
            System.out.print(i+" ");    
        } 
    }
     public static void insertionSort(int array[]) {  
        int n = array.length;  
        for (int j = 1; j < n; j++) {  
            int key = array[j];  
            int i = j-1;  
            while ( (i > -1) && ( array [i] > key ) ) {  
                array [i+1] = array [i];  
                i--;  
            }  
            array[i+1] = key;  
        }  
    }  
}
