#include <stdio.h>
#include <stdint.h>

// IPv4 Header structure
typedef struct {
    uint8_t version;
    uint8_t ihl;
    uint16_t totalLength;
    uint16_t identification;
    uint16_t flagsAndOffset;
    uint8_t ttl;
    uint8_t protocol;
    uint16_t headerChecksum;
    uint32_t sourceAddress;
    uint32_t destinationAddress;
} IPv4Header;

// Function to convert IP address from string format to integer format
uint32_t convertIPtoInteger(const char* ipAddress) {
    uint32_t result = 0;
    int ipParts[4];

    sscanf(ipAddress, "%d.%d.%d.%d", &ipParts[0], &ipParts[1], &ipParts[2], &ipParts[3]);

    result = (ipParts[0] << 24) | (ipParts[1] << 16) | (ipParts[2] << 8) | ipParts[3];

    return result;
}

// Function to convert IP address from integer format to string format
void convertIPtoString(uint32_t ipAddress, char* result) {
    sprintf(result, "%d.%d.%d.%d", (ipAddress >> 24) & 0xFF, (ipAddress >> 16) & 0xFF, (ipAddress >> 8) & 0xFF, ipAddress & 0xFF);
}

int main() {
    IPv4Header originalHeader;

    char sourceIP[16];
    printf("Enter the source IP address (e.g., 192.168.1.1): ");
    scanf("%s", sourceIP);
    originalHeader.sourceAddress = convertIPtoInteger(sourceIP);

//    char destinationIP[16];
//    printf("Enter the destination IP address (e.g., 192.168.1.2): ");
//    scanf("%s", destinationIP);
//    originalHeader.destinationAddress = convertIPtoInteger(destinationIP);

    printf("Enter the number of LANs: ");
    int numLans;
    scanf("%d", &numLans);

    int i;
    int totalFragments = 0;

    for (i = 0; i < numLans; i++) {
        uint16_t mtu;
        printf("Enter the MTU for LAN %d: ", i + 1);
        scanf("%hu", &mtu);

        uint16_t maxFragmentSize = mtu - sizeof(IPv4Header);
        int numFragments = (originalHeader.totalLength + maxFragmentSize - 1) / maxFragmentSize;
        totalFragments += numFragments;

        printf("\nLAN %d Fragments:\n", i + 1);

        int j;
        int offset = 0;

        for (j = 0; j < numFragments; j++) {
            IPv4Header fragmentHeader = originalHeader;
            fragmentHeader.totalLength = (j != numFragments - 1) ? maxFragmentSize + sizeof(IPv4Header) : originalHeader.totalLength - offset + sizeof(IPv4Header);
            fragmentHeader.flagsAndOffset = (offset / 8) << 3;

            // Calculate M (More Fragments) flag
            fragmentHeader.flagsAndOffset |= (j != numFragments - 1) << 5;

            // Calculate D (Don't Fragment) flag
            fragmentHeader.flagsAndOffset |= (numFragments == 1) << 6;

            char fragmentSourceIP[16];
            convertIPtoString(fragmentHeader.sourceAddress, fragmentSourceIP);

            char fragmentDestIP[16];
            convertIPtoString(fragmentHeader.destinationAddress, fragmentDestIP);

            printf("Fragment %d:\n", j + 1);
            printf("Total Length: %d\n", fragmentHeader.totalLength);
            printf("M (More Fragments) Flag: %d\n", (fragmentHeader.flagsAndOffset >> 5) & 1);
            printf("D (Don't Fragment) Flag: %d\n", (fragmentHeader.flagsAndOffset >> 6) & 1);
            printf("Fragment Offset: %d\n", fragmentHeader.flagsAndOffset >> 3);
            printf("Source IP: %s\n", fragmentSourceIP);
            printf("\n -x- \n");
//            printf("Destination IP: %s\n\n", fragmentDestIP);

            offset += maxFragmentSize;
        }
    }

    printf("Total Fragments: %d\n", totalFragments);

    return 0;
}

