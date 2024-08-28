import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String entrada = sc.nextLine();
		
		while(!entrada.equals("FIM")){
		    if(vogais(entrada)){
		        System.out.printf("SIM ");
		    }else{
		        System.out.printf("NAO ");
		    }
		    if(consoantes(entrada)){
		        System.out.printf("SIM ");
		    }else{
		        System.out.printf("NAO ");
		    }
		    if(inteiro(entrada)){
		        System.out.printf("SIM ");
		    }else{
		        System.out.printf("NAO ");
		    }
		    if(real(entrada)){
		        System.out.printf("SIM");
		    }else{
		        System.out.printf("NAO");
		    }
		    System.out.println("");
		    entrada = sc.nextLine();
		}
		
		sc.close();
	}
	
	public static boolean vogais(String entrada){
	    String vogais = "aeiouAEIOU";
	    for(int i = 0;i<entrada.length();i++){
	        char c = entrada.charAt(i);
	        if(vogais.indexOf(c) == -1){
	            return false;
	        }
	    }
	   return true;
	}
	
	public static boolean consoantes(String entrada){
	    String consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
	    for(int i = 0;i<entrada.length();i++){
	        char c = entrada.charAt(i);
	        if(consoantes.indexOf(c) == -1){
	            return false;
	        }
	    }
	   return true;
	}
	
	public static boolean inteiro(String entrada){
	    try{
	        Integer.parseInt(entrada);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public static boolean real(String entrada){
	    try{
	        Float.parseFloat(entrada);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
}