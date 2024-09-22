import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        int entrada = MyIO.readInt();

        if(entrada==5){
            System.out.println("deu bom");
        }else{
            System.out.println("deu ruim");
        }
    }
    // COMANDOS PARA USAR ARQUIVO COMO ENTRADA
    // cd LAB01
    // javac Main.java
    // Get-content teste.txt | java Main
}
