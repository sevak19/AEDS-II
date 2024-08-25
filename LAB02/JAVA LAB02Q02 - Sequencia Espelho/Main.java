import java.util.*;

public class Main {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        
        int num1, num2, num3;
        
        while (sc.hasNextInt()){
            
            num1 = sc.nextInt();
            num2 = sc.nextInt();
            
            List<Character> numeros = new ArrayList<>();
            List<Character> numerosEsp = new ArrayList<>();
            
            for(int i = 0;i <= Math.abs(num1-num2); i++){
                num3 = (num1 + i);
                String numStr = Integer.toString(num3);
                for (char ch : numStr.toCharArray()) {
                    numeros.add(ch);
                    System.out.printf("%c", ch);
                }
            }
            
            for(int i = numeros.size() - 1; i >= 0; i--)
                numerosEsp.add(numeros.get(i));
            
            for(char num : numerosEsp)
                System.out.printf("%c", num);
            
            System.out.println();
        }
        sc.close();
    }
}