#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>

int pai2filho(){
    int p[2];
    char* line[] = {"line1", "line2","line3","line4"};
    char buffer[200];
    int res;
    int status;

    bzero(buffer,200);

    if (pipe(p) == -1){
        perror("pipe");
        return -1;
    }


    switch ((fork())) {
        case -1:
            perror("fork");
            return -1;
        case 0:

            close(p[1]);
            while ((res = read(p[0], &buffer, sizeof(buffer))) > 0)  printf("[FILHO]: read %s from pipe res %d\n", buffer, res);
            close(p[0]);
            _exit(0);
        default:

        close(p[0]);
        for (int i = 0;i < 4 && (write(p[1], line[i], strlen(line[i])) > 0);i++) {
            printf("[PAI]: wrote %s to pipe\n",line[i]);
        }
        close(p[1]);
        wait(&status);
    }
}

int main(int argc, char *argv[]) {

    pai2filho();}
