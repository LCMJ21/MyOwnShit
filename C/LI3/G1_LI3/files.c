#include "files.h"

int qBusRev(Businesses s,int tam){
    int i = 0,j;

    for(j=0;j < tam;j++){
        if (s[j].y > 0) i++;
    }
    return i;
}

int fourOrMore(Businesses s,int tam){
    int i = 0,j;

    for(j=0;j < tam;j++){
        if (s[j].x >= 4.0) i++;
    }
    return i;
}

int maxReviews(Businesses s,int tam){
    int maior = 0,j;
    int pos = -1;

    for(j=0;j < tam;j++){
        if (s[j].y > maior) {pos = j;maior=s[j].y;}
    }
    return pos;
}

char *maxCity()
