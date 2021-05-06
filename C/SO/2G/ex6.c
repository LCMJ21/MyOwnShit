#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

        int value = atoi(argv[1]);
        int random_max = 1000;
            int rows = 10;
                int cols = 1000;
                    int matriz[rows][cols];

                        for(int i = 0;i < rows;i++){
                                    for(int j = 0;j < cols;j++){
                                                    matriz[i][j] = rand() % random_max;
                                                            }
                                        }

                            for(int i= 0; i < rows;i++){
                                        if(fork() == 0){
                                                        for(int j = 0;j < cols;j++){
                                                                            if (matriz[i][j] == value) _exit(i);
                                                                                        }
                                                                    _exit(255);
                                                                            }
                                            }

                                int status;
                                    for(int i= 0; i < rows;i++){
                                                pid_t terminated_pid = wait(&status);

                                                        if (WIFEXITED(status)){
                                                                        if (WEXITSTATUS(status) < 255) printf("[pai] process %d existed. found number at row: %d\n",terminated_pid,WEXITSTATUS(status));
                                                                                    else printf("[pai] process %d existed. Nothing found\n",terminated_pid);
                                                                                            } else printf("[pai] process %d existed. Something went wrong\n",terminated_pid);

                                                            }

                                        return 0;
}
