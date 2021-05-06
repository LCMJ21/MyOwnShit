#ifndef G1_LI3_HASH_H
#define G1_LI3_HASH_H

#define MAX 10

typedef struct lligada {
    char key[50];
    int value;
    struct lligada* next;
} *lista;

typedef struct {
    lista tbl[MAX];
} HT;

int hash(char key[], int size);

void initHT(HT *h, int size);

int writeHT(HT *h, char key[], int value);

char *maxHT(HT *h, char key[], int value);

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#endif //G1_LI3_HASH_H
