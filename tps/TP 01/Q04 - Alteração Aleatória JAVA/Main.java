import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Random gerador = new Random();
        gerador.setSeed(4);
        
		String entrada = sc.nextLine();
		
		while(!entrada.equals("FIM")){
            char primeiro = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            char segundo = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
		    
		    String saida = alteracao(entrada, primeiro, segundo);
		    
		    System.out.println(saida);
		    
		    entrada = sc.nextLine();
		}
		
		sc.close();
	}
	
	public static String alteracao(String entrada, char primeiro, char segundo) {
	    
        int tamanho = entrada.length();
        
        char[] saida = new char[tamanho];
        
        for(int i = 0;i<tamanho;i++){
            char c = entrada.charAt(i);
            if(c == primeiro){
                saida[i] = segundo;
            } else {
                saida[i] = entrada.charAt(i);
            }
        }
        
        return new String(saida);
	}
}
