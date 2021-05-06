#include "ex.h"

int main(int argc,char * argv[]) {
    printf("Qual o ex:");
    int p,fd;
    char*line = malloc(MAX_BUF_1024);
    scanf("%d",&p);

    switch (p) {
        case 1:
            copy(argc,argv);
            break;
        case 2:
            mcat();
            break;
        case 3:
            fd = open("edgar.txt", O_RDONLY);

            //readln(fd,line, MAX_BUF_1024);
            readln_2(fd,line,MAX_BUF_1024);
            printf("%s\n",line);
            break;
        case 4:




            break;

        default:
            printf("Nao se encontra esse exercicio");
    }

    return 0;
}
