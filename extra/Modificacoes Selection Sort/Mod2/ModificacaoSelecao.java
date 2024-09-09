import java.util.*;

public class ModificacaoSelecao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Insira o tamanho do array: ");
        int n = sc.nextInt();

        int[] array = new int[n];
        
        for(int i = 0; i < n;i++){
            System.out.printf("Insira o %d elemento do array: ", i+1);
            array[i] = sc.nextInt();
        }

        int negativos = 0;

        for(int i = 0; i < n;i++){
            if(array[i] < 0){
                swap(array, i, negativos);
                negativos++;
            }else{
                int menor = i;
                for(int j = (i+1+negativos);j < n;j++){
                    if(array[menor] > array[j] && array[j] > -1 ){
                        menor = j;
                    }
                }
                swap(array, menor, i);
            }
        }
        
        System.out.println("Array ordenado(selection sort) com os negativos no inicio na mesma ordem: ");
        for(int i = 0; i < n;i++){
            System.out.println(array[i]);
        }

        sc.close();
    }
    public static void swap (int[] array, int A, int B){
        int temp = array[A];
        array[A] = array[B];
        array[B] = temp;
    }
}