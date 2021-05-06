#include "ex.h"

#define BUFFER_SIZE 10

char buffer2[100];
int next_position = 0;
int last_read_bytes = 0;

int copy(int argc,char * argv[]) {

    void *buffer = malloc(MAX_BUF_1024 * sizeof (char ));

    int f_org,f_dst;

    if ((f_org = open("nova.txt", O_RDONLY)) == -1){
        perror("open");
        return -1;
    }


    if ((f_dst = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0600)) == -1){
        perror("open");
        return -1;
    }

    int by_r;

    while ((by_r = read(f_org,buffer,MAX_BUF_1024)) > 0){
        write(f_dst,buffer,by_r);
    }

    close(f_org);
    close(f_dst);

    free(buffer);

    return 0;
}

int mcat(){
    void *buffer = malloc(MAX_BUF_1024 * sizeof (char ));
    int by_r;

    while ((by_r = read(0,buffer,MAX_BUF_1024)) > 0){
        write(1,buffer,by_r);
    }
    return 0;
}

int readc(int fd,char *c){
    return read(fd,c,1);
}


ssize_t readln(int fd, char *line, size_t size){
    int res;
    int i = 0;

    while ( i < size && (res = readc(fd,&line[i])) > 0){
        if (((char *) line)[i++] == '\n') return i;
    }

    printf("%s",line);

    return i;
}

int readch(int fd,char *buf){

    if (next_position == last_read_bytes){
        int bytes = 0;
        if ((bytes = read(fd, buffer2, BUFFER_SIZE)) < 1) return 1;

        next_position = 0;
        last_read_bytes = bytes;
    }

    *buf = buffer2[next_position];
    next_position++;

    return 1;
}

ssize_t readln_2(int fd, char *line, size_t size){
    int next_pos = 0;

    while (next_pos < size && readch(fd, line + next_pos) > 0){
        if(line[next_pos++] == '\n') break;
    }

    return next_pos;
}