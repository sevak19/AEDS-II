public class Palindromo {
    public static void main(String[] args) {
        
        String palavra;
        
        palavra = MyIO.readLine();
        
        while(!palavra.equals("FIM")){
            verificarPalindromo(palavra);
            palavra = MyIO.readLine();
        }
    }

    public static void verificarPalindromo (String palavra){
        int contador = 0;
        int tamanho = palavra.length();
        for(int i = 0;i < tamanho;i++){ 
            if(palavra.charAt(i) == palavra.charAt(tamanho - 1 - i)){
                contador++;
            }
        }
        if(contador == tamanho){
            MyIO.print("SIM\n");
        }else{
            MyIO.print("NAO\n");
        }
    }
}
