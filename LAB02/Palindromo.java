public class Palindromo {
    public static void main(String[] args) {
        
        String palavra;
        int contador;
        palavra = MyIO.readLine();
        int tamanho;
        if(!palavra.equals("FIM")){
            do{
                contador = 0;
                tamanho = palavra.length();
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
                palavra = MyIO.readLine();
            }while(!palavra.equals("FIM"));
        }
    }
}
