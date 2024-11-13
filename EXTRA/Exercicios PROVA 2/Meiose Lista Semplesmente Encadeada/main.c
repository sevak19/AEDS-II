#include <stddef.h>

typedef struct Celula {
    int elemento;
    struct Celula* prox;
} Celula;

Celula* inicio;
Celula* fim;

void Meiose(){
    Celula* aux = inicio;
    while(aux != NULL) {
        Celula *newCelula;
        newCelula = (Celula *)malloc(sizeof(Celula));

        newCelula->elemento = (aux->elemento / 2);
        aux->elemento = (aux->elemento / 2);

        newCelula->prox = aux->prox;
        aux->prox = newCelula;

        aux = newCelula->prox;

        if(aux == fim) {
            fim = newCelula;
        }
    }
}