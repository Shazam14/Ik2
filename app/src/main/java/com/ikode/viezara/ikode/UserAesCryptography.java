package com.ikode.viezara.ikode;

import android.util.Base64;
import android.util.Log;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Viezara on 11/04/2016.
 */
public class UserAesCryptography {

    private final String provider = "BC";
    private final String keyGenAlgorithm = "PBKDF2WithHmacSHA1";
    private final String cryptoType = "AES";
    private final String algorithm = "AES/CBC/PKCS5Padding";
    private final String stringEncoding = "UTF-8";
    private final String delimeter =  " ";
    private final int iteration = 28;
    private final int keySize = 256;
    //private String password = "";

    //UserAesCryptography (String password) { this.password = password; }
    UserAesCryptography() {}

    //This method encrypts a message using a secret key
    public String encryptMessage (SecretKey secretKey, String message) throws Exception {
        byte[] iv = generateIV();

        Cipher cipher2 = Cipher.getInstance(algorithm);
        cipher2.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] encryptedTextBytes = org.apache.commons.codec.binary.Base64.encodeBase64(cipher2.doFinal(message.getBytes(stringEncoding)));

        //Log.v("AES ENCRYPTION TWO: ", new String(encryptedTextBytes).toString() + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(iv)) + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(salt)));

        return new String(encryptedTextBytes).toString() + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(iv));
    }

    //This method decrypts the encrypted message
    public String decryptMessages (String secretKey, String encryptedMessage) throws Exception {

        byte[] iv = this.getIV(encryptedMessage);
        byte[] message = this.getEncryptedMessage(encryptedMessage);

        byte[] key = org.apache.commons.codec.binary.Base64.decodeBase64(secretKey.getBytes());

        SecretKey originalKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(iv));
        String plaintext = new String(cipher.doFinal(message), stringEncoding);
        //Log.v("FINAL", plaintext);
        return plaintext;
    }

    //This method generate a random 16 byte salt for the encryption
    private byte[] generateSalt () {

        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    //This method generate a random 16 byte initialization vector for the encryption
    private byte[] generateIV () {

        SecureRandom random = new SecureRandom();
        byte iv[] = new byte[16]; //generate random 16 byte IV AES is always 16bytes
        random.nextBytes(iv);

        return iv;
    }

    //This method gets the encrypted message
    private byte[] getEncryptedMessage (String encryptedMessage) {

        String[] message = encryptedMessage.split(delimeter);

        byte[] ConvertedEncryptedMessage = Base64.decode(message[0], Base64.DEFAULT);

        return ConvertedEncryptedMessage;
    }

    //This method gets the initialization vector
    private byte[] getIV (String encryptedMessage) {

        String[] stringIv = encryptedMessage.split(delimeter);

        byte[] iv = Base64.decode(stringIv[1], Base64.DEFAULT);

        return iv;
    }

    //This method converts the secret key generated into a string to be sent to the server
    public static String convertSecretKeyToString(SecretKey secretKey){
        String key = new String(org.apache.commons.codec.binary.Base64.encodeBase64(secretKey.getEncoded()));
        return key;
    }

    //This method generates a secret key used for encryption
    //public SecretKey generateKey (String password)  throws Exception {
    public SecretKey generateKey ()  throws Exception {
        byte[] salt = this.generateSalt();
        String password = this.random();
        SecretKeyFactory factory = SecretKeyFactory.getInstance(this.keyGenAlgorithm, this.provider);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, this.iteration, this.keySize);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), cryptoType);

        return secret;
    }
    public String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = 10;
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

}
