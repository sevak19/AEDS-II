#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//LAB01Q04 - Aquecimento Recursivo em C

int metodo(int posicao, char entrada[], char maiusculas[])
{
    if(posicao >= strlen(entrada)){
        return 0;
    }
    int contador = metodo(posicao + 1, entrada, maiusculas);
    for (int i = 0; i < 26; i++){
        if (entrada[posicao] == maiusculas[i]){
            contador++;
            i = 26;
        }
    }
    return contador;
}

int main()
{
    char maiusculas[26] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char entrada[100];
    bool ehFIM = false;
    
    while (!ehFIM){
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") == 0){
            ehFIM = true;
        }else{
            int contador = metodo(0, entrada, maiusculas);
            printf("%d\n", contador);
        }
    }
    
    return 0;
}