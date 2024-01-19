private static String decrypt(String input, SecretKey secretKey) {
    try {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    } catch (Exception e) {
        e.printStackTrace();
        return "Error decrypting message";
    }
}
