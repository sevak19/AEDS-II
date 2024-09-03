import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ancora;
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] entrada = new int[N];
        int[] modulos = new int[N];
        int[] ordenados = new int[N];

        for(int i = 0;i< N; i++){
            entrada[i] = sc.nextInt();
            modulos[i] = entrada[i] % M;
        }
        for(int i = 1;i< N; i++){
            ancora = modulos[i];
            if(ancora > modulos[i-1]){
                ancora = modulos[i-1];
            } else {
                ordenados[i] = entrada[i];
            }
        }
        for(int numero : ordenados){
            System.out.println(numero);
        }

        sc.close();
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