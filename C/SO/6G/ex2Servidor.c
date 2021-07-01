#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[]) {

	if (mkfifo("fifo", 0644) == -1) perror("mkfifo");

    int fd = open("fifo", O_RDONLY);
    int bytesRead = 0;
    char buffer[1024];
    int log;

    if ( (log = open("log.txt", O_CREAT | O_WRONLY | O_APPEND)) == -1) perror("open");
    open("fifo", O_RDONLY);
    while(1) {
    	bytesRead = read(fd, buffer, 1024);
        write(log, buffer, bytesRead);
        read(STDIN_FILENO, buffer, 1024);
        if (strcmp(buffer,"quit") == 0) break;
    }

    close(fd);
    unlink("fifo");
    return 0;
}

/*
int main(int argc, char const *argv[]) {

	//mkfifo("fifo", 0644);

    int fd = open("fifo", O_RDONLY);
    int bytesRead = 0;
    char buffer[1024];
    //int log = open("log.txt", O_APPEND);
    //open("fifo", O_RDONLY);
    while((bytesRead = read(STDIN_FILENO, buffer, 1024)) > 0) {
    write(STDOUT_FILENO, buffer, bytesRead);
       //write(log, buffer, bytesRead);
    }
    close(fd);
    unlink("fifo");
    return 0;
}*/