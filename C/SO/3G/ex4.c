#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char* argv[]) {
        execl("ex3", "ex3", "arg1", "arg2", "arg3",NULL);
            perror("exec");
                
                return 0;}
