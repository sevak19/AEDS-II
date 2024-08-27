#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void alteracao(const char entrada[], char primeiro, char segundo, char saida[], int i);

int main()
{
    srand(4);
    
    char entrada[350];
    
    scanf(" %[^\n]", entrada);
    
    while (strcmp(entrada, "FIM") != 0) {
        char primeiro = 'a' + (rand() % 26);
        char segundo = 'a' + (rand() % 26);
        
        char saida[350];
        
        alteracao(entrada, primeiro, segundo, saida, 0);
        
        printf("%s\n", saida);
        
        scanf(" %[^\n]", entrada);
    }
    
    return 0;
}

void alteracao(const char entrada[], char primeiro, char segundo, char saida[], int i)
{
    if(i >= strlen(entrada)){
        saida[i] = '\0';
        return;
    }
    
    char c = entrada[i];
    
    if (c == primeiro) {
        saida[i] = segundo;
    } else {
        saida[i] = entrada[i];
    }

    alteracao(entrada, primeiro, segundo, saida, i + 1);
}
