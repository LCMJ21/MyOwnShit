#ifndef LZWD_AUXILIAR_H
#define LZWD_AUXILIAR_H

#include <time.h>
#include "auxLista.h"

#define MAX_FILE_NAME 50
#define INICIAL_BUFOUT_SIZE 1000000
#define INICIAL_BUFDEBUG_SIZE 10000000
#define MAX_ARRAY_COD 1000000

typedef struct lligada *Hashtable[256];

//Determina o numero de digitos de x
int tam_dig(int x);

//junta os dois padrões PJ e PK com o tamanho tam e insere na posição disponivel
void merge( char **cod,int atual, char *PJ, char *PK, int tam);

//Inicializa o array de padrões cod
char ** inicializaCod(int max);

//Duplica o array de padrões cod
char ** aumentaCod(char **cod,int atual,int max);

//Impime a informação final do programa
void print_final(long long n_blocks, clock_t time, int debug, char* filename);

//Encontra o proximo padrão no buffer
Lcod findP ( char *bufferEnt,int tam,int atual, char **cod,int maxlen);

#endif //LZWD_AUXILIAR_H
