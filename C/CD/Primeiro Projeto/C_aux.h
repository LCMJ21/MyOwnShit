#ifndef CD_C_AUX_H
#define CD_C_AUX_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define NUM_CHA 256

#define MAX_COD 8
#define MAX_FILE_NAME 50
#define EMPTY_8 "00000000\0"

typedef struct{
    char arrayCod[8][NUM_CHA];
    int index[8];
    int next[8];
}codigo;

void printador(int nblocos,int tamantes[],int tamdps[],clock_t tempo,char *filename);
int leTamanhos(char* tam,FILE* file,int arrobas);
void myprint(FILE *file, char* resultado,int tam);
void mystrcpy (char *dest,char *org);
void preencheOff(char* cod,int tam,codigo *codigos);
void or (char* byte,char* cod);

#endif //CD_C_AUX_H
