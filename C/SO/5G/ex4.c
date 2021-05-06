#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>

int pai2filho(){
    int p[2];

    if (pipe(p) == -1){
        perror("pipe");
        return -1;
    }

    if (fork() == 0){
        close(p[0]);
        dup2(p[1], 1);
        close(p[1]);
        execlp("ls","ls", "/etc",NULL);
        _exit(0);
    }

    close(p[1]);
    wait(NULL);

    if (fork() == 0){
        dup2(p[0], 0);
        close(p[0]);
        execlp("wc","wc","-l",NULL);
        _exit(1);
    }

    wait(NULL);
    close(p[0]);

    return 0;
}

int main(int argc, char *argv[]) {

    pai2filho();
}