#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

        for(int i = 1; i <= 10;i++){

                    if(fork() == 0)  {
                                    printf("Child %d -> pid %d\n",i, getpid());
                                                printf("Child %d -> Parent pid %d\n",i, getppid());
                                                        }
                            else{
                                            int status;
                                                        int terminated_pid = wait(&status);
                                                                    printf("Parent -> Child pid %d, exit code %d\n",terminated_pid, WEXITSTATUS(status));
                                                                                _exit(i-1);
                                                                                        }
                                }

            _exit(10);
}
