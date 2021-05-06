#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>

int pai2filho(){
        int p[2];
            char line[] = "linha1";
                char buffer[200];
                    int res;
                        int status;
                            
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
                                                                                                res = read(p[0], &buffer, sizeof(buffer));
                                                                                                            printf("[FILHO]: read %s from pipe res %d\n", buffer, res);
                                                                                                                        close(p[0]);
                                                                                                                                    _exit(0);
                                                                                                                                            default:
                                                                                                                                            close(p[0]);
                                                                                                                                                    write(p[1], &line, strlen(line));
                                                                                                                                                            printf("[PAI]: read wrote line to pipe\n");
                                                                                                                                                                    close(p[1]);
                                                                                                                                                                            wait(&status);
                                                                                                                                                                                }
}





int main(int argc, char *argv[]) {

        pai2filho();}
