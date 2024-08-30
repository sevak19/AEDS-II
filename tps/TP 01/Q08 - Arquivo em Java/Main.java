import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try{
            int quantidade = sc.nextInt();
            
            RandomAccessFile file = new RandomAccessFile("valores.txt", "rw");
            
            for(int i = 0; i < quantidade; i++){
                double valor = sc.nextDouble();
                file.writeDouble(valor);
            }
            
            file.close();
            
            file = new RandomAccessFile("valores.txt", "r");
            
            long tamanho = file.length();
            int qntNum = (int) (tamanho / 8);
            
            for(int i = 0; i < qntNum; i++){
                long posicao = tamanho-(i + 1)*8;
                file.seek(posicao);
                double valor = file.readDouble();
                
                if(valor % 1 == 0){
                    System.out.println((int)valor);
                }else{
                    System.out.println(valor);
                }
            }
            
            file.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        sc.close();
    }
}
