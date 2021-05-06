#include <stdio.h>
#include "avl.h"

Tree rotateRight(Tree t){
    Tree aux = t->right;
    t->right = aux->left;
    aux->left = t;
    return aux;
}






int main() {
    printf("Hello, World!\n");
    return 0;
}
