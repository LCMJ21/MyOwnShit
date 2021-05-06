#include "C_aux.h"

//Lê o ficheiro .cod e coloca na struct os seus codigos
void leCodBloco(FILE *file, codigo *codigos[]){
    char c;
    int indice = 0; //indice para percorrer o ficheiro

    //lê todos os codigos de um bloco
    while(indice != NUM_CHA) {
        //O cod é iniciado desta maneira pois caso nao haja codigo a ler identifica com o 2
        char cod[NUM_CHA] = "";
        int i;
        //lê um codigo
        for(i = 0; c != ';' && c != '@'; i++) {
            c = fgetc(file);
            if(c != ';' && c != '@') cod [i] = c;
        }
        //Acrencenta o codigo na struct
        if (strcmp(cod, "")) {
            codigos[indice] = malloc(sizeof(codigo));
            preencheOff(cod,i-1,codigos[indice]);
        }
        c = '0';
        indice++;
    }
}

//Faz e escvreve os bytes no ficheiro
int escrevecod(FILE *file,char *buffer, codigo *codigos[],int tam) {
    char *comprimido = malloc(tam);//array onde vai ficar os bytes
    char byte[9] = EMPTY_8; //1byte
    char *temp;
    unsigned char c;
    int i = 0, offset = 0, cc = 0,contrltam = 0,ntam = 2,tamcod,endb;

    while (i != tam) {
        //Controlo se o ficheiro está cheio e arranja mais memoria se sim
        if (contrltam > tam - NUM_CHA) {
            comprimido = realloc(comprimido,(ntam++)*tam);
            contrltam = 0;
        }

        //Encontra a informação do cod do carater c a partir do atual offset
        c = buffer[i];
        tamcod = codigos[c]->next[offset];
        endb = codigos[c]->index[offset];
        temp = codigos[c]->arrayCod[offset];

        //Se o cod vai ocupar mais que 1byte
        if (endb != 0) {
            or(byte, temp);
            comprimido[cc++] = strtol(byte, 0, 2);
            contrltam ++;
            temp += 8;
            endb--;

            while (endb != 0) {
                mystrcpy(byte, temp);
                comprimido[cc++] = strtol(byte, 0, 2);
                contrltam ++;
                temp += 8;
                endb--;
            }

            mystrcpy(byte, temp);
            offset = tamcod;
        }

        //Caso não enha mais que 1 byte
        else{
            or(byte, temp);
            offset = tamcod;
        }
        i++;
    }

    //O ultimo byte
    comprimido[cc++] = strtol(byte, 0, 2);
    //contrltam ++;

    fprintf(file,"\n@%d@",cc);
    myprint(file,comprimido,cc);
    free(comprimido);

    return cc;
}

//Função principal do modulo c
void main_c (char *filename){
    clock_t timeinicio = clock();

    //Abre o ficheiro .cod
    char *filenameCod=malloc(MAX_FILE_NAME);
    strcpy(filenameCod,filename);
    strcat(filenameCod,".cod");
    FILE *fCod = fopen(filenameCod, "rb");

    //Abre o ficheiro o ficheiro original
    FILE *fileOrig = fopen(filename,"rb");

    //Cria o ficheiro .shaf
    char *filenameShaf = malloc(MAX_FILE_NAME);
    strcpy(filenameShaf,filename);
    strcat(filenameShaf,".shaf");
    FILE *fShaf = fopen(filenameShaf, "wb");

    codigo *codigos[NUM_CHA]; // Struct com todos os cods
    char *strNblocos = malloc(MAX_COD); //String onde vamos colocar o nºde blocos
    char *strTamAntes = malloc(MAX_COD); //Sring onde vão ficar os tamnhos dos blocos pré-compreção

    //Lê o numero de blocos do ficheiro .cod
    int nBlocos = leTamanhos(strNblocos,fCod,3);

    //Escreve no ficheiro .shaf o numero de blocos
    fprintf(fShaf, "@%s", strNblocos);

    int tamantes[nBlocos]; //Array com o tamanho dos blocos antes da compressão
    int tamdepois[nBlocos]; //Array com o tamnho dos blocos após compressão

    int blocoAtual = 0; //O Bloco em que estamos a trabalhar
    while (blocoAtual < nBlocos) {

        //lê o tamanho dos blocos original
        tamantes[blocoAtual] = leTamanhos(strTamAntes, fCod, 1);

        //lê o ficheiro .cod e coloca os codigos no arrayCod
        leCodBloco(fCod,codigos);

        //Coloca no buffer o bloco do ficheiro original
        char *buffer = malloc(tamantes[blocoAtual] * sizeof(char));
        fread(buffer, tamantes[blocoAtual], sizeof(char), fileOrig);

        //Escreve no ficheiro .shaf os bytes codificados
        tamdepois[blocoAtual] = escrevecod(fShaf, buffer, codigos, tamantes[blocoAtual]);

        free(buffer);
        blocoAtual++;
    }

    //dá free a variaveis com malloc
    free(strNblocos);
    free(strTamAntes);
    //freeStruct(codigos);

    //Fecha todos os ficheiros abertos
    fclose(fCod);
    fclose(fileOrig);
    fclose(fShaf);

    clock_t timeend = clock();

    //Dá print a informação sobre a execução do modulo c
    printador(blocoAtual, tamantes, tamdepois, timeend - timeinicio, filenameShaf);
}

