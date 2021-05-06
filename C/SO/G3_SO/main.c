#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>

ssize_t readln(int fd, char* line, size_t size) {
    ssize_t i = 0;
    while(i < size - 1) {
        ssize_t bytes_read = read(fd, &line[i], 1);
        if(bytes_read < 1) break;
        if(line[i++] == '\n') break;
    }
    line[i] = 0;
    return i;
}

int pai2filho(){
    int p[2];
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
            close(pipe_fd[1]);
            dup2(pipe_fd[0], STDIN_FILENO);
            close(pipe_fd[0]);
            execlp("wc","wc",NULL);
        default:

            close(pipe_fd[0]);
            int bytes;
            char buf[10];
            while((bytes = read(STDIN_FILENO, buf, 10)) > 0)
                write(pipe_fd[1], buf, bytes);
            close(pipe_fd[1]);
            wait(NULL);
    }
}





int main(int argc, char *argv[]) {

    pai2filho();










    int ifd = open("/etc/passwd",O_RDONLY);
    int ofd = open("saida.txt",O_CREAT | O_TRUNC | O_WRONLY, 0644);
    int efd = open("erros.txt",O_CREAT | O_TRUNC | O_WRONLY, 0644);

    dup2(ifd,0);
    close(ifd);
    dup2(ofd,1);
    close(ofd);
    dup2(efd,2);
    close(efd);


    if (fork() == 0) {
        char c;
        while (read(0, &c, 1) > 0) {
            execlp("wc", "wc", NULL);
            _exit(1);
        }
    }

    wait(NULL);

    return 0;




































    int status;

    for(int i = 1;i < argc;i++){
        if(fork() == 0){
            execlp(argv[i], argv[i],NULL);
            perror("exec");
            _exit(i);
        }
    }

    for(int i = 1;i < argc;i++){
        pid_t terminated_pid = wait(&status);

        if (WIFEXITED(status)){
           printf("[pai] process %d existed. "
                  "exist code: %d\n",terminated_pid,WEXITSTATUS(status));
        }
        else printf("[pai] process %d existed. \n",terminated_pid);
    }

    return 0;
}




























#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char* argv[]) {
    execl("ex3", "ls", "arg1", "arg2", "arg3",NULL);
    perror("exec");

    return 0;




    _exit(0);















    for(int i = 0; i < argc ;i++){
        printf("argv[%d]: %s\n", i, argv[i]);
    }

    return 0;



    if (fork() == 0){
        execl("/bin/ls", "ls", "-l",NULL);
        _exit(0);
    }

    int status;
    wait(&status);

    printf("Fork + Exec done\n");
    return 0;
}
