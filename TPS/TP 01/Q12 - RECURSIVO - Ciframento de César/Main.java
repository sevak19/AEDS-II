import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String entrada = sc.nextLine();
		
		while(!entrada.equals("FIM")){
        	String saida = Ciframento(entrada, 0);
        	
        	System.out.println(saida);
        	
        	entrada = sc.nextLine();
		}
		sc.close();
	}
	
	public static String Ciframento(String entrada, int i){
	    if(i >= entrada.length()){
	         return "";
        }
        
        char c = entrada.charAt(i);
        char cC;
        
        if(c >= 0 && c <= 127){
            cC = (char)(c + 3);
        }else{
            cC = c;
        }
        
        return cC + Ciframento(entrada, i + 1);
	    }
}