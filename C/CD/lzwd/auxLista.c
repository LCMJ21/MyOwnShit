#include "auxLista.h"

//Recebe uma string e devolve o seu tamanho
int mystrlen(char *Pattern){
    int cc = 0;
    for(;Pattern[cc] != '\0';cc++);
    return cc;
}

//Determina o tamanho da lista l
int lenthLista (Lcod l){
    Lcod teste = l;
    int cc = 0;
    for(;teste;teste = teste->next,cc++);
    return cc;
}

//Insere um novo padrão na lista ligada de forma decrescente
Lcod insertLista (Lcod l, char *Pattern, int len,int ind){
    //Cria o novo padrão
    Lcod new = malloc(sizeof(struct lligada));
    new->Pattern = Pattern;
    new->len = len;
    new->ind = ind;

    //Insere na posição coreta da lista
    Lcod *percorre = &l;
    while (*percorre){
        if (new->len >= (*percorre)->len){
            new->next = (*percorre);
            (*percorre) = new;
            return l;
        }
        percorre = &((*percorre)->next);
    }
    new->next = NULL;
    (*percorre) = new;

    return l;
}

// filtra a lista para retirar os padrões que não tenhem o char c na possição nivel
Lcod filerLista ( char c,int nivel,Lcod l){
    char h;
    Lcod *teste = &l;

    //percorre a lista
    while (*teste){
        h = (*teste)->Pattern[nivel];
        //Se o char não for igual e ainda estamos dentro do limite do padrão retiramos o padrão da lista
        if (nivel < (*teste)->len && h != c) (*teste) = (*teste)->next;
        else teste = &((*teste)->next);
    }

    return l;
}

//gera a uma lista com os padrões começados em c e com o padrão do caracter c
Lcod filerListaCreate ( char c, char **cod,int maxlen){
    Lcod new = NULL;
    int len;
    //cria um padrão com o char c e insere na lista
    char *Pattern = malloc(2);
    Pattern[0] = c;
    Pattern[1] = '\0';
    new = insertLista(new,Pattern,1,(unsigned char) c);

    //percorre o array de padrões e insere na lista
    int cc = 0;
    while (cod[cc] != NULL){
        len =  mystrlen(cod[cc]);
        if (cod[cc][0] == c && len <= maxlen) new = insertLista(new,cod[cc],len,cc+ 257);
        cc++;
    }

    return new;
}