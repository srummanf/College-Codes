// #include <stdio.h>
// int main()
// {
//     int w, i, f, frames[50];
//     printf("Enter window size: ");
//     scanf("%d", &w);
//     printf("\nEnter number of frames to transmit: ");
//     scanf("%d", &f);
//     printf("\nEnter %d frames: ", f);
//     for (i = 1; i <= f; i++)
//         scanf("%d", &frames[i]);
//     printf("\nWith sliding window protocol the frames will be sent in the following manner (assuming no corruption of frames)\n\n");
//     printf("After sending %d frames at each stage sender waits for acknowledgement sent by the receiver\n\n", w);
//     for (i = 1; i <= f; i++)
//     {
//         if (i % w == 0)
//         {
//             printf("%d\n", frames[i]);
//             printf("Acknowledgement of above frames sent is received by sender\n\n");
//         }
//         else
//             printf("%d ", frames[i]);
//     }
//     if (f % w != 0)
//         printf("\nAcknowledgement of above frames sent is received by sender\n");
//     return 0;
// }

#include <iostream>

using namespace std;

// Function to simulate sliding window protocol
void slidingWindow(int numOfBits, int sf, int sn, int ackReceived, int numOfFrames)
{
    int windowSize = 1; // Window size

    cout << "Sliding Window Protocol Simulation" << endl;
    cout << "---------------------------------" << endl;
    cout << "Number of bits for frames: " << numOfBits << endl;
    cout << "Initial Range of Frame Numbers: [" << sf << ", " << sf + windowSize - 1 << "]" << endl;
    cout << "Initial Sf: " << sf << endl;
    cout << "Initial Sn: " << sn << endl;
    cout << "ACK received: " << ackReceived << endl;
    cout << "Number of frames transmitted: " << numOfFrames << endl;
    cout << endl;

    // Simulate transmission and acknowledgment process
    for (int base = sf; base <= numOfFrames; base += windowSize)
    {
        // Send frames
        cout << "Sending frames in range [" << base << ", " << min(base + windowSize - 1, numOfFrames) << "]" << endl;

        // Receive ACK
        int ack;
        cout << "Enter the last ACK received: ";
        cin >> ack;

        if (ack == sn)
        {
            // Move the window
            sf = sn;
            sn++;
            cout << "ACK received for frame " << ack << "." << endl;
            cout << "Updated Range of Frame Numbers: [" << sf << ", " << sf + windowSize - 1 << "]" << endl;
            cout << "Updated Sf: " << sf << endl;
            cout << "Updated Sn: " << sn << endl;
            cout << "Updated Window Size: " << windowSize << endl;
        }
        else
        {
            // Resend frames starting from base
            cout << "ACK not received for frame " << ack << ". Resending frames in range [" << base << ", " << min(base + windowSize - 1, numOfFrames) << "]" << endl;
        }

        cout << endl;
    }

    cout << "All frames transmitted successfully!" << endl;
}

int main()
{
    int numOfBits, sf, sn, ackReceived, numOfFrames;

    // Get user inputs
    cout << "Enter the number of bits required for frames: ";
    cin >> numOfBits;
    cout << "Enter the value of Sf: ";
    cin >> sf;
    cout << "Enter the value of Sn: ";
    cin >> sn;
    cout << "Enter the ACK received: ";
    cin >> ackReceived;
    cout << "Enter the number of frames transmitted: ";
    cin >> numOfFrames;
    cout << endl;

    // Call slidingWindow function
    slidingWindow(numOfBits, sf, sn, ackReceived, numOfFrames);

    return 0;
}
