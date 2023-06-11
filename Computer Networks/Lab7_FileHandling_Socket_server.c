#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

#define PORT 8080
int main(int argc, char const *argv[])
{
    int socket_fd, new_socket, valread;
    struct sockaddr_in address;
    int opt = 1;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};
    // Create a TCP socket
    if ((socket_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }
    // Set socket options
    if (setsockopt(socket_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,
                   &opt, sizeof(opt)))
    {
        perror("setsockopt failed");
        exit(EXIT_FAILURE);
    }
    // Bind the socket to a port
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
    if (bind(socket_fd, (struct sockaddr *)&address, sizeof(address)) < 0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }
    // Start listening for incoming connections
    if (listen(socket_fd, 3) < 0)
    {
        perror("listen failed");
        exit(EXIT_FAILURE);
    }
    // Accept incoming connections
    if ((new_socket = accept(socket_fd, (struct sockaddr *)&address,
                             (socklen_t *)&addrlen)) < 0)
    {
        perror("accept failed");
        exit(EXIT_FAILURE);
    }
    // Receive file name from client
    valread = read(new_socket, buffer, 1024);
    printf("Received file name: %s\n", buffer);
    // Open the file
    FILE *fp;
    fp = fopen(buffer, "r");
    if (fp == NULL)
    {
        perror("file open failed");
        exit(EXIT_FAILURE);
    }
    // Count the number of words in the file
    int word_count = 0;
    char ch = fgetc(fp);
    while (ch != EOF)
    {
        if (ch == ' ' || ch == '\n' || ch == '\t')
        {
            word_count++;
        }
        ch = fgetc(fp);
    }
    fclose(fp);
    // Send the word count to the client
    sprintf(buffer, "%d", word_count);
    send(new_socket, buffer, strlen(buffer), 0);
    return 0;
}