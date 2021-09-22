import java.util.*;
import java.lang.*;

public class CapitalizeText {

	public static void main(String[] args) {    
        
        String str1;    
        Scanner sc=new Scanner(System.in);
        str1=sc.nextLine();
        
        StringBuffer newStr=new StringBuffer(str1);
            
        for(int i = 0; i < str1.length(); i++) {    
                
            if(Character.isLowerCase(str1.charAt(i))) {   
                newStr.setCharAt(i, Character.toUpperCase(str1.charAt(i)));    
            }   
        }    
        System.out.println("String after case conversion : " + newStr);    
    }    
}     
