import java.util.*;

public class NocoesComplexidade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] array = new int[n];
        
        for(int i = 0; i < n;i++){
            array[i] = sc.nextInt();
        }

        int mov = 0;

        for(int i = 0; i < (n-1);i++){
            int menor = i;
            for(int j = (i+1);j < n;j++){
                if(array[menor] > array[j]){
                    menor = j;
                }
            }

            int temp = array[menor];
            array[menor] = array[i];
            array[i] = temp;

            mov+=3;
        }

        System.out.println("Teoria: " + (3*n-3));
        System.out.println("Prática: " + mov);
        
        System.out.println("Array ordenado(selection sort): ");
        for(int i = 0; i < n;i++){
            System.out.println(array[i]);
        }

        sc.close();
    }
}