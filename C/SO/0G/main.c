#include <stdlib.h>
#include <stdio.h>
#include "vetor.h"
#define MAX 10

int main (){
    int *vetor = malloc(MAX * sizeof(int));
    int value;
    printf("Intruduza o valor do array:");
    scanf("%d",&value);
    fill(vetor,MAX,value);

    int max,min;
    printf("Qual é o limite inferior?:");
    scanf("%d",&min);
    printf("Qual e o limite superior?:(Max = %d)",MAX);
    scanf("%d",&max);
    printf("Qual e o valor a encontrar:");
    scanf("%d",&value);

    int resultado = find(vetor+min,max,value);
    if(resultado == -1) printf("O valor que pediu nao se encontra no array\n");
    else printf("O valor que pediu encontrasse no indice %d\n",resultado+min);

    return 0;
}
