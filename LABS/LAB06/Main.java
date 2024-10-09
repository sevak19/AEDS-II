import java.util.*;

public class Main {
    static public void main(String[] args) {
        
        int[] sizes = {100, 1000, 10000};
        String[] methods = {"FirstPivot", "LastPivot", "RandomPivot", "MedianOfThree"};
        String[] arrayTypes = {"Ordered", "NearlyOrdered", "Random"};

        for (int size : sizes) {
            int[] array = null;
            for(String type : arrayTypes) {
                if(type.equals("Ordered")){
                    array = generateNearlyOrderedArray(size);
                } else if (type.equals("NearlyOrdered")){
                    array = generateNearlyOrderedArray(size);
                } else {
                    array = generateRandomArray(size);
                }
                for (String method : methods) {
                    int[] arrayToSort = Arrays.copyOf(array, array.length);
                    long time = SortTime(arrayToSort, method);
                    System.out.printf("    %s: %d ns \t \t (Array Size - %s) (Array Type - %s)\n", method, time, size, type);
                }
            }
            System.out.println();
        }
    }

    public static void FirstPivotQuicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[esq];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)  FirstPivotQuicksort(esq, j, array);
        if (i < dir)  FirstPivotQuicksort(i, dir, array);
    }

    public static void LastPivotQuicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[dir];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)  LastPivotQuicksort(esq, j, array);
        if (i < dir)  LastPivotQuicksort(i, dir, array);
    }

    public static void RandomPivotQuicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[esq + (int)(Math.random() * (dir - esq + 1))];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)  RandomPivotQuicksort(esq, j, array);
        if (i < dir)  RandomPivotQuicksort(i, dir, array);
    }

    public static void MedianOfThreQuicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[ MedianOfThre ( (esq), ((esq+dir)/2), (dir),  array ) ];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j, array);
                i++;
                j--;
            }
        }
        if (esq < j)  MedianOfThreQuicksort(esq, j, array);
        if (i < dir)  MedianOfThreQuicksort(i, dir, array);
    }
    public static int MedianOfThre(int a, int b, int c, int[] array) {
        int A = array[a];
        int B = array[b];
        int C = array[c];
        
        if ((A > B && A < C) || (A > C && A < B)) {
            return a;
        } else if ((B > A && B < C) || (B > C && B < A)) {
            return b;
        } else {
            return c;
        }
    }

    public static void swap (int A, int B, int[] array) {
        int aux = array[A];
        array[A] = array[B];
        array[B] = aux;
    }

    public static int[] generateOrderedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateNearlyOrderedArray(int size) {
        int[] array = generateOrderedArray(size);
        Random rand = new Random();
        for (int i = 0; i < size/10; i++) {
            int index1 = rand.nextInt(size);
            int index2 = rand.nextInt(size);
            swap(index1, index2, array);
        }
        return array;
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++)
            array[i] = rand.nextInt(size * 10);
        return array;
    }

    public static long SortTime(int[] array, String method) {
        long startTime = System.nanoTime();
        if(method.equals("FirstPivot")){
            FirstPivotQuicksort(0, array.length - 1, array);
        }else if(method.equals("LastPivot")){
            LastPivotQuicksort(0, array.length - 1, array);
        }else if(method.equals("RandomPivot")){
            RandomPivotQuicksort(0, array.length - 1, array);
        }else{
            MedianOfThreQuicksort(0, array.length - 1, array);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}