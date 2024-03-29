import java.security.*;

public class DigitalSignatureUtils {
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(2048, random);
        return keyGen.generateKeyPair();
    }
    
    public static byte[] signData(byte[] data, PrivateKey privKey) throws Exception {
        Signature signer = Signature.getInstance("SHA256withDSA");
        signer.initSign(privKey);
        signer.update(data);
        return signer.sign();
    }

    public static boolean verifySignature(byte[] data, byte[] signature, PublicKey pubKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withDSA");
        verifier.initVerify(pubKey);
        verifier.update(data);
        return verifier.verify(signature);
    }
}
