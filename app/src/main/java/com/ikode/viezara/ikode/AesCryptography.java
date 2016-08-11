<<<<<<< Updated upstream
package com.ikode.viezara.ikode;

import android.util.Base64;
import android.util.Log;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Viezara on 11/04/2016.
 */
public class AesCryptography {

    private final String provider = "BC";
    private final String keyGenAlgorithm = "PBKDF2WithHmacSHA1";
    private final String cryptoType = "AES";
    private final String algorithm = "AES/CBC/PKCS5Padding";
    private final String delimeter =  "]";
    private final int iteration = 65536;
    private final int keySize = 256;
    private String password = "";

    AesCryptography (String password) { this.password = password; }

    public String encryptMessage (String message) throws Exception {
        byte[] salt = generateSalt();
        byte[] iv = generateIV();

        //Dont forget to append the salt and iv along with the encrypted message

        SecretKey key = generateKey( this.password, salt ); //put inputed password in whatever (this.password)

        Cipher cipher2 = Cipher.getInstance(algorithm);
        cipher2.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encryptedTextBytes =
                org.apache.commons.codec.binary.Base64.encodeBase64(cipher2.doFinal(message.getBytes("UTF-8")));

        return new String(encryptedTextBytes).toString() + delimeter
                + new String(org.apache.commons.codec.binary.Base64.encodeBase64(iv)) + delimeter
                + new String(org.apache.commons.codec.binary.Base64.encodeBase64(salt));
    }

    public String decryptMessage (String fullEncryptedMessage) throws Exception {

        SecretKey key = generateKey(this.password, getSalt(fullEncryptedMessage));
        byte[] encryptedMessage = getAesMessage(fullEncryptedMessage);
        byte[] iv = getIV(fullEncryptedMessage);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        String plaintext = new String(cipher.doFinal(encryptedMessage), "UTF-8");

        Log.v("AES DECRYPTION TWO: ", plaintext);

        return plaintext;
    }

    //this is just for encryption
    private byte[] generateSalt () {

        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    //this is just for encryption
    private byte[] generateIV () {

        SecureRandom random = new SecureRandom();
        byte iv[] = new byte[16]; //generate random 16 byte IV AES is always 16bytes
        random.nextBytes(iv);

        return iv;
    }

    private byte[] getAesMessage (String encryptedMessage) {

        //use encryptedMessageToArray method to get the aes encrypted message

        String[] message = encryptedMessageToArray(encryptedMessage);

        byte[] ConvertedEncryptedMessage = Base64.decode(message[0], Base64.DEFAULT);

        return ConvertedEncryptedMessage;
    }

    //this is just for decryption
    private byte[] getSalt (String encryptedMessage) {

        //use encryptedMessageToArray method to get salt
        String[] message = encryptedMessageToArray(encryptedMessage);

        return message[2].getBytes();
    }

    private byte[] getIV (String encryptedMessage) {

        //use encryptedMessageToArray method to get iv
        String[] message = encryptedMessageToArray(encryptedMessage);

        byte[] iv = Base64.decode(message[1], Base64.DEFAULT);

        return iv;
    }

    //this is just for decryption
    private String[] encryptedMessageToArray (String fullEncryptedMessage) {

        //explode the full encrypted message and return as an array
        String[] messages = fullEncryptedMessage.split(delimeter);

        return messages;
    }

    private SecretKey generateKey (String password, byte[] salt)  throws Exception {

        SecretKeyFactory factory = SecretKeyFactory.getInstance(this.keyGenAlgorithm, this.provider);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, this.iteration, this.keySize);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), cryptoType);

        return secret;
    }
}
=======
package com.ikode.viezara.ikode;

import android.util.Base64;
import android.util.Log;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;

/**
 * Created by Viezara on 11/04/2016.
 */
public class AesCryptography {

    private final String provider = "BC";
    private final String keyGenAlgorithm = "PBKDF2WithHmacSHA1";
    private final String cryptoType = "AES";
    private final String algorithm = "AES/CBC/PKCS5Padding";
    private final String stringEncoding = "UTF-8";
    private final String delimeter = " ";
    private final int iteration = 28;
    private final int keySize = 256;
    private String password = "";

    AesCryptography(String password) {
        this.password = password;
    }

    public String encryptMessage(String message) throws Exception {
        byte[] salt = generateSalt();
        byte[] iv = generateIV();

        //Dont forget to append the salt and iv along with the encrypted message

        SecretKey key = generateKey(this.password, salt); //put inputed password in whatever (this.password)
        //SecretKey k = keyGen(this.password, salt);

        Cipher cipher2 = Cipher.getInstance(algorithm);
        cipher2.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encryptedTextBytes = org.apache.commons.codec.binary.Base64.encodeBase64(cipher2.doFinal(message.getBytes(stringEncoding)));

        //Log.v("AES ENCRYPTION TWO: ", new String(encryptedTextBytes).toString() + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(iv)) + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(salt)));

        return new String(encryptedTextBytes).toString() + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(iv)) + delimeter + new String(org.apache.commons.codec.binary.Base64.encodeBase64(salt));
    }

    public String decryptMessage(String fullEncryptedMessage) throws Exception {

        SecretKey key = generateKey(this.password, getSalt(fullEncryptedMessage));
        byte[] encryptedMessage = getAesMessage(fullEncryptedMessage);
        byte[] iv = getIV(fullEncryptedMessage);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        String plaintext = new String(cipher.doFinal(encryptedMessage), stringEncoding);

        return plaintext;
    }

    //this is just for encryption
    private byte[] generateSalt() {

        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    //this is just for encryption
    private byte[] generateIV() {

        SecureRandom random = new SecureRandom();
        byte iv[] = new byte[16]; //generate random 16 byte IV AES is always 16bytes
        random.nextBytes(iv);

        return iv;
    }

    private byte[] getAesMessage(String encryptedMessage) {

        //use encryptedMessageToArray method to get the aes encrypted message
        String[] message = encryptedMessageToArray(encryptedMessage);

        byte[] ConvertedEncryptedMessage = Base64.decode(message[0], Base64.DEFAULT);

        return ConvertedEncryptedMessage;
    }

    //this is just for decryption
    private byte[] getSalt(String encryptedMessage) {

        //use encryptedMessageToArray method to get salt
        String[] message = encryptedMessageToArray(encryptedMessage);

        return message[2].getBytes();
    }

    private byte[] getIV(String encryptedMessage) {

        //use encryptedMessageToArray method to get iv
        String[] message = encryptedMessageToArray(encryptedMessage);

        byte[] iv = Base64.decode(message[1], Base64.DEFAULT);

        return iv;
    }

    //this is just for decryption
    private String[] encryptedMessageToArray(String fullEncryptedMessage) {

        //explode the full encrypted message and return as an array
        String[] messages = fullEncryptedMessage.split(delimeter);

        return messages;
    }

    private SecretKey generateKey(String password, byte[] salt) throws Exception {

        SecretKeyFactory factory = SecretKeyFactory.getInstance(this.keyGenAlgorithm, this.provider);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, this.iteration, this.keySize);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), cryptoType);

        return secret;
    }
/**
 private SecretKey keyGen (String password, byte[] salt) throws Exception {

 //SecretKeyFactory factory = SecretKeyFactory.getInstance(this.keyGenAlgorithm, this.provider);
 // KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, this.iteration, this.keySize);
 //byte[] tmp = factory.generateSecret(spec).getEncoded();
 //SecretKey secret = new SecretKeySpec(tmp.getEncoded(), cryptoType);

 //return tmp;

 PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
 generator.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), salt, 4096);
 KeyParameter dk = (KeyParameter) generator.generateDerivedMacParameters(256);
 SecretKey key = new SecretKeySpec(dk.getKey(), cryptoType);

 return key;
 }

 public String enc (String message) throws Exception{
 byte[] salt = generateSalt();
 byte[] iv = generateIV();

 return "";
 }
 */
}
>>>>>>> Stashed changes
