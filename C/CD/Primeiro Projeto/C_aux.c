#include "C_aux.h"

//Dá print caracter a caracter para um file de uma string
void myprint(FILE *file, char* resultado,int tam){
    int cc = 0;
    while (tam){
        fprintf(file,"%c",resultado[cc++]);
        tam--;
    }
}

/*
 * Função potência para números inteiros (desnecessário importar math.h).
 */
int my_pow(int base, int exp){
    if(exp < 0)
        return -1;

    int result = 1;
    while (exp){
    	if (exp & 1)
    		result *= base;
        exp >>= 1;
        base *= base;
    }
    return result;
}

//Transforma a string numC no seu correspondente inteiro
//Esta função é repetida para se poder correr o modulo independente
int charToNum2(int N, char numC[N]){
    int num = 0;
    for(int i = 0; i < N; ++i){
        num += (numC[i] - '0') * (int)my_pow(10,N-i-1);
    }
    return num;
}

//Recebe um ficheiro e lê a sua parte inicial guardando o valor numa string
int leTamanhos(char* tam,FILE* file,int arrobas){
    int i=0;char c;

    while (arrobas){
        c = fgetc(file);
        if (c == '@') arrobas--;
        else if (c >= '0' && c <= '9'){
            tam[i++] = c;
        }
    }
    tam[i] = '\0';

    return charToNum2(i, tam);
}

//
void shift(char *cod){
    int i;
    for(i = NUM_CHA-2;i;i--) cod[i] = cod[i-1];
    cod[0] = '0';
}

void enche0(char* cod,int tam){
    //printf("%s -> %d\n",cod, tam);
    for(;tam<NUM_CHA;tam++) cod[tam] = '0';
    cod[255] = '\0';
}

void mystrcpy (char *dest,char *org){
    for(int i=0;i<8;i++) dest[i] = org[i];
}

//Com um codigo preenche a struct com todos os seus offsets
void preencheOff(char* cod,int tam,codigo *codigos){
    int i,ind = tam/8;
    enche0(cod,tam);
    tam -= ind*8;
    for(i=0;i<8;i++,tam++,shift(cod)){
        strcpy(codigos->arrayCod[i],cod);
        if (tam == 8) {
            ind++;
            tam = 0;
        }
        codigos->next[i] = tam;
        codigos->index[i] = ind;
    }
}

void or (char* byte,char* cod){
    for (int i=0;i<8;i++) {if (cod[i] == '1') byte[i] = '1';}
}

void printador(int nblocos,int tamantes[],int tamdps[],clock_t tempo,char *filename) {
    float ctotal=0;
    float depois = 0,antes=0;
    printf("Jorge Lima,a93183,MIEI/CD\nJoao Martins,a91669,MIEI/CD\n");
    printf("10 Dez 2021\n");
    printf("Modulo: c (codificacao dum ficheiro de simbolos)\n");
    printf("Numero de blocos: %d\n",nblocos);
    for(int i=0;i<nblocos;i++) {
        printf("Tamanho antes/depois & taxa de compressao (bloco %d): %d/%d\n",i+1,tamantes[i],tamdps[i]);
        depois += tamdps[i];
        antes += tamantes[i];
    }
    ctotal = (depois/antes) * 100;
    printf("Taxa de compressao global: %f%%\n",ctotal);
    printf("Tempo de execucao do modulo (milissegundos): %ld\n",tempo);
    printf("Ficheiro gerado: %s",filename);
}
