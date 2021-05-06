#include <unistd.h>
#include <fcntl.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
        int ifd = open("/etc/passwd",O_RDONLY);
            int ofd = open("saida.txt",O_CREAT | O_TRUNC | O_WRONLY, 0644);
                int efd = open("erros.txt",O_CREAT | O_TRUNC | O_WRONLY, 0644);

                    dup2(ifd,0);
                        close(ifd);
                            dup2(ofd,1);
                                close(ofd);
                                    dup2(efd,2);
                                        close(efd);


                                            if (fork() == 0) {
                                                        char c;
                                                                while (read(0, &c, 1) > 0) {
                                                                                write(1, &c, 1);
                                                                                            write(2, &c, 2);
                                                                                                    }
                                                                    }
                                                
                                                wait(NULL);

                                                    return 0;}
