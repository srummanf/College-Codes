import java.io.*;
import java.util.*;

public class MD5 {

  private static int[] s = {
    7,
    12,
    17,
    22,
    5,
    9,
    14,
    20,
    4,
    11,
    16,
    23,
    6,
    10,
    15,
    21,
  };
  private static int[] K = {
    0xd76aa478,
    0xe8c7b756,
    0x242070db,
    0xc1bdceee,
    0xf57c0faf,
    0x4787c62a,
    0xa8304613,
    0xfd469501,
    0x698098d8,
    0x8b44f7af,
    0xffff5bb1,
    0x895cd7be,
    0x6b901122,
    0xfd987193,
    0xa679438e,
    0x49b40821,
    0xf61e2562,
    0xc040b340,
    0x265e5a51,
    0xe9b6c7aa,
    0xd62f105d,
    0x02441453,
    0xd8a1e681,
    0xe7d3fbc8,
    0x21e1cde6,
    0xc33707d6,
    0xf4d50d87,
    0x455a14ed,
    0xa9e3e905,
    0xfcefa3f8,
    0x676f02d9,
    0x8d2a4c8a,
    0xfffa3942,
    0x8771f681,
    0x6d9d6122,
    0xfde5380c,
    0xa4beea44,
    0x4bdecfa9,
    0xf6bb4b60,
    0xbebfbc70,
    0x289b7ec6,
    0xeaa127fa,
    0xd4ef3085,
    0x04881d05,
    0xd9d4d039,
    0xe6db99e5,
    0x1fa27cf8,
    0xc4ac5665,
    0xf4292244,
    0x432aff97,
    0xab9423a7,
    0xfc93a039,
    0x655b59c3,
    0x8f0ccc92,
    0xffeff47d,
    0x85845dd1,
    0x6fa87e4f,
    0xfe2ce6e0,
    0xa3014314,
    0x4e0811a1,
    0xf7537e82,
    0xbd3af235,
    0x2ad7d2bb,
    0xeb86d391,
  };

  public static int[] round1(int[] M, int[] abcd) {
    int a = abcd[0];
    int b = abcd[1];
    int c = abcd[2];
    int d = abcd[3];

    a = FF(a, b, c, d, M[0], s[0], K[0]);
    d = FF(d, a, b, c, M[1], s[1], K[1]);
    c = FF(c, d, a, b, M[2], s[2], K[2]);
    b = FF(b, c, d, a, M[3], s[3], K[3]);
    a = FF(a, b, c, d, M[4], s[0], K[4]);
    d = FF(d, a, b, c, M[5], s[1], K[5]);
    c = FF(c, d, a, b, M[6], s[2], K[6]);
    b = FF(b, c, d, a, M[7], s[3], K[7]);
    a = FF(a, b, c, d, M[8], s[0], K[8]);
    d = FF(d, a, b, c, M[9], s[1], K[9]);
    c = FF(c, d, a, b, M[10], s[2], K[10]);
    b = FF(b, c, d, a, M[11], s[3], K[11]);
    a = FF(a, b, c, d, M[12], s[0], K[12]);
    d = FF(d, a, b, c, M[13], s[1], K[13]);
    c = FF(c, d, a, b, M[14], s[2], K[14]);
    b = FF(b, c, d, a, M[15], s[3], K[15]);

    return new int[] { a, b, c, d };
  }


  private static int FF(int a, int b, int c, int d, int x, int s, int k) {
    int f = (b & c) | (~b & d);
    return addModulo32(rotateLeft(a + f + x + k, s), b);
  }

  private static int GG(int a, int b, int c, int d, int x, int s, int k) {
    int g = (a & d) | (b & ~d);
    return addModulo32(rotateLeft(a + g + x + k, s), b);
  }

  private static int HH(int a, int b, int c, int d, int x, int s, int k) {
    int h = b ^ c ^ d;
    return addModulo32(rotateLeft(a + h + x + k, s), b);
  }

  private static int II(int a, int b, int c, int d, int x, int s, int k) {
    int i = c ^ (b | ~d);
    return addModulo32(rotateLeft(a + i + x + k, s), b);
  }

  private static int rotateLeft(int x, int n) {
    return (x << n) | (x >>> (32 - n));
  }

  private static int addModulo32(int a, int b) {
    return (a + b) & 0xffffffff;
  }

  public static void main(String[] args) {
    // Sample input message
    String message = "They are deterministic";

    // Convert message to bytes
    byte[] messageBytes = message.getBytes();

    // Pad and split message into 16 32-bit blocks
    int[] M = padAndSplit(messageBytes);
    System.out.println(M);

    // Initialize MD5 buffer with initial values
    int[] abcd = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476 };

    // Perform round 1
    abcd = round1(M, abcd);
    
    

    // Print the final MD5 hash
    printHash(abcd);
  }

//   private static int[] padAndSplit(byte[] messageBytes) {
//     // Implementation of padding and splitting the message goes here
//     return null; // Replace with the actual implementation
//   }
    private static int[] padAndSplit(byte[] messageBytes) {
    // Calculate the length of the message in bits
    long messageLengthBits = messageBytes.length * 8;

    // Create a new byte array to hold the padded message
    int numBlocks = ((messageBytes.length + 8) / 64) + 1;
    int totalLength = numBlocks * 64;
    byte[] paddedMessage = new byte[totalLength];

    // Copy the original message into the new array
    System.arraycopy(messageBytes, 0, paddedMessage, 0, messageBytes.length);

    // Pad the message with a single bit '1' followed by '0' bits
    paddedMessage[messageBytes.length] = (byte) 0x80;

    // Append the length of the original message in bits as a 64-bit value
    long lengthBits = messageLengthBits;
    for (int i = totalLength - 8; i < totalLength; i++) {
        paddedMessage[i] = (byte) (lengthBits & 0xFF);
        lengthBits >>>= 8;
    }

    // Split the padded message into 16 32-bit blocks
    int[] blocks = new int[16];
    for (int i = 0; i < 16; i++) {
        int offset = i * 4;
        int block = ((paddedMessage[offset] & 0xFF) | ((paddedMessage[offset + 1] & 0xFF) << 8) |
                    ((paddedMessage[offset + 2] & 0xFF) << 16) | ((paddedMessage[offset + 3] & 0xFF) << 24));
        blocks[i] = block;
    }

    return blocks;
}

//   private static void printHash(int[] abcd) {
//     // Implementation of converting the final buffer to a hexadecimal string goes here
//   }
private static void printHash(int[] abcd) {
    StringBuilder hashBuilder = new StringBuilder();
    for (int value : abcd) {
        hashBuilder.append(String.format("%08x", value));
    }
    String md5Hash = hashBuilder.toString();
    System.out.println("MD5 Hash: " + md5Hash);
}
}
