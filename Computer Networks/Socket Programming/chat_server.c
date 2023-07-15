#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#define MAX_CLIENTS 5
#define BUFFER_SIZE 1024
int clients[MAX_CLIENTS];
int num_clients = 0;
void handle_client(int client_socket)
{
    char buffer[BUFFER_SIZE];
    int bytes_received;
    while ((bytes_received = recv(client_socket, buffer, BUFFER_SIZE, 0)) > 0)
    {
        buffer[bytes_received] = '\0';
        printf("Received from client: %s\n", buffer);
        for (int i = 0; i < num_clients; i++)
        {
            if (clients[i] != client_socket)
            {
                send(clients[i], buffer, strlen(buffer), 0);
            }
        }
    }
    printf("Client disconnected\n");
    for (int i = 0; i < num_clients; i++)
    {
        if (clients[i] == client_socket)
        {
            memmove(clients + i, clients + i + 1, (num_clients - i - 1) * sizeof(int));
            break;
        }
    }
    num_clients--;
    close(client_socket);
}

int main()
{
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t addr_size;
    server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket == -1)
    {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(8000);
    if (bind(server_socket, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1)
    {
        perror("Socket binding failed");
        exit(EXIT_FAILURE);
    }
    if (listen(server_socket, MAX_CLIENTS) == -1)
    {
        perror("Socket listening failed");
        exit(EXIT_FAILURE);
    }

    printf("Chat server is listening for connections\n");
}
