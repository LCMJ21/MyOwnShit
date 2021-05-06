#include "main.h"

void initHeap (Heap *h, int size) {
    h->used = 0;
    h->size = size;
    h->values = malloc(size*sizeof(Elem));
}



void bubbleUp (Elem h[], int i) {
    while (i>0 && h[i] < h[PARENT(i)]){
        swap(h,i,PARENT(i));
        i = PARENT(i);
    }
}



int  insertHeap (Heap *h, Elem x) {
    if (h->size == h->used){
        h->values = realloc(h->values, 2*(h->size)*sizeof(Elem));
        h->size = h->size *2;
    }
    h->values[h->used] = x;
    bubbleUp (h->values,h->used);
    (h->used)++;
    return 1;
}



void bubbleDown (Elem h[], int N) {
    int i = 0;
    while (LEFT(i) < N){
        if (h[LEFT(i)] > h[RIGHT(i)] && h[i] >  h[RIGHT(i)]){
            swap(h,i,RIGHT(i));
            i = RIGHT(i);
        }
        else if (h[LEFT(i)] <= h[RIGHT(i)] && h[i] >  h[LEFT(i)]) {
            swap(h, i, LEFT(i));
            i = LEFT(i);
        }
        else break;
    }
}



int  extractMin (Heap *h, Elem *x) {
    if (!h->used) return 0;
    (*x) = h->values[0];
    (h->used)--;
    h->values[0] = h->values[h->used];
    bubbleDown (h->values, h->used);
    return (*x);
}

int minHeapOK (Heap h) {
    int i = 0;
    while (LEFT(i) < h.used){
        if (h.values[i] > h.values[LEFT(i)]) return -1;
        if (RIGHT(i) < h.used){
            if (h.values[i] > h.values[RIGHT(i)]) return -1;
        } else return 1;
        i++;
    }
    return 1;
}