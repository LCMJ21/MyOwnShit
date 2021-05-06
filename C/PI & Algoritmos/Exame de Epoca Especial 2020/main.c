#include <stdio.h>
#include <stdlib.h>

#define PARENT(i) (i-1)/2  // os indices do array começam em 0
#define LEFT(i) 2*i + 1
#define RIGHT(i) 2*i + 2
#define MAX 5

/*
 * 1.1
 *
 * P: ((forall (0 <= i < N), v[i] == vi) && N > 1)
 * Q: ((forall (0 < i < N), v[i] > v[PARENT(i)]) && N > 1)
 *
 */

void heapify (int v[], int N) {
    // (forall (0 <= i < N), v[i] == vi) && N > 1
    int i;
    for (i=(N-2)/2; i>=0; i--)
        bubbledown (v,N,i);
    // (forall (0 < i < N), v[i] > v[PARENT(i)]) && N > 1
}

/*
 * 1.2
 *
 * I = (forall (N < i < N0), V[i-1] < v[i]) && N >= 0
 *
 */
void hsort (int v[], int N) {
    heapify (v,N);
    // I = (forall (N < i < N0), V[i-1] < v[i]) && N >= 0
    while (--N > 0) {
        swap (v,0,N);
        bubbledown (v,N,0);
    }
}



/*
 * 2.1
 */

typedef struct pair {
    int key;
    int value;
}PAIR;

typedef struct {
    int   size;
    int   used;
    PAIR *tbl;
} HT;

HT *init_ht(int size){
    HT *h = malloc(sizeof(HT));
    h->tbl = calloc(size, sizeof(PAIR));
    h->size = size;
    h->used = 0;
    int i;
    for (i = 0; i < h->size; i++) h->tbl[i].key = -1;
    return h;
}

int hash(int key, int size){ return (mod(key)%size); }

int freeHT_sol(HT *h, int k) {
    if (h->tbl[i].key = -1) return 1;
    return 0;
}

void insert_table(HT *h, int value){
    // posição onde vamos colocar o valor Y[i]
    int i = hash(key, h->size);
    while (!freeHT(h, i)) i = (i+1)%(h->size);

    h->tbl[i].key = key;
    h->tbl[i].value = value;
    h->used++;
}

int search_table(HT *h, int value) {
    int i, ii;
    i = ii = hash(value, h->size);

    while ( h->tbl[i].value != value){
        if((h->tbl)[i].key == -1) return -1;
        i = (i+1)%(h->size);
        if (i == ii) return -1;
    }
    return i;
}


void bubble_down(int v[], int N){
    int f = 1; // filho da esquerda, se existir
    int i = 0;
    while(f < N){
        if(f+1 < N && v[f+1] < v[f]) f++;
        if(v[f] > v[i]) break;
        swap(v,i,f);
        i=f; f=2*i+1;
    }
}


void bubble_up(int v[], int i){
    // enquanto que o nodo pai seja maior do que o filho, bubble up
    while(i > 0 && v[(i-1)/2] > v[i]){
        swap(v, (i-1)/2, i);
        i = (i-1)/2;
    }
}

void maiores(int X[], int Y[], int N, int M, int resultado[]){

    // Iniciar hash table com tamanho N*2 (era fixe que fosse com nº primo)
    HT *hash_table = init_ht(2*N);

    // Inserir os elementos de Y na hash_table (tempo amortizado linear)
    int i;
    for(i = 0; i < N; ++i) insert_table(hash_table, Y[i]);

    // Vamos assumir que o array resultado é uma min-heap de tamanho M.
    int heap_used = 0;
    for(i = 0; i < M; ++i) resultado[i] = -1;

    // Vamos percorrer o array X e adicionar os M primeiros elementos que não estão em Y
    for(i = 0; i < N && heap_used < M; ++i){
        // Se o elemento X[i] não ocurrer em Y[i]
        int x = X[i];
        if(search_table(hash_table, x) == -1){
            // o valor x é maior do que o menor valor da min-heap
            resultado[heap_used] = x;
            bubble_up(resultado, heap_used);
            heap_used++;
        }
    }

    // Vamos continuar a percorrer o array X e manter os M elementos maiores de X
    // que não estão no array Y[]
    for(; i < N; ++i){
        int x = X[i];
        if(search_table(hash_table, x) == -1)
            if(x > resultado[0]){
                resultado[0] = x;
                bubble_down(resultado, M);
            }
    }
}
/*
 * 2.2
 * Em ambos os casos sabemos que vamos ter que criar a heap  que contem Y que demora N
 *
 * Também temos que preencher a heap / array resultado com -1 logo:M
 *
 * Aqui temos o melhor caso e o pior caso:
 *
 * No melhor caso temos todos os M elementos que procuramos nos M primeiro elementos de X
 * Assim temos M * T(bubble_up) = M * logN
 *
 * Logo no melhor caso T = N + M + (M * logN)
 *
 * No pior caso temos os M elementos que procuramos nos ultimos elementos de x
 * Assim temos M * T(bubble_up) + (N-M) * T (bubble_down) = M logN + (N-M) log N = N logN
 *
 * Logo no pior caso T = N + M + (N * logN)
 */

typedef struct node {
    int elem;
    struct node *esq;
    struct node *dir;
} Node, *ArvBin;

int avl (ArvBin t)
{
    if (!t) return 1;
    if abs(altura(t->esq) - altura(t->dir)) > 1 return 0;
    if (avl(t->esq) && avl(t->dir)) return 1;
}

/*
 *T(altura) = N
 *
 * No melhor caso o lado esquerdo e o direito da raiz tem aluras diferentes por mais de 1 unidade
 * 2 chamadas das altuas 2N
 * Assim T = 2N
 *
 * No pior caso temos uma arvore avl
 * Assim temos o T = 2N + T(N/2) + T(N/2)
 * que pode ser raduzido como sum (i=0 até logN) 2N/(2^i) = 2N * (2 - (2^-logN))
 *
 */

struct edge {
    int dest;
    struct edge *next;
};
typedef struct edge *GraphL[MAX];

void insert_graph(GraphL g, int src, int dest){
    struct edge *new_edge = malloc(sizeof(struct edge));
    new_edge->dest = dest;

    new_edge->next = g[src];
    g[src] = new_edge;
}

GraphL reverse_graph(GraphL g, int n){
    GraphL* new = malloc(sizeof(struct edge *) * n);

    int i;
    for(i = 0; i < n; ++i){
        struct edge *aux = g[i];
        for(; aux != NULL; aux = aux->next){
            /* Se existe uma aresta de i para aux->dest neste grafo
             * No novo grafo existirá uma aresta de aux->dest para i.
             */
            insert_graph(new, aux->dest, i);
        }
    }

    return *new;
}

int most_reachable (GraphL g, int N){
    int i,maior = 0,novo = 0,indice;
    GraphL g_reverse = reverse_graph(g, N);
    struct edge* new;

    for(i=0;i<N;i++){
        new = g_reverse[i];
        for(;new;new = new->next) novo++;
        if (novo == N-1) return i;
        if (novo > maior){
            indice = i;
            maior = novo;
        }
    }

    return indice;
}

typedef struct GraphM[MAX][MAX];
int most_reachable2 (GraphM g, int N){
    int i,j,maior = 0,new = 0,maiorind = 0;

    for(i=0;i<N;i++){
        new = 0;
        for(j=0;j<N;j++) new += g[j][i];
        if (new == N-1) return j;
        if (new > maior) {
            maiorind = j;
            maior = new;
        }
    }

    return maiorind;
}

/*
 * 3
 * Em ambas as funções o mellhor caso é o primeiro nodo, o nodo 0 ter ligação a todos os ouros nodos
 *
 * O melhor caso T = V
 *
 * O pior caso na primeira função é percorer toda a estrutura que é T = V + A
 *
 * O pior caso na segunda função é percorer toda a estrutura que é T = V^2
 */

int main() {
    printf("Hello, World!\n");
    return 0;
}
