#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main() {
    int N;

    // O(N) - O laço continua enquanto houver entradas
    while (scanf("%d", &N) != EOF) {
        int gridLargada[N];
        int posicaoInicial[N];

        // O(N) - Leitura e atribuição da posição inicial de cada competidor
        for(int i = 0; i < N ; i++) {
            scanf("%d", &gridLargada[i]);
            // Cada competidor do grid é mapeado para sua posição inicial
            posicaoInicial[gridLargada[i]] = i;
        }

        int ordemChegada[N];

        // O(N) - Leitura da ordem de chegada
        for(int i = 0; i < N ; i++) {
            scanf("%d", &ordemChegada[i]);
        }

        int ultrapassagens = 0;

        // O(N^2) - Duplo laço aninhado para calcular ultrapassagens
        // Este laço compara todos os pares (i, j) onde j > i
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                // Verificação de ultrapassagem: se a posição inicial do competidor
                // em ordemChegada[i] for maior que a posição do competidor em ordemChegada[j],
                // houve uma ultrapassagem
                if(posicaoInicial[ordemChegada[i]] > posicaoInicial[ordemChegada[j]]) {
                    ultrapassagens++;
                }
            }
        }
        
        // O(1) - Exibição do número de ultrapassagens
        printf("%d\n", ultrapassagens);
    }

    return 0;
}