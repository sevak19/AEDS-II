import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		while(!entrada.equals("FIM")){
		    processamento(entrada);
		    entrada = sc.nextLine();
		}
		sc.close();
	}
	
	public static void processamento(String entrada){
	    int parenteses = 0, controle = 0;
	    int tamanho = entrada.length();
	    for(int i = 0; i < tamanho;i++){
	        if(entrada.charAt(i) == '('){
	            parenteses++;
	            controle = 1;
	        }else if(entrada.charAt(i) == ')' && controle == 1){
	            parenteses--;
	        }
	    }
	    if(parenteses == 0 && controle == 1){
	        System.out.println("correto");
	    }else{
	        System.out.println("incorreto");
	    }
	}
}