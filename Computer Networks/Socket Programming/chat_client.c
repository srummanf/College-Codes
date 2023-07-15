#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#define BUFFER_SIZE 1024
void receive_messages(int socket)
{
    char buffer[BUFFER_SIZE];
    int bytes_received;

    while ((bytes_received = recv(socket, buffer, BUFFER_SIZE, 0)) > 0)
    {
        buffer[bytes_received] = '\0';
        printf("Received from server: %s\n", buffer);
    }
    printf("Disconnected from the server\n");
}
void send_messages(int socket)
{
    char buffer[BUFFER_SIZE];
    int bytes_sent;
    while (1)
    {
        printf("Enter a message: ");
        fgets(buffer, BUFFER_SIZE, stdin);
        buffer[strcspn(buffer, "\n")] = '\0';
        bytes_sent = send(socket, buffer, strlen(buffer), 0);
        if (bytes_sent == -1)
        {
            perror("Error sending message");
            break;
        }
    }
}
int main()
{
    int client_socket;
    struct sockaddr_in server_addr;
    client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (client_socket == -1)
    {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_addr.sin_port = htons(8000);
    if (connect(client_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1)
    {
        perror("Connection failed");
        exit(EXIT_FAILURE);
    }
    printf("Connected to the chat server\n");
    if (fork() == 0)
    {
        receive_messages(client_socket);
        exit(EXIT_SUCCESS);
    }
    send_messages(client_socket);
    close(client_socket);
}
