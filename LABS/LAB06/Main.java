import java.util.*;

public class Main {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = new int[10];

        for(int i = 0; i < 10; i++) {
            array[i] = sc.nextInt();
        }

        quicksort(0, 9, array);

        for(int numero : array) {
            System.out.println(numero);
        }

        sc.close();
    }

    public static void quicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[(dir+esq)/2];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)  quicksort(esq, j, array);
        if (i < dir)  quicksort(i, dir, array);
    }

    public static void swap (int A, int B, int[] array) {
        int aux = array[A];
        array[A] = array[B];
        array[B] = aux;
    }
}

