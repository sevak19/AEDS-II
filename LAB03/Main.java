import java.util.*;

public class Main {
    public static void main(String[] args) {
        Geracao algoritmo;

        int n = (args.length < 1) ? 1000 : Integer.parseInt(args[0]);

        double inicio,fim;

        algoritmo = new Selecao(n);

        algoritmo.aleatorio();

        inicio = algoritmo.now();
        algoritmo.sort();
        fim = algoritmo.now();

        System.out.println("Tempo para ordenar:" + (fim-inicio)/1000.0 + "s.");
        System.out.println("isOrdenado:" + algoritmo.isOrdenado());
    }
}