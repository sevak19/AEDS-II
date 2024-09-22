import java.util.*;

public class Main
{
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String entrada = sc.nextLine();
		
		if(!entrada.equals("FIM")){
		    saidas(entrada);
		}
		
		sc.close();
	}
	
	public static void saidas(String entrada){
        
        if(vogais(entrada, 0)){
            System.out.printf("SIM ");
        }else{
            System.out.printf("NAO ");
        }
        if(consoantes(entrada, 0)){
            System.out.printf("SIM ");
        }else{
            System.out.printf("NAO ");
        }
		if(inteiro(entrada, 0)){
            System.out.printf("SIM ");
        }else{
            System.out.printf("NAO ");
        }
        if(real(entrada, 0)){
            System.out.printf("SIM");
        }else{
            System.out.printf("NAO");
        }
        
        System.out.println("");
        
        entrada = sc.nextLine();
        
        if(!entrada.equals("FIM")){
            saidas(entrada);
		}
	}
	
    public static boolean vogais(String entrada, int i){
        if(i >= entrada.length()){
            return true;
        }
        String vogais = "aeiouAEIOU";
        
        char c = entrada.charAt(i);
        
        if(vogais.indexOf(c) == -1){
            return false;
        }
        
        return true && vogais(entrada, i +1);
	}
	
	public static boolean consoantes(String entrada, int i){
        if(i >= entrada.length()){
            return true;
        }
        String consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        
        char c = entrada.charAt(i);
        
        if(consoantes.indexOf(c) == -1){
            return false;
        }
        
        return true && consoantes(entrada, i + 1);
	}
	
	public static boolean inteiro(String entrada, int i){
        try{
            Integer.parseInt(entrada);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
	}
	
	public static boolean real(String entrada, int i) {
	    try{
	        Double.parseDouble(entrada);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
}