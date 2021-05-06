#include "heap.h"

void Swap(Elem h[], int x, int y){
    int z = h[x];
    h[x] = h[y];
    h[y] = z;
}

void BubbleUp(Elem h[], int i){
    while (i != 0 && h[i] < h[PARENT(i)]){
        Swap(h, i, PARENT(i));
        i = PARENT(i);
    }
}

void BubbleDown(Elem h[], int n){
    int i = 0,m;

    while (LEFT(i) < n) {
        m = h[i] < h[LEFT(i)] ? i : LEFT(i);

        if (RIGHT(i) < n) m = h[m] < h[RIGHT(i)] ? m : RIGHT(i);

        if (m != i) {
            Swap(h, i, m);
            i = m;
        }
        else break;
    }
}

void printHeap(Elem h[], int n){
    int i = 0;
    while (i<n){
        printf("%d ",h[i]);
        i++;
    }
    printf("\n");
}

void initHeap (Heap *h, int size){
    h->size = size;
    h->used = 0;
    h->values = malloc(size * sizeof(Elem));
}

int  insertHeap (Heap *h, Elem x){
    if (h->used == h->size){
        h->size *=2;
        h->values = realloc(h->values,h->size * sizeof(Elem));
    }

    h->values[h->used] = x;
    BubbleUp(h->values,h->used++);

    return 1;
}

int  extractMin (Heap *h, Elem *x){
    if (!h->used) return 0;
    *x = h->values[0];
    h->used--;
    h->values[0] = h->values[h->used];
    BubbleDown(h->values,h->used);
    return 1;
}

/*
int main()
{

    Heap h;
    int i, x;

    initHeap(&h, 1);

    insertHeap(&h, 30);
    insertHeap(&h, 60);
    insertHeap(&h, 40);
    insertHeap(&h, 10);
    insertHeap(&h, 100);
    insertHeap(&h, 20);
    insertHeap(&h, 90);
    insertHeap(&h, 50);
    insertHeap(&h, 80);
    insertHeap(&h, 70);

    printHeap(h.values, h.used);

}
 */