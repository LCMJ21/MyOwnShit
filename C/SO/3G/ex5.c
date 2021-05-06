#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

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
                                                   printf("[pai] process %d existed. exist code: %d\n",terminated_pid,WEXITSTATUS(status));
                                                           }
                                            else printf("[pai] process %d existed. \n",terminated_pid);
                                                }

                    return 0;
}
