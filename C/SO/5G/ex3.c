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

    switch ((fork())) {
        case -1:
            perror("fork");
        case 0:
            close(p[1]);
        	dup2(p[0], STDIN_FILENO);
        	close(p[0]);
        	execlp("wc","wc",NULL);
        default:
            close(p[0]);
        	int bytes;
        	char buf[10];
        	while((bytes = read(STDIN_FILENO, buf, 10)) > 0) write(p[1], buf, bytes);
        	close(p[1]);
        	wait(NULL);
    }
}

int main(int argc, char *argv[]) {

    pai2filho();
}