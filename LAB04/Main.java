import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int valor;
        int modulo;

        while(M != 0 && N != 00){
            Numeros[] entrada = new Numeros[N];
    
            for (int i = 0; i < N; i++) {
                entrada[i] = new Numeros();
            }
    
            for (int i = 0; i < N; i++) {
                valor = sc.nextInt();
                modulo = valor % M;
                entrada[i].setNumero(valor);
                entrada[i].setModulo(modulo);
            }
    
            for (int i = 1; i < N; i++) {
                Numeros chave = entrada[i];
                int j = i - 1;
    
                while (j >= 0 && deveMover(entrada[j], chave)) {
                    entrada[j + 1] = entrada[j];
                    j--;
                }
                entrada[j + 1] = chave;
            }
            
            System.out.println(N + " " + M);
    
            for(int i = 0; i < N; i++) {
                System.out.println(entrada[i].getNumero());
            }
            N = sc.nextInt();
            M = sc.nextInt();
        }

        System.out.println(N + " " + M);

        sc.close();
    }

    private static boolean deveMover(Numeros anterior, Numeros chave) {
        if (anterior.getModulo() > chave.getModulo()) {
            return true;
        }else if (anterior.getModulo() == chave.getModulo()) {
            if (anterior.getNumero() % 2 != 0 && chave.getNumero() % 2 != 0) {
                if(anterior.getNumero() < chave.getNumero()){
                    return anterior.getNumero() < chave.getNumero();
                }
            }else if (anterior.getNumero() % 2 == 0 && chave.getNumero() % 2 == 0) {
                if(anterior.getNumero() > chave.getNumero()){
                    return anterior.getNumero() > chave.getNumero();
                }
            }else if(anterior.getNumero() % 2 != 0 && chave.getNumero() % 2 == 0) {
                return false;
            }else if(anterior.getNumero() % 2 == 0 && chave.getNumero() % 2 != 0){
                return true;
            }
        }
        return false;
    }
}

class Numeros {
    protected int numero;
    protected int modulo;

    public Numeros() {
        this.numero = 0;
        this.modulo = 0;
    }
    public Numeros(int numero, int modulo) {
        this.numero = numero;
        this.modulo = modulo;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getModulo() {
        return modulo;
    }
    public void setModulo(int modulo) {
        this.modulo = modulo;
    }
}