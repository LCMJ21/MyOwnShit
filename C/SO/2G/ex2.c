#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

        int child_pid;
            if((child_pid = fork()) == 0){
                        printf("Child -> pid %d\n", getpid());
                                printf("Child -> Parent pid %d\n", getppid());

                                        _exit(5);
                                            }
                else{
                            printf("Parent -> pid %d\n", getpid());
                                    printf("Parent -> Parent pid %d\n", getppid());
                                            printf("Parent -> Child pid %d\n", child_pid);

                                                    int status;
                                                            int terminated_pid = wait(&status);
                                                                    printf("Parent -> Child pid %d, exit code %d\n",terminated_pid, WEXITSTATUS(status));
                                                                        }

                    
                    return 0;
}
