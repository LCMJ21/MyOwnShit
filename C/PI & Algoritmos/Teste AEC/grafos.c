#include "grafos.h"

void grafoM_L(GraphL gl, GraphM gm, int n) {
    int i, j;
    struct edge *head, *new;

    for (i = 0; i<n; i++) {
        head = NULL;
        for (j = n-1; j>=0; j--)
            if (gm[i][j] != NE) {
                new = malloc(sizeof(struct edge));
                new -> dest = j;
                new -> next = head;
                head = new;
            }
        gl[i] = head;
    }
}

//fucoes que dao reverse ao grafo, tenho que criar o novo antes de mandar pra aqui
void insertGra(GraphL g, int o, int dest){
    struct edge *new = malloc(sizeof(struct edge));
    new->dest = dest;
    new->next = g[o];
    g[o] = new;
}

void reverseGra(GraphL g, int n, GraphL new){
    int i;
    for(i = 0; i < n; ++i){
        struct edge *pass = g[i];
        for(; pass; pass = pass->next) insertGra(new, pass->dest, i);
    }
}

void printGra(GraphL g, int n){
    int i;
    struct edge* new;
    for(i=0;i<n;i++){
        printf("%d/",i);
        new = g[i];
        for (;new;new = new->next) printf("-->%d",new->dest);
        printf("\n");
    }
}

void inseretGrafo(GraphL g, int org, int dest){
    struct edge *new = malloc(sizeof(struct edge));
    new->dest = dest;
    new->next = g[org];
    g[org] = new;
}





/*
 *GraphM gm1 = {
            {NE,  2, NE, NE, NE,  7,  3, NE, NE},
            { 2, NE,  4, NE, NE, NE,  6, NE, NE},
            {NE,  4, NE,  2, NE, NE, NE,  2, NE},
            {NE, NE,  2, NE,  1, NE, NE,  8, NE},
            {NE, NE, NE,  1, NE,  6, NE, NE,  2},
            { 7, NE, NE, NE,  6, NE, NE, NE,  5},
            { 3,  6, NE, NE, NE, NE, NE,  3,  1},
            {NE, NE,  2,  8, NE, NE,  3, NE,  4},
            {NE, NE, NE, NE,  2,  5,  1,  4, NE}
    };
    int n1 = 9;

    GraphL g1,g2;
    graphMtoL_sol(g1,gm1,n1);
    printGra(g1, n1);

    reverseGra(g1,n1,g2);
    printGra(g2, n1);
 */