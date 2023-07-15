#include <stdio.h> #include <stdlib.h> #include <string.h> #include <sys/socket.h> #include <arpa/inet.h> #include <unistd.h> #define BUFFER_SIZE 1024
double perform_operation(double a, double b, char operator)
{
    switch (operator)
    {
    case '+':
        return a + b;
    case '-':
        return a - b;
    case '/':
        return a / b;
    case '*':
        return a * b;
    default:
        return 0.0;
    }
}
int main()
{
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t addr_size;
    char buffer[BUFFER_SIZE];
    double a, b, result;
    char operator;
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
    if (listen(server_socket, 1) == -1)
    {
        perror("Socket listening failed");
        exit(EXIT_FAILURE);
    }

    printf("Calculator server is listening for connections\n");
    addr_size = sizeof(client_addr);
    client_socket = accept(server_socket, (struct sockaddr *)&client_addr, &addr_size);
    if (client_socket == -1)
    {
        perror("Error accepting connection");
        exit(EXIT_FAILURE);
    }

    printf("Client connected\n");
    if (recv(client_socket, buffer, BUFFER_SIZE, 0) == -1)
    {
        perror("Error receiving data from client");
        exit(EXIT_FAILURE);
    }

    sscanf(buffer, "%lf %lf %c", &a, &b, &operator);
    printf("Received from client: a = %lf, b = %lf, operator = %c\n", a, b, operator);
    result = perform_operation(a, b, operator);
    printf("Result: %lf\n", result);
    snprintf(buffer, BUFFER_SIZE, "%lf", result);
    if (send(client_socket, buffer, strlen(buffer), 0) == -1)
    {
        perror("Error sending data to client");
        exit(EXIT_FAILURE);
    }
    close(client_socket);
    close(server_socket);
    return 0;
}
