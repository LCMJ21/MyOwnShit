#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

    int value = atoi(argv[1]);
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
            else printf("[pai] process %d existed. Nothing found",terminated_pid);
        } else printf("[pai] process %d existed. Something went wrong",terminated_pid);

    }

    return 0;





















    return 0;
















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


















    for(int i = 1; i <= 10;i++){

        int status;
        int terminated_pid = wait(&status);
        printf("Parent -> Child pid %d, exit code %d\n",terminated_pid, WEXITSTATUS(status));
    }

















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
