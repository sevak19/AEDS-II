public class Insertion {
    public static void main(String[] args){

        int[] array = {1, 7, 9, 10, 6, 3};

        int n = array.length;

        for(int i = 1;i<n;i++){
            int chave = array[i];
            int j = pesqBinaria(array, chave, 0, i);
            int k = i;
            while(k > j){
                array[k] = array[k-1];
                k--;
            }
            array[j] = chave;
        }

        for(int i = 0;i<n;i++){
            System.out.println(array[i]);
        }
    }
    public static int pesqBinaria(int[] array, int chave, int esq, int dir){
        while(esq < dir){
            int meio = (esq+dir)/2;
            if(chave > array[meio]){
                esq = meio + 1;
            }else{
                dir = meio;
            }
        }
        return esq;
    }
}