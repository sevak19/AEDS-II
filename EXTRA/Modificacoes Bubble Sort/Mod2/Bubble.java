public class Bubble {
    public static void main(String[] args){

        int[] array = {1, 7, 9, 10, 6, 3};

        int n = array.length;

        int teto = n-1;
        int aux = 0;

        for (int i = 0; i < n-1; i++) {
            boolean trocou = false;
			for (int j = 0; j < teto; j++) {
				if (array[j] > array[j + 1]) {
                    swap(array, j, j+1);
                    trocou = true;
                    aux = j;
				}
			}
            teto = aux;
            if(trocou == false){
                i = n;
            }
		}

        for(int i = 0;i<n;i++){
            System.out.println(array[i]);
        }

    }

    public static void swap (int[] array, int A, int B){
        int temp = array[A];
        array[A] = array[B];
        array[B] = temp;
    }
}