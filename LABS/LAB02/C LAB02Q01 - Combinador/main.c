#include <stdio.h>
#include <string.h>

// LAB02Q01 - Combinador

int main() {
    char entrada1[100], entrada2[100];
    int tamanho1, tamanho2;
    
    while (scanf("%s %s", entrada1, entrada2) != EOF) {
        char saida[200];
        int j = 0;
        
        tamanho1 = strlen(entrada1);
        tamanho2 = strlen(entrada2);
        
        for(int i = 0; i < tamanho1 && i < tamanho2; i++){
            saida[j++] = entrada1[i];
            saida[j++] = entrada2[i];
        }
        if(tamanho1 > tamanho2){
            for(int i = tamanho2; i < tamanho1; i++){
                saida[j++] = entrada1[i];
            }
        }else if(tamanho2 > tamanho1){
            for(int i = tamanho1; i < tamanho2; i++){
                saida[j++] = entrada2[i];
            }
        }
        
        saida[j] = '\0';
        printf("%s\n", saida);
    }
    return 0;
}