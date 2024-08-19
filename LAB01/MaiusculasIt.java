import java.util.Scanner;

//JAVA LAB01Q01 - Aquecimento Iterativo Palindromo

public class MaiusculasIt
{
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String entrada;
        
        int contador, tamanho;
        int ehFIM = 0;
        do{
            entrada = sc.nextLine();
            if (entrada.equals("FIM")){
                ehFIM = 1;
            }else{
                tamanho = entrada.length();
                contador = 0;
                for(int j = 0; j < tamanho; j++){
                    for(int i = 0; i < 26;i++){
                        if(entrada.charAt(j) == maiusculas.charAt(i)){
                            contador++;
                        }
                    }
                }
                System.out.printf("%d\n", contador);
            }
        }while(ehFIM == 0);
        
        sc.close();
	}
}
