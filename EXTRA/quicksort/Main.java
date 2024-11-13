private void quicksort(int esq, int dir) {
    int i = esq, j = dir;
    int pivo = array[(dir+esq)/2];
    while (i <= j) {
        while (array[i] < pivo) i++;
        while (array[j] > pivo) j--;
        if (i <= j) {
            swap(i, j);
            i++;
            j--;
        }
    }
    if (esq < j)  quicksort(esq, j);
    if (i < dir)  quicksort(i, dir);
}

public void swap(int A, int B) {
    int tmp = array[B];
    array[B] = array[A];
    array[A] = tmp;
}