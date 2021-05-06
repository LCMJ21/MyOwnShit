#include <stdio.h>
#include "grafos.h"

typedef struct btree {
    int node;
    struct btree *left, *right;
} *BTree;

int test (BTree a){
    if (!a) return 0;
    else {
        int esq,dir;
        esq = test(a->left);
        dir = test(a->right);
        if (dir == -1 || esq == -1 || abs(dir - esq) > 1) return -1;
        else return 1 + esq + dir;
    }
}

#define MAX 200



int main() {
    GraphL g;
    int i = 0;
    for(;i<5;i++) g[i] = NULL;

    inseretGrafo(g, 1, 2);
    inseretGrafo(g, 1, 3);
    inseretGrafo(g, 2, 0);
    inseretGrafo(g, 2, 4);
    inseretGrafo(g, 3, 0);
    inseretGrafo(g, 3, 4);
    inseretGrafo(g, 4, 2);
    inseretGrafo(g, 4, 3);




    printGra(g, 5);



}
