//
// Created by jorge on 24/02/2021.
//

#ifndef SC_EX_H
#define SC_EX_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>

#define MAX_BUF_1024 1024
#define MAX_BUF_2048 2048

int copy(int argc,char * argv[]);
int mcat();
ssize_t readln(int fd, char *line, size_t size);
ssize_t readln_2(int fd, char *line, size_t size);

#endif //SC_EX_H
