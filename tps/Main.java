import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        
        int num1, num2;
        List<Character> numeros = new ArrayList<>();
        List<Character> numerosEsp = new ArrayList<>();

        num1 = sc.nextInt();
        num2 = sc.nextInt();
        
        for(int i = 0;i <= Math.abs(num1-num2); i++){
            char ch = (char) (num1 + i);
            numeros.add(ch);
            System.out.printf("%c", ch);
        }
        for (int i = numeros.size() - 1; i >= 0; i--)
            numerosEsp.add(numeros.get(i));
        
        for (int num : numerosEsp)
            System.out.printf("%c", num);

        sc.close();
    }
}