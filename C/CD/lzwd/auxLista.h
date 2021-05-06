#ifndef LZWD_AUXLISTA_H
#define LZWD_AUXLISTA_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct lligada {
    char *Pattern;
    int len;
    unsigned int ind;
    struct lligada *next;
} *Lcod;

// filtra a lista para retirar os padrões que não tenhem o char c na possição nivel
Lcod filerLista ( char c,int nivel,Lcod l);

//gera a uma lista com os padrões começados em c e com o padrão do caracter c
Lcod filerListaCreate ( char c, char **cod,int maxlen);

//Determina o tamanho da lista l
int lenthLista (Lcod l);

#endif //LZWD_AUXLISTA_H


