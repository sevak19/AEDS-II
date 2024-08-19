#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//LAB01Q03 - Aquecimento Iterativo em C

int main()
{
    char maiusculas[26] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    char entrada[100];
    int contador, tamanho;
    bool ehFIM = false;
    
    scanf(" %[^\n]", entrada);
    if(strcmp(entrada, "FIM")){
        do{
            tamanho = strlen(entrada);
            contador = 0;
            for(int j = 0; j < tamanho; j++){
                for(int i = 0; i < 26;i++){
                    if(entrada[j] == maiusculas[i]){
                        contador++;
                    }
                }
            }
            printf("%i\n", contador);
                scanf(" %[^\n]", entrada);
            if (strcmp(entrada, "FIM") == 0){
                ehFIM = true;
            }
        }while(!ehFIM);
    }

    return 0;
}