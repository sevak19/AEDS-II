#include <stdio.h>
#include <string.h>

char palavra[150];

void leitura(){
    scanf(" %[^\n]", palavra);
}

void palindromo(){
    
    int tamanho = strlen(palavra);
    int contador = 0;
    
    for(int i = 0; i < tamanho; i ++){
        
        if(palavra[i] == palavra[tamanho-i-1]){
            contador++;
        }
    }
    if(contador == tamanho){
        printf("SIM\n", palavra);
    }
    else{
        printf("NAO\n", palavra);
    }
}

int main()
{
    leitura();
    
    while(strcmp(palavra,"FIM")!= 0){
        palindromo();
        leitura();
    }

    return 0;
}
