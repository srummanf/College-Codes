#include <sys/types.h> 
#include <sys/socket.h> 
#include <netdb.h> 
#include <stdio.h> 
#include<string.h>
int factorial(char n[])
{
    int x;
    sscanf(n, "%d", &x);

    int f = 1;
    for (int i = 2; i <= x; i++)
    {
        f *= i;
    }

    return f;
}
int main()
{
    char str[100];
    int listen_fd, comm_fd;
    struct sockaddr_in servaddr;
    listen_fd = socket(AF_INET, SOCK_STREAM, 0);
    bzero(&servaddr, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htons(INADDR_ANY);
    servaddr.sin_port = htons(22000);
    bind(listen_fd, (struct sockaddr *)&servaddr, sizeof(servaddr));
    listen(listen_fd, 10);
    comm_fd = accept(listen_fd, (struct sockaddr *)NULL, NULL);
    while (1)
    {
        bzero(str, 100);
        read(comm_fd, str, 100);
        int f = factorial(str);
        printf("Echoing back -%s %d", str, f);
        char st[10];
        sprintf(st, "%d", f);
        write(comm_fd, st, strlen(st) + 1);
    }
}
