#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <stdbool.h>
#include <err.h>

#define NUM_POKEMONS 801

typedef struct {
    int ano;
    int mes;
    int dia;
} LocalDate;

typedef struct {
    int id;
    int generation;
    char name[50];
    char description[500];
    char types[2][50];
    char abilities[10][50];
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    LocalDate captureDate;
} Pokemon;

typedef struct Celula {
	Pokemon elemento;
	struct Celula* prox;
} Celula;

typedef struct {
    Celula* topo;
} Pilha;

void ler(Pokemon pokedex[NUM_POKEMONS]);
void imprimir(Pokemon* pokemon);
Pokemon clone(Pokemon* pokemon);

void start(Pilha* pilha);
Celula* novaCelula(Pokemon elemento);
void inserir(Pokemon x, Pilha* pilha);
Pokemon remover(Pilha* pilha);
void mostrar (Pilha* pilha);
void mostrarRecursivo(Celula* i, int contador);
int contarElementos(Celula* i);


int getId(Pokemon* pokemon);
void setId(Pokemon* pokemon, int id);

int getGeneration(Pokemon* pokemon);
void setGeneration(Pokemon* pokemon, int generation);

char* getName(Pokemon* pokemon);
void setName(Pokemon* pokemon, char nome[]);

char* getDescription(Pokemon* pokemon);
void setDescription(Pokemon* pokemon, char descricao[]);

char* getType(Pokemon* pokemon, int i);
void setType(Pokemon* pokemon, int i, char tipo[]);

char* getAbility(Pokemon* pokemon, int i);
void setAbility(Pokemon* pokemon, int i, char habilidade[]);

double getWeight(Pokemon* pokemon);
void setWeight(Pokemon* pokemon, double weight);

double getHeight(Pokemon* pokemon);
void setHeight(Pokemon* pokemon, double height);

int getCaptureRate(Pokemon* pokemon);
void setCaptureRate(Pokemon* pokemon, int captureRate);

bool isLegendary(Pokemon* pokemon);
void setLegendary(Pokemon* pokemon, bool isLegendary);

LocalDate getCaptureDate(Pokemon* pokemon);
void setCaptureDate(Pokemon* pokemon, LocalDate captureDate);

int main () {
    
    Pokemon pokedex[NUM_POKEMONS];

    ler(pokedex);

    Pilha pilha;
    start(&pilha);


    char line[3];
    scanf(" %[^\n]", line);
    while(strcmp(line, "FIM") != 0) {
        int num = strtol(line, NULL, 10);
        inserir(clone(&pokedex[num-1]), &pilha);
        scanf(" %[^\n]", line);
    }

    int N;
    scanf(" %d", &N);

    for (int i = 0; i < N; i++) {
        char linha[100];
        scanf(" %[^\n]", linha);

        char array[3][3];
        int aux = 0;

        char* token = strtok(linha, " ");
        while (token != NULL && aux < 3) {
            strcpy(array[aux++], token);
            token = strtok(NULL, " ");
        }

        char type[2];
        strcpy(type, array[0]);

        int number = atoi(array[1]);

        if (strcmp(type, "I") == 0) {
            inserir(clone(&pokedex[number - 1]), &pilha);
        } else if (strcmp(type, "R") == 0) {
            Pokemon removido = remover(&pilha);
            printf("(R) %s\n", getName(&removido));
        }
    }

    mostrar(&pilha);

    return 0;
}


void start(Pilha* pilha){
    pilha->topo = NULL;
}
Celula* novaCelula(Pokemon elemento) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}
void inserir(Pokemon x, Pilha* pilha) {
    Celula* tmp = novaCelula(x);
    tmp->prox = pilha->topo;
    pilha->topo = tmp;
    tmp = NULL;
}
Pokemon remover(Pilha* pilha) {
    if (pilha->topo == NULL) {
        errx(1, "Erro ao remover!");
    }

    Pokemon resp = pilha->topo->elemento;
    Celula* tmp = pilha->topo;
    pilha->topo = pilha->topo->prox;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}
void mostrar (Pilha* pilha){
    int totalElementos = contarElementos(pilha->topo);
    mostrarRecursivo(pilha->topo, totalElementos-1);
}
int contarElementos(Celula* i) {
    if (i == NULL) return 0;
    return 1 + contarElementos(i->prox);
}
void mostrarRecursivo(Celula* i, int contador) {
    if (i == NULL) return;

    mostrarRecursivo(i->prox, contador - 1);

    printf("[%d] ", contador);

    imprimir(&i->elemento);
}

void ler(Pokemon pokedex[NUM_POKEMONS]) {
    FILE *file = fopen("/tmp/pokemon.csv", "r");

    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        exit(1);
    }

    char line[256];
    fgets(line, sizeof(line), file);
    int counter = 0;
    while (fgets(line, sizeof(line), file) != NULL) {
        int id, generation, captureRate, dia, mes, ano;
        char name[50] = "", description[50] = "NULL";
        char types[2][50], abilities[10][50];
        char auxiliar[10];
        double weight, height;
        bool isLegendary;
        strcpy(auxiliar, "NULL");
        for (int i = 0; i < 2; i++) {
            strcpy(types[i], "NULL");
        }
        for (int i = 0; i < 10; i++) {
            strcpy(abilities[i], "NULL");
        }
        if(counter < 9) {
            id = line[0] - '0';
            generation = line[2] - '0';
            int aux = 4, i = 0;
            while(line[aux] != ',') {
                name[i] = line[aux];
                i++, aux++;
            }
            i = 0;
            aux++;
            while(line[aux] != ',') {
                description[i] = line[aux];
                i++, aux++;
            }
            i = 0;
            aux++;
            while(line[aux] != ',') {
                types[0][i] = line[aux];
                i++, aux++;
            }
            types[0][i] = '\0';
            i = 0;
            aux++;
            while(line[aux] != ',') {
                types[1][i] = line[aux];
                i++, aux++;
            }
            types[1][i] = '\0';
            int j = 0;
            aux++;
            while(line[aux] != ']') {
                while(line[aux] == '"' || line[aux] == '[' || line[aux] == '\'') {
                    aux++;
                }
                i = 0;
                while(line[aux] != '\'') {
                    abilities[j][i] = line[aux];
                    i++, aux++;
                }
                abilities[j][i] = '\0';
                j++, aux++;
                while (line[aux] == ',' || line[aux] == ' ' || line[aux] == '\'') {
                    aux++;
                }
            }
            aux += 3;
            i = 0;
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            weight = strtod(auxiliar, NULL);
            aux ++;
            i = 0;
            strcpy(auxiliar, "NULL");
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            height = strtod(auxiliar, NULL);
            aux ++;
            i = 0;
            strcpy(auxiliar, "NULL");
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            captureRate = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            if(line[aux] == '1') {
                isLegendary = true;
            } else {
                isLegendary = false;
            }
            aux += 2;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 2; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            dia = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 2; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            mes = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 4; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            ano = (int) strtol(auxiliar, NULL, 10);
        }else if(counter < 99) {
            id = (line[0] - '0') * 10 + (line[1] - '0');
            generation = line[3] - '0';
            int aux = 5, i = 0;
            while(line[aux] != ',') {
                name[i] = line[aux];
                i++, aux++;
            }
            i = 0;
            aux++;
            while(line[aux] != ',') {
                description[i] = line[aux];
                i++, aux++;
            }
            i = 0;
            aux++;
            while(line[aux] != ',') {
                types[0][i] = line[aux];
                i++, aux++;
            }
            types[0][i] = '\0';
            i = 0;
            aux++;
            while(line[aux] != ',') {
                types[1][i] = line[aux];
                i++, aux++;
            }
            types[1][i] = '\0';
            int j = 0;
            aux++;
            while(line[aux] != ']'){
                while(line[aux] == '"' || line[aux] == '[' || line[aux] == '\'') {
                    aux++;
                }
                i = 0;
                while(line[aux] != '\'') {
                    abilities[j][i] = line[aux];
                    i++, aux++;
                }
                abilities[j][i] = '\0';
                j++, aux++;
                while (line[aux] == ',' || line[aux] == ' ' || line[aux] == '\'') {
                    aux++;
                }
            }
            aux += 3;
            i = 0;
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            weight = strtod(auxiliar, NULL);
            aux ++;
            i = 0;
            strcpy(auxiliar, "NULL");
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            height = strtod(auxiliar, NULL);
            aux ++;
            i = 0;
            strcpy(auxiliar, "NULL");
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            captureRate = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            if(line[aux] == '1') {
                isLegendary = true;
            } else {
                isLegendary = false;
            }
            aux += 2;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 2; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            dia = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 2; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            mes = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 4; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            ano = (int) strtol(auxiliar, NULL, 10);
        }else {
            id = (line[0] - '0') * 100 + (line[1] - '0') * 10 + (line[2] - '0');
            generation = line[4] - '0';
            int aux = 6, i = 0;
            while(line[aux] != ',') {
                name[i] = line[aux];
                i++, aux++;
            }
            i = 0;
            aux++;
            while(line[aux] != ',') {
                description[i] = line[aux];
                i++, aux++;
            }
            i = 0;
            aux++;
            while(line[aux] != ',') {
                types[0][i] = line[aux];
                i++, aux++;
            }
            types[0][i] = '\0';
            i = 0;
            aux++;
            while(line[aux] != ',') {
                types[1][i] = line[aux];
                i++, aux++;
            }
            types[1][i] = '\0';
            int j = 0;
            aux++;
            while(line[aux] != ']') {
                while(line[aux] == '"' || line[aux] == '[' || line[aux] == '\'') {
                    aux++;
                }
                i = 0;
                while(line[aux] != '\'') {
                    abilities[j][i] = line[aux];
                    i++, aux++;
                }
                abilities[j][i] = '\0';
                j++, aux++;
                while (line[aux] == ',' || line[aux] == ' ' || line[aux] == '\'') {
                    aux++;
                }
            }
            aux += 3;
            i = 0;
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            weight = strtod(auxiliar, NULL);
            aux ++;
            i = 0;
            strcpy(auxiliar, "NULL");
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            height = strtod(auxiliar, NULL);
            aux ++;
            i = 0;
            strcpy(auxiliar, "NULL");
            while(line[aux] != ',') {
                auxiliar[i] = line[aux];
                i++, aux++;
            }
            auxiliar[i] = '\0';
            captureRate = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            if(line[aux] == '1') {
                isLegendary = true;
            } else {
                isLegendary = false;
            }
            aux += 2;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 2; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            dia = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 2; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            mes = (int) strtol(auxiliar, NULL, 10);
            aux ++;
            strcpy(auxiliar, "NULL");
            for( i = 0; i < 4; i++ ) {
                auxiliar[i] = line[aux];
                aux++;
            }
            auxiliar[i+1] = '\0';
            ano = (int) strtol(auxiliar, NULL, 10);
        }
        setId(&pokedex[counter], id);
        setGeneration(&pokedex[counter], generation);
        setName(&pokedex[counter], name);
        setDescription(&pokedex[counter], description);
        setType(&pokedex[counter], 0, types[0]);
        setType(&pokedex[counter], 1, types[1]);
        int i =0;
        while(strcmp(abilities[i], "NULL") != 0) {
            setAbility(&pokedex[counter], i, abilities[i]);
            i++;
        }
        setAbility(&pokedex[counter], i, "");
        setWeight(&pokedex[counter], weight);
        setHeight(&pokedex[counter], height);
        setCaptureRate(&pokedex[counter], captureRate);
        setLegendary(&pokedex[counter], isLegendary);
        LocalDate captureDate;
        captureDate.dia = dia;
        captureDate.mes = mes;
        captureDate.ano = ano;
        setCaptureDate(&pokedex[counter], captureDate);

        counter++;
    }

    int fclose(FILE *file);
}

void imprimir(Pokemon* pokemon) {
    printf("[#%d -> ", getId(pokemon));
    printf("%s: ", getName(pokemon));
    printf("%s - ", getDescription(pokemon));
    if (strlen(getType(pokemon, 1)) > 0) {
        printf("['%s', '%s'] - [", getType(pokemon, 0), getType(pokemon, 1));
    } else {
        printf("['%s'] - [", getType(pokemon, 0));
    }
    for (int i = 0; i < 10 && strlen(getAbility(pokemon, i)) > 0; i++) {
        printf("'%s'", getAbility(pokemon, i));
        if (i < 9 && strlen(getAbility(pokemon, i + 1)) > 0) {
            printf(", ");
        }
    }
    printf("] - %.1fkg - ", getWeight(pokemon));
    printf("%.1fm - ", getHeight(pokemon));
    printf("%d%% - ", getCaptureRate(pokemon));
    printf("%s - ", isLegendary(pokemon) ? "true" : "false");
    printf("%d gen] - ", getGeneration(pokemon));
    LocalDate captureDate = getCaptureDate(pokemon);
    printf("%02d/%02d/%d\n", captureDate.dia, captureDate.mes, captureDate.ano);
}

Pokemon clone(Pokemon* pokemon) {
    Pokemon newPokemon;

    setId(&newPokemon, getId(pokemon));
    setGeneration(&newPokemon, getGeneration(pokemon));
    setName(&newPokemon, getName(pokemon));
    setDescription(&newPokemon, getDescription(pokemon));
    for (int i = 0; i < 2; i++) {
        setType(&newPokemon, i, getType(pokemon, i));
    }
    for (int i = 0; i < 10; i++) {
        setAbility(&newPokemon, i, getAbility(pokemon, i));
    }
    setWeight(&newPokemon, getWeight(pokemon));
    setHeight(&newPokemon, getHeight(pokemon));
    setCaptureRate(&newPokemon, getCaptureRate(pokemon));
    setLegendary(&newPokemon, isLegendary(pokemon));
    setCaptureDate(&newPokemon, getCaptureDate(pokemon));

    return newPokemon;
}

int getId(Pokemon* pokemon) {
    return pokemon->id;
}
void setId(Pokemon* pokemon, int id) {
    pokemon->id = id;
}


int getGeneration(Pokemon* pokemon) {
    return pokemon->generation;
}
void setGeneration(Pokemon* pokemon, int generation) {
    pokemon->generation = generation;
}


char* getName(Pokemon* pokemon) {
    return pokemon->name;
}
void setName(Pokemon* pokemon, char nome[]) {
    strncpy(pokemon->name, nome, sizeof(pokemon->name) - 1);
    pokemon->name[sizeof(pokemon->name) - 1] = '\0';
}


char* getDescription(Pokemon* pokemon) {
    return pokemon->description;
}
void setDescription(Pokemon* pokemon, char descricao[]) {
    strncpy(pokemon->description, descricao, sizeof(pokemon->description) - 1);
    pokemon->description[sizeof(pokemon->description) - 1] = '\0';
}


char* getType(Pokemon* pokemon, int i) {
    return pokemon->types[i];
}
void setType(Pokemon* pokemon, int i, char tipo[]) {
    strncpy(pokemon->types[i], tipo, sizeof(pokemon->types[i]) - 1);
    pokemon->types[i][sizeof(pokemon->types[i]) - 1] = '\0';
}


char* getAbility(Pokemon* pokemon, int i) {
    return pokemon->abilities[i];
}
void setAbility(Pokemon* pokemon, int i, char habilidade[]) {
    strncpy(pokemon->abilities[i], habilidade, sizeof(pokemon->abilities[i]) - 1);
    pokemon->abilities[i][sizeof(pokemon->abilities[i]) - 1] = '\0';
}


double getWeight(Pokemon* pokemon) {
    return pokemon->weight;
}
void setWeight(Pokemon* pokemon, double weight) {
    pokemon->weight = weight;
}


double getHeight(Pokemon* pokemon) {
    return pokemon->height;
}
void setHeight(Pokemon* pokemon, double height) {
    pokemon->height = height;
}


int getCaptureRate(Pokemon* pokemon) {
    return pokemon->captureRate;
}
void setCaptureRate(Pokemon* pokemon, int captureRate) {
    pokemon->captureRate = captureRate;
}


bool isLegendary(Pokemon* pokemon) {
    return pokemon->isLegendary;
}
void setLegendary(Pokemon* pokemon, bool isLegendary) {
    pokemon->isLegendary = isLegendary;
}


LocalDate getCaptureDate(Pokemon* pokemon) {
    return pokemon->captureDate;
}
void setCaptureDate(Pokemon* pokemon, LocalDate captureDate) {
    pokemon->captureDate = captureDate;
}