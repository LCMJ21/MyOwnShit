#include "auxiliar.h"

//Determina o numero de digitos de x
int tam_dig(int x){
    int cc = 0;
    while (x != 0){
        cc++;
        x/=10;
    }
    return cc;
}

int mystrcmp(char *Pattern1,char *Pattern2){
    int cc;
    for(cc = 0;cc < 8;cc++) if (Pattern1[cc] != Pattern2[cc]) return -1;
    return 1;
}

//Impime a informação final do programa
void print_final(long long n_blocks, clock_t time, int debug, char* filename){
    printf("Jorge Lima, a93183, MIEI/CD, 13-jan-2021\n");
    printf("Numero de blocos: %lld\n", n_blocks + 1);
    printf("Tempo de execucao do modulo (milissegundos): %ld\n", time);
    if (debug) printf("Debug: Sim\n");
    else printf("Debug: Nao\n");
    printf("Ficheiro gerado: %s\n",filename);
}

//junta os dois padrões PJ e PK com o tamanho tam e insere na posição disponivel
void merge(char **cod,int atual,char *PJ,char *PK, int tam){
    int cc1,cc2;
    cod[atual] = malloc(tam+1);

    for(cc1 = 0;PJ[cc1];cc1++) cod[atual][cc1] = PJ[cc1];
    for(cc2 = 0;PK[cc2];) cod[atual][cc1++] = PK[cc2++];
    cod[atual][tam] = '\0';
}

//Inicializa o array de padrões cod
char ** inicializaCod(int max){
    char **cod = malloc(max * sizeof(char *));
    int cc = 0;
    while (cc < max){
        cod[cc++] = NULL;
    }
    return cod;
}

//Duplica o array de padrões cod
char ** aumentaCod(char **cod,int atual,int max){
    cod = realloc(cod,max * sizeof(char *));
    for(;atual<max;atual++) cod[atual] = NULL;
    return cod;
}

//Encontra o proximo padrão no buffer
Lcod findP (char *bufferEnt,int tam,int atual,char **cod,int maxlen){
    Lcod new = filerListaCreate (bufferEnt[atual++],cod,maxlen);
    int cc = 1,len = new->len;

    while (lenthLista(new) != 1 && cc < len && atual < tam){
        new = filerLista (bufferEnt[atual++],cc++,new);
        len = new->len;
    }

    return new;
}

