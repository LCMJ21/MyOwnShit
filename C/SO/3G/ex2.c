#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
        if (fork() == 0){
                    execl("/bin/ls", "ls", "-l",NULL);
                            _exit(0);
                                }
            
            int status;
                wait(&status);

                    printf("Fork + Exec done\n");
                        return 0;
}
