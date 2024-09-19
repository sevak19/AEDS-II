#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

int main () {
    char dicionario[200];
    char entrada[200];
    while(scanf(" %s", dicionario) != EOF && scanf(" %[^\n]", entrada) != EOF) {
        int qnt = 0;
        int tamanhoDIC = strlen(dicionario);
        int tamanhoENT = strlen(entrada);
        for ( int i = 0; i < tamanhoENT; i++ ) {
            for(int j = 0; j < tamanhoDIC; j++) {
                if(entrada[i] == dicionario[j]) {
                    qnt++;
                    j = tamanhoDIC;
                }
            }
        }
        printf("%d\n", qnt);
    }
}