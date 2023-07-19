#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define MAX_LINE_LENGTH 100
#define PORT 8080

int main() {
    char searchWord[MAX_LINE_LENGTH];
    char ip_address[MAX_LINE_LENGTH];

    // Create socket
    int sock = 0;
    struct sockaddr_in serv_addr;
    
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        printf("Socket creation failed.\n");
        return -1;
    }
    
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(PORT);
    
    // Convert IP address from string to binary form
    if (inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr) <= 0) {
        printf("Invalid address/ Address not supported.\n");
        return -1;
    }
    
    // Connect to the server
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        printf("Connection failed.\n");
        return -1;
    }

    printf("Enter the hostname: ");
    scanf("%s", searchWord);

    // Send the hostname to the server
    if (send(sock, searchWord, strlen(searchWord), 0) < 0) {
        printf("Send failed.\n");
        return -1;
    }

    // Receive the IP address from the server
    if (read(sock, ip_address, MAX_LINE_LENGTH) < 0) {
        printf("Read failed.\n");
        return -1;
    }

    printf("IP address: %s\n", ip_address);

    return 0;
}
