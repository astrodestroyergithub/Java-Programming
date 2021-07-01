package sortingtechniques;

import java.util.Scanner;

public class SelectionSort {
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
        selectionSort(arr);  
        //sorting array using selection sort 
        System.out.println("After Selection Sort");  
        for(int i:arr){  
            System.out.print(i+" ");  
        }  
    }
    public static void selectionSort(int[] arr){  
        for (int i = 0; i < arr.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < arr.length; j++){  
                if (arr[j] < arr[index]){  
                    index = j;//searching for lowest index  
                }  
            }  
            int smallerNumber = arr[index];   
            arr[index] = arr[i];  
            arr[i] = smallerNumber;  
        }  
    }  
}
