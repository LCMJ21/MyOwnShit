#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define MAX_NAME 200

typedef struct Person {
    char name[MAX_NAME];
    int age;
} Person;

int main(int argc,char * argv[]) {
    char mode[2];
    strcpy(mode,argv[1]);
    char nome[MAX_NAME];
    strcpy(nome,argv[2]);
    int age = atoi(argv[3]);

    /*

    printf("%s\n",mode);
    printf("%s\n",nome);
    printf("%d\n",age);

     */

    int fd;

    if (strcmp("-i",mode) == 0){
        int i = 0;
        fd = open("pessoas.bin", O_CREAT | O_APPEND | O_RDWR, 0644);
        printf("%d\n",fd);
        Person p;
        strcpy(p.name,nome);
        p.age = age;
        write(fd,&p,sizeof(Person));
    }
    else if (strcmp("-u",mode) == 0){
        fd = open("pessoas.bin", O_CREAT | O_RDWR, 0644);
        Person p;
        while ( read(fd,&p,sizeof(Person)) > 0){
            if (strcmp(nome,p.name) == 0){
                lseek(fd,-sizeof(Person),SEEK_CUR);
                p.age = age;
                write(fd,&p,sizeof(Person));
                break;
            }
        }
    }
    else if (strcmp("-is",mode) == 0){
        fd = open("pessoas.bin", O_CREAT | O_RDWR, 0644);
        int i = 0;
        Person p;
        while ( read(fd,&p,sizeof(Person)) > 0) i++;
        printf("Registo %d\n",i+1);
        strcpy(p.name,nome);
        p.age = age;
        write(fd,&p,sizeof(Person));
    }
    else if (strcmp("-us",mode) == 0){
        fd = open("pessoas.bin", O_CREAT | O_RDWR, 0644);
        int registo = atoi(argv[2]) - 1;
        lseek(fd,registo * sizeof(Person),0);
        Person p;
        read(fd,&p,sizeof(Person));
        lseek(fd,-sizeof(Person),SEEK_CUR);
        p.age = age;
        write(fd,&p,sizeof(Person));
    }
    else {
        printf("Erro");
    }

    int new = open("pessoas.bin", O_CREAT | O_RDWR, 0644);
    Person p;
    while ( read(new,&p,sizeof(Person)) > 0){
        printf("%s %d\n",p.name,p.age);
    }

    return 0;
}