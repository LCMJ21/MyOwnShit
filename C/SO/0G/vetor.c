#include <stdlib.h>

void fill(int* vector, int size, int value){
    int i = 0;
    for(;i < size;i++) vector[i] = value;
}

int find(int* vector, int size, int value){
    int i = 0;
    for(;i < size;i++) if(vector[i] == value) return i;
    return -1;
}
