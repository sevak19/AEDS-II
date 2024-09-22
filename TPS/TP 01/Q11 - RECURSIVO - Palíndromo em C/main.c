#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool palindromo(char palavra[], int esq, int dir);

int main()
{
    char palavra[350];
    
    scanf(" %[^\n]", palavra);
    
    while(strcmp(palavra,"FIM")!= 0){
        if(palindromo(palavra, 0, strlen(palavra) - 1)){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }
        scanf(" %[^\n]", palavra);
    }
    return 0;
}

bool palindromo(char palavra[], int esq, int dir){
    if (esq >= dir){
            return true;
    }
    if (palavra[esq] != palavra[dir]){
        return false;
    }
    return palindromo(palavra, esq + 1, dir - 1);
}