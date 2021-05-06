#ifndef TESTE_AEC_GRAFOS_H
#define TESTE_AEC_GRAFOS_H

#include <stdlib.h>
#include <stdio.h>

#define MAX 100

#define WHITE 0
#define GRAY 1
#define BLACK 2
#define NE 0
#define INTREE 0
#define FRINGE 1
#define UNSEEN 2

typedef int WEIGHT;

struct edge {
    int dest;
    WEIGHT weight;
    struct edge *next;
};

typedef struct edge *GraphL[MAX];

typedef WEIGHT GraphM[MAX][MAX];

void graphMtoL_sol(GraphL gl, GraphM gm, int n);
void printGra(GraphL g, int n);
void reverseGra(GraphL g, int n, GraphL new);
void inseretGrafo(GraphL g, int org, int dest);

#endif //TESTE_AEC_GRAFOS_H
