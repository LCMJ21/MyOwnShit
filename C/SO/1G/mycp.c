#include <unistd.h>
#include <fcntl.h>

#define MAX_BUF 1024

int main(int argc,char * argv[]) {

    void *buffer = malloc(MAX_BUF);

    int f_org,f_dst;

    if ((f_org = open(argv[1], O_RDONLY) == -1)){
        perror("open");
        return -1;
    }

    if ((f_dst = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0640)) == -1){
        perror("open");
        return -1;
    }

    int by_r;

    printf("%s",argv[1]);

    while ((by_r = read(f_org,buffer,MAX_BUF)) > 0){
        printf("%d",by_r);
        write(f_dst,buffer,by_r);
    }

    close(f_org);
    close(f_org);

    return 0;
}
