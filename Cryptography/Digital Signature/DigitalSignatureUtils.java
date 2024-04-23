import java.security.*;

/**
 * This class provides utility methods for digital signature operations using
 * the Digital Signature Algorithm (DSA) and the SHA-256 hashing algorithm.
 */
public class DigitalSignatureUtils {

    /**
     * Generates a new DSA key pair with a key size of 2048 bits.
     *
     * @return A KeyPair object containing the generated public and private keys.
     * @throws Exception If there is an error during key generation.
     */
    public static KeyPair generateKeyPair() throws Exception {
        // Create a KeyPairGenerator instance for the DSA algorithm
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

        // Create a SecureRandom instance using the SHA1PRNG algorithm
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Initialize the KeyPairGenerator with a key size of 2048 bits and the SecureRandom instance
        keyGen.initialize(2048, random);

        // Generate the key pair
        return keyGen.generateKeyPair();
    }

    /**
     * Signs the given data using the provided private key and the SHA-256 with DSA signature algorithm.
     *
     * @param data    The data to be signed.
     * @param privKey The private key to use for signing.
     * @return The digital signature as a byte array.
     * @throws Exception If there is an error during the signing process.
     */
    public static byte[] signData(byte[] data, PrivateKey privKey) throws Exception {
        // Create a Signature instance for the SHA-256 with DSA algorithm
        Signature signer = Signature.getInstance("SHA256withDSA");

        // Initialize the signer with the provided private key for signing
        signer.initSign(privKey);

        // Update the signer with the data to be signed
        signer.update(data);

        // Generate the digital signature
        return signer.sign();
    }

    /**
     * Verifies the digital signature of the given data using the provided public key.
     *
     * @param data      The data whose signature is to be verified.
     * @param signature The digital signature to be verified.
     * @param pubKey    The public key to use for verification.
     * @return true if the signature is valid for the given data and public key, false otherwise.
     * @throws Exception If there is an error during the verification process.
     */
    public static boolean verifySignature(byte[] data, byte[] signature, PublicKey pubKey) throws Exception {
        // Create a Signature instance for the SHA-256 with DSA algorithm
        Signature verifier = Signature.getInstance("SHA256withDSA");

        // Initialize the verifier with the provided public key for verification
        verifier.initVerify(pubKey);

        // Update the verifier with the data to be verified
        verifier.update(data);

        // Verify the digital signature
        return verifier.verify(signature);
    }
}