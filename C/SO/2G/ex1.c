#include <unistd.h>
#include <glib.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char *argv[]) {

     printf("Child oid %d\n", getpid());

     printf("Parent oid %d\n", getppid());

     return 0;
}
