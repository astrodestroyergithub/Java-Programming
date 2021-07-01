package sortingtechniques;

import java.util.Scanner;

public class BubbleSort {
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
        for(int i=0; i < n; i++)
        {  
            for(int j=1; j < (n-i); j++){  
                if(arr[j-1] > arr[j])
                {  
                    //swap elements  
                    temp = arr[j-1];  
                    arr[j-1] = arr[j];  
                    arr[j] = temp;  
                }  
                          
            }  
        }
        System.out.println("The sorted array is: ");
        for(int i=0;i<n;i++)
        {
            System.out.print(arr[i]+" ");
        }
    }
    
}
