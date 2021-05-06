#include "hash.h"

int hash(char key[], int size) {

    int i=0, sum=0;
    while(key[i] != '\0') sum += key[i++];

    return (sum % size);
}

void initHT(HT *h, int size) {
    int i;
    for (i=0; i<MAX; i++) h->tbl[i] = NULL;
}

int writeHT(HT *h, char key[], int value) {
    int i = hash(key, MAX);

    lista *temp = &(h[i].tbl[i]);

    while (*temp){
        if (strcmp((*temp)->key,key) == 0){
            (*temp)->value++;
            return 1;
        }
        else{
            temp = &(*temp)->next;
        }
    }
    (*temp) = malloc(sizeof(struct lligada));
    strcpy((*temp)->key, key);
    (*temp)->value = 0;
    (*temp)->next = NULL;

    return 1;
}

char *maxHT(HT *h, char key[], int value) {
    int i,max=-1;
    char *buffer = malloc(MAX);
    lista *temp;

    for(i=0;i < MAX;i++){
        temp = &(h[i].tbl[i]);
        while (temp){
            if ((*temp)->value > max){
                max=(*temp)->value;
                strcpy(buffer,(*temp)->key);
            }
            temp = &(*temp)->next;
        }
    }

    return buffer;
}
