import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String entrada = sc.nextLine();
		
		while(!entrada.equals("FIM")) {
        	String saida = Ciframento(entrada);
        	
        	System.out.println(saida);
        	
        	entrada = sc.nextLine();
		}
		sc.close();
	}
	
	public static String Ciframento(String entrada){
	    int tamanho = entrada.length();
	    char[] saida = new char[tamanho];
	    for(int i = 0; i < tamanho; i++){
	        char c = entrada.charAt(i);
	        if(c >= 0 && c <= 127){
	            saida[i] = (char)(c + 3);
	        }else{
	            saida[i] = c;
	        }
	    }
	    
	    return new String(saida);
	}
}