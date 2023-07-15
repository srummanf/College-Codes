#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

#define BUFFER_SIZE 1024

int main()
{
    int client_socket;
    struct sockaddr_in server_addr;
    char buffer[BUFFER_SIZE];
    double a, b;
    char operator;
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

    printf("Connected to the calculator server\n");
    printf("Enter value for 'a': ");
    scanf("%lf", &a);
    printf("Enter value for 'b': ");
    scanf("%lf", &b);
    printf("Enter the operator (+, -, /, *): ");
    scanf(" %c", &operator);
    snprintf(buffer, BUFFER_SIZE, "%lf %lf %c", a, b, operator);
    if (send(client_socket, buffer, strlen(buffer), 0) == -1)
    {
        perror("Error sending data to server");
        exit(EXIT_FAILURE);
    }
    if (recv(client_socket, buffer, BUFFER_SIZE, 0) == -1)
    {
        perror("Error receiving data from server");
        exit(EXIT_FAILURE);
    }
    printf("Result received from server: %s\n", buffer);
    close(client_socket);

    return 0;
}
