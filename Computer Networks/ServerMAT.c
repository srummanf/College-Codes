#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define MAX_LINE_LENGTH 100
#define PORT 8080

int main() {
    FILE *file;
    char line[MAX_LINE_LENGTH];
    char hostname[MAX_LINE_LENGTH];
    char ip_address[MAX_LINE_LENGTH];
    char searchWord[MAX_LINE_LENGTH];

    // Open the file in read mode
    file = fopen("IP_config.txt", "r");
    if (file == NULL) {
        printf("Unable to open the file.\n");
        return 1;
    }

    // Create socket
    int server_fd, new_socket;
    struct sockaddr_in address;
    int addrlen = sizeof(address);
    
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("Socket creation failed");
        exit(EXIT_FAILURE);
    }
    
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);
    
    // Bind the socket to the specified port
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("Bind failed");
        exit(EXIT_FAILURE);
    }
    
    // Listen for incoming connections
    if (listen(server_fd, 3) < 0) {
        perror("Listen failed");
        exit(EXIT_FAILURE);
    }

    printf("Server is listening for incoming connections...\n");
    
    // Accept a new connection
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
        perror("Accept failed");
        exit(EXIT_FAILURE);
    }

    // Read the hostname from the client
    if (read(new_socket, searchWord, MAX_LINE_LENGTH) <= 0) {
        perror("Read failed");
        exit(EXIT_FAILURE);
    }

    // Read each line from the file
    while (fgets(line, MAX_LINE_LENGTH, file) != NULL) {
        // Extract the hostname and IP address
        sscanf(line, "%s %s", hostname, ip_address);

        // Check if the hostname matches the client's request
        if (strcmp(hostname, searchWord) == 0) {
            // Send the IP address to the client
            if (send(new_socket, ip_address, strlen(ip_address), 0) < 0) {
                perror("Send failed");
                exit(EXIT_FAILURE);
            }
            break;
        }
    }

    // Close the file
    fclose(file);

    // Close the socket
    close(server_fd);
    
    return 0;
}
