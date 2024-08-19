import java.util.Scanner;

//JAVA LAB01Q02 - Aquecimento Recursivo

public class MaiusculasRec
{
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String entrada;
        int ehFIM = 0;
    
        while (ehFIM == 0){
            entrada = sc.nextLine();
            if(entrada.equals("FIM")){
                ehFIM = 1;
            }else{
                int contador = metodo(0, entrada, maiusculas);
                System.out.printf("%d\n", contador);
            }
        }
        sc.close();
	}
	
	public static int metodo(int posicao, String entrada, String maiusculas){
        if(posicao >= entrada.length()){
            return 0;
        }
        int contador = metodo(posicao + 1, entrada, maiusculas);
        for (int i = 0; i < 26; i++){
            if (entrada.charAt(posicao) == maiusculas.charAt(i)){
                contador++;
                i = 26;
            }
        }
        return contador;
    }
}