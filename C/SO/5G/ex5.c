#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>

#define MAX 1024

int command_exec(char* command) {
    char* args[MAX];
    char* token;
    char* rest = command;
    size_t i = 0;
    while((token = strtok_r(rest, " ", &rest)))
        args[i++] = strdup(token);
    args[i] = NULL;

    return execvp(args[0], args);
}

int pai2filho(){
    char *command[] = {"grep -v ˆ# /etc/passwd" , "cut -f7 -d:" , "uniq" , "wc -l"};
    int N = 4;
    int pipe_fd[N-1][2];

    int i = 0;
    while (i < N){
        if (i == 0){
            if(pipe(pipe_fd[i]) < 0) {
            perror("pipe");
            exit(1);
            }

            switch ((fork())) {
                case -1:
                    perror("fork");
                case 0:
                    close(pipe_fd[i][0]);
                    dup2(pipe_fd[i][1], 1);
                    close(pipe_fd[i][1]);
                    //execc_comand
                    command_exec(command[i]);
                    _exit(1);
                default:
                    close(pipe_fd[i][1]);
            }
        }

        else if (i == N - 1){
            switch ((fork())) {
                case -1:
                    perror("fork");
                case 0:
                    dup2(pipe_fd[i-1][0], 0);
                    close(pipe_fd[i-1][0]);
                    command_exec(command[i]);
                    _exit(1);
                default:
                    close(pipe_fd[i-1][0]);
            }

        }

        else {
            if(pipe(pipe_fd[i]) < 0) {
                perror("pipe");
                exit(1);
            }

            switch ((fork())) {
                case -1:
                    perror("fork");
                case 0:
                    close(pipe_fd[i][0]);
                    dup2(pipe_fd[i-1][0], 0);
                    close(pipe_fd[i-1][0]);
                    dup2(pipe_fd[i][1], 1);
                    close(pipe_fd[i][1]);
                    command_exec(command[i]);
                    _exit(1);
                default:
                    close(pipe_fd[i][1]);
            }
        }
        i++;
    }

    if (wait(NULL) == -1) puts("Error");
    if (wait(NULL) == -1) puts("Error");
    if (wait(NULL) == -1) puts("Error");
    if (wait(NULL) == -1) puts("Error");

    return 0;

}

int main(int argc, char *argv[]) {
    pai2filho();
    // Não percebi como é que é suposo implementar a coisa de receber os args
}