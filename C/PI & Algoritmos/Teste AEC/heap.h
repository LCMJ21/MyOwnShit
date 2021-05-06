#ifndef TESTE_AEC_HEAP_H
#define TESTE_AEC_HEAP_H

#include <stdlib.h>
#include <stdio.h>

#define PARENT(i) (i - 1) / 2
#define LEFT(i)   2 * i + 1
#define RIGHT(i)  2 * i + 2

typedef int Elem;

typedef struct heap {
    int size;
    int used;
    Elem *values;
}Heap;

#endif //TESTE_AEC_HEAP_H
