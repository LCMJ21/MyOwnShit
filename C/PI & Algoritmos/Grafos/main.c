#include <stdio.h>
#include <stdlib.h>

#define NE 0
#define MAX 20

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

int mst(GraphL g, int n, int parent[])
{   int orla[n],status[n],edges = 0,i = 0,x = 0;
    for(;i < n;i++) {status[n] = UNSEEN;orla[i] = -1;}
    struct edge *lista = NULL;

    while (edges < n){
        lista = g[x];
        for(;lista;lista = lista->next){
            int y = lista->dest,wxy = lista->weight;
            if (status[y] == UNSEEN){
                status[y] = FRINGE;
                parent[y] = x;
                orla[y] = wxy;
            }
            else if (status[y] == FRINGE && wxy < orla[y]){
                parent[y] = x;
                orla[y] = wxy;
            }
        }
        orla[x] = -1;
        status[x] = INTREE;
        edges++;
    }

    return 1;
}



