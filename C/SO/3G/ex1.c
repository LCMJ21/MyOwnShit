#include <stdio.h>
#include <unistd.h>

int main() {
        execl("/bin/ls", "ls", "-l",NULL);

            printf("Exec done\n");
                return 0;
}
