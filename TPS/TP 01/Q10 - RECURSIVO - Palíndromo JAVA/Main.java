import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String palavra;
        
        palavra = sc.nextLine();
        
        while(!palavra.equals("FIM")){
            if (verificarPalindromo(palavra, 0, palavra.length() - 1)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            palavra = sc.nextLine();
        }
        sc.close();
    }

    public static boolean verificarPalindromo (String palavra, int esq, int dir){
        if (esq >= dir) {
            return true;
        }
        if (palavra.charAt(esq) != palavra.charAt(dir)) {
            return false;
        }
        return verificarPalindromo(palavra, esq + 1, dir - 1);
    }
}