#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netdb.h>
#define MAX_BUFFER_SIZE 1024
int main()
{
    int clientSocket;
    struct sockaddr_in serverAddress;
    clientSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (clientSocket == -1)
    {
        perror("Error: Could not create socket");
        exit(1);
    }
    struct hostent *server = gethostbyname("localhost");
    if (server == NULL)
    {
        perror("Error: Could not resolve hostname");
        exit(1);
    }
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_port = htons(8080);
    memcpy(&serverAddress.sin_addr.s_addr, server->h_addr, server->h_length);
    if (connect(clientSocket, (struct sockaddr *)&serverAddress, sizeof(serverAddress)) < 0)
    {
        perror("Error: Connection failed");
        exit(1);
    }
    char cidrAddress[MAX_BUFFER_SIZE];
    int numSubnetworks;
    printf("Enter IP address: ");
    fgets(cidrAddress, sizeof(cidrAddress), stdin);
    printf("Enter number of subnetworks: ");
    scanf("%d", &numSubnetworks);
    write(clientSocket, cidrAddress, strlen(cidrAddress));
    write(clientSocket, &numSubnetworks, sizeof(numSubnetworks));
    char serverResponse[MAX_BUFFER_SIZE];
    read(clientSocket, serverResponse, sizeof(serverResponse));
    printf("%s\n", serverResponse);
    close(clientSocket);
    return 0;
}
