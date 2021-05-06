#ifndef G1_LI3_FILES_H
#define G1_LI3_FILES_H

#define MAX_STRING 50
#define MAX_STRING2 500
#define INIT_STRUCT_SIZE 100

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "hash.h"

typedef struct user{
    char string[MAX_STRING];
} *Users;

typedef struct business{
    char string1[MAX_STRING];
    char string2[MAX_STRING];
    char string3[MAX_STRING];
    double x;
    int y;
} *Businesses;

typedef struct review{
    char string1[MAX_STRING];
    char string2[MAX_STRING];
    char string3[MAX_STRING];
    double x;
    int y1;
    int y2;
    int y3;
} *Reviews;

int qBusRev(Businesses s,int tam);

int fourOrMore(Businesses s,int tam);

int maxReviews(Businesses s,int tam);

#endif //G1_LI3_FILES_H
