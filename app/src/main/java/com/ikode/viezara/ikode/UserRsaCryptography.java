package com.ikode.viezara.ikode;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;


/**
 * Created by Viezara on 21/04/2016.
 */
public class UserRsaCryptography {

    private static final String provider = "BC";
    private static final String keyPairAlgorithm = "RSA";
    private static final String encryptionAlgorithm = "RSA/None/OAEPWithSHA256AndMGF1Padding";
    private static final String KeyPairAlias = "KeyPair";

    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    private final String header = "-----BEGIN PUBLIC KEY-----\n";
    private final String footer = "\n-----END PUBLIC KEY-----";

    UserRsaCryptography () {
        //sharedPreferences = context.getSharedPreferences(KeyPairAlias, Context.MODE_PRIVATE);
    }

    //Method on generating key pair (Public and Private key).
    //After generating, the keys will be saved in the shared preferences(Globally available).
    //PRIVATE KEY: Base 64 encoded (DEFAULT)
    //PUBLIC KEY: Base 64 eencoded (Using commons apache)
    //PRIVATE KEY BYTE: Byte format to be used for password AES

    public boolean generateKeyPair () throws Exception{
        sharedPreferencesEditor = sharedPreferences.edit();
        boolean checker = false;

        if (!sharedPreferences.contains("PrivateKey") && !sharedPreferences.contains("PublicKey")) {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(keyPairAlgorithm, provider);
            //generator.initialize(256, new SecureRandom());
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();

            publicKey = pair.getPublic();
            privateKey = pair.getPrivate();

            byte[] pubKeyByte = publicKey.getEncoded();
            String pubKeyString = new String(Base64.encode(pubKeyByte, Base64.DEFAULT));
            String convertedPubKey = this.convertToPEM(pubKeyString);
            String finalPublicKey = new String(org.apache.commons.codec.binary.Base64.encodeBase64(convertedPubKey.getBytes()));

            byte[] privKeyByte = privateKey.getEncoded();
            String privKeyString = new String(Base64.encode(privKeyByte, Base64.DEFAULT));

            //Log.v("USER_RSA", "PublicKey " + finalPublicKey);
            //Log.v("USER_RSA", "PrivateKey " + privKeyString);
            sharedPreferencesEditor.putString("PublicKey",  finalPublicKey);
            sharedPreferencesEditor.putString("PrivateKey", privKeyString);
            sharedPreferencesEditor.putString("PrivateKeyByte", privateKey.getEncoded().toString());

            sharedPreferencesEditor.commit();

            return true;

        }else{
            return false;
        }

    }

    //Method for clearing all the data in shared preferences.
    //DATA: (PublicKey), (PrivateKey), and (PrivateKeyByte).

    public static void clearPreferences(Context context) {
        Log.v("USER_RSA", "PKey Cleared ");
        sharedPreferences = context.getSharedPreferences(KeyPairAlias, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.commit();
    }

    //Method for decrypting encrypted message by the public key.

    public static String decryptWithPrivateKey(Context context, String encryptedMessage) throws Exception{

        sharedPreferences = context.getSharedPreferences(KeyPairAlias, Context.MODE_PRIVATE);
        String container = "";

        if (sharedPreferences.contains("PrivateKey") && sharedPreferences.contains("PublicKey")) {
            String key = sharedPreferences.getString("PrivateKey", "");

            byte[] sigBytes = Base64.decode(key, Base64.DEFAULT);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(sigBytes);
            KeyFactory factory = KeyFactory.getInstance(keyPairAlgorithm, provider);
            PrivateKey finalPrivKey = factory.generatePrivate(spec);

            Cipher cipher = Cipher.getInstance(encryptionAlgorithm, provider);
            cipher.init(Cipher.DECRYPT_MODE, finalPrivKey);
            cipher.init(Cipher.DECRYPT_MODE, finalPrivKey);
            byte[] message = cipher.doFinal(Base64.decode(encryptedMessage, Base64.DEFAULT));
            //Log.v("RESULT", new String(message));

            container = new String(message); //WEB Publickey


        } else {
            //If there is no keys in shared preferences it will throw an error

            throw new NullPointerException();

        }

        return container;
    }

    //Method for decrypting encrypted message by the private key.

    public String decryptWithPrivateKey(String encryptedMessage) throws Exception{

        String key = sharedPreferences.getString("PrivateKey", "");
        byte[] sigBytes = Base64.decode(key, Base64.DEFAULT);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(sigBytes);
        KeyFactory factory = KeyFactory.getInstance(keyPairAlgorithm, provider);
        PrivateKey finalPrivKey = factory.generatePrivate(spec);

        Cipher cipher = Cipher.getInstance(encryptionAlgorithm, provider);
        cipher.init(Cipher.DECRYPT_MODE, finalPrivKey);
        cipher.init(Cipher.DECRYPT_MODE, finalPrivKey);
        byte[] message = cipher.doFinal(Base64.decode(encryptedMessage, Base64.DEFAULT));
        //Log.v("RESULT", new String(message));

        //this.clearPreferences();

        return new String(message);
    }

    //This method encrypt a string message using the public key from the server

    public static String encryptWithServerPublicKey(String message, String serverPublicKey) throws Exception{
        String container = "";

        // create the key factory
        KeyFactory kFactory = KeyFactory.getInstance(keyPairAlgorithm, new BouncyCastleProvider());

        // decode base64 of your key
        byte yourKey[] =  Base64.decode(serverPublicKey, Base64.DEFAULT);

        // generate the public key
        X509EncodedKeySpec spec =  new X509EncodedKeySpec(yourKey);
        PublicKey publicKey = (PublicKey) kFactory.generatePublic(spec);

        Cipher cipher = Cipher.getInstance(encryptionAlgorithm, provider);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherData = org.apache.commons.codec.binary.Base64.encodeBase64(cipher.doFinal(message.getBytes()));

        container = new String(cipherData);

        return container;
    }

    //Method for converting DER formatted key into PEM format
    //so that apache can encrypt using the public key.

    private String convertToPEM(String key) {

        String[] splittedPubKey =  key.split("\n");
        String con = "";

        for (int x=0; x<splittedPubKey.length; x++) {
            con += splittedPubKey[x];
        }

        String pemFormat = header + new String(con).replaceAll("(.{64})", "$1\n") + footer;
        //Log.v("PEM FORMAT", pemFormat);

        return pemFormat;
    }

    //This method gets the public key generated which is stored in sharedpreferences

    public static String getPublicKey(Context context) {
        sharedPreferences = context.getSharedPreferences(KeyPairAlias, Context.MODE_PRIVATE);
        String key = sharedPreferences.getString("PublicKey", "");

        return key;
    }

    //This method gets the private key (Byte Format) generated which is stored in sharedpreferences
    //That will be used in AES encryption

    public static String getPrivateKey(Context context) {
        sharedPreferences = context.getSharedPreferences(KeyPairAlias, Context.MODE_PRIVATE);
        String key = sharedPreferences.getString("PrivateKeyByte", "");

        return key;
    }

    //This method checks if there is an existing keys in shared preferences.
    //Returns true if there is data else it return false if empty

    public static boolean checkIfKeyExist(Context context) {
        sharedPreferences = context.getSharedPreferences(KeyPairAlias, Context.MODE_PRIVATE);
        boolean checker = false;

        if(sharedPreferences.contains("PrivateKey") && sharedPreferences.contains("PublicKey")) {
            checker = true;
        }

        return checker;
    }

}
