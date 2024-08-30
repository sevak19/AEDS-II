#include <stdio.h>
#include <stdbool.h>

int main () {
    int quantidade;
    char linha[256];
    
    scanf(" %d", &quantidade);
    
    float entrada[quantidade];
    float saida[quantidade];
    
    FILE *file;
    file = fopen("saida.txt", "w");
    
    for (int i = 0; i < quantidade; i++) {
        scanf(" %f", &entrada[i]);
        if(i == quantidade - 1){
            fprintf(file, "%g", entrada[i]);
        }else{
            fprintf(file, "%g\n", entrada[i]);
        }
    }
    
    fclose(file);
    
    file = fopen("saida.txt", "r");
    
    for (int i = 0; i < quantidade; i++){
        fgets(linha, sizeof(linha), file);
        sscanf(linha, "%f", &saida[i]);
    }
    
    fclose(file);
    
    for (int i = quantidade - 1; i >= 0; i--){
        if ((int)saida[i] == saida[i]) {
            printf("%.0f\n", saida[i]);
        } else {
            printf("%g\n", saida[i]);
        }
    }
    
    return 0;
}