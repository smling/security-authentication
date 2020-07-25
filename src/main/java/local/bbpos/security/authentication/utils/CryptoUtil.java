/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.bbpos.security.authentication.utils;

import com.fasterxml.uuid.Generators;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

/**
 * Crypto class to encrypt / decrypt content.
 * @author Ling
 */
public class CryptoUtil {
    protected static final String ENCRYPTION_TRANSFORMATION="RSA/ECB/PKCS1Padding";
    protected static final int ENCRYPTION_MAX_ENCRYPT_BLOCK=512;
    protected static final String ENCRYPTION_DEFAULT_FORMAT="UTF-8";
    protected static final String HASH_DEFAULT_FORMAT="SHA-256";
    
    public static byte[] encryptString(Key key, String value) throws Exception{
        byte[] result={};
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherBytes=cipher.doFinal(value.getBytes());
            //result=Base64.getEncoder().encodeToString(cipherBytes);
            result=cipherBytes;
        } catch (Exception ex) {
            throw new Exception("Exception thrown during encryption.", ex);
        }
        return result;
    }
    public static String decryptString(Key key, byte[] encryptedString) throws Exception {
        String result="";
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cipherBytes = cipher.doFinal(encryptedString);
            /*
            byte[] cipherBytes=encryptedString;
            int inputLength=cipherBytes.length;
            int offSet = 0;
            byte[] cache = {};
            while (inputLength - offSet > 0) {
		if (inputLength - offSet > ENCRYPTION_MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(cipherBytes, offSet, ENCRYPTION_MAX_ENCRYPT_BLOCK);
                    offSet += ENCRYPTION_MAX_ENCRYPT_BLOCK;
		} else {
                    cache = cipher.doFinal(cipherBytes, offSet, inputLength - offSet);
                    offSet = inputLength;
		}
		cipherBytes = Arrays.copyOf(cipherBytes, cipherBytes.length + cache.length);
		System.arraycopy(cache, 0, cipherBytes, cipherBytes.length - cache.length, cache.length);
            }*/
            result=new String(cipherBytes); 
        } catch (Exception ex) {
            throw new Exception("Exception thrown during decryption.", ex);
        }
        return result;
    }  
    
    public static String hashString(String value) throws Exception {
        String result="";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_DEFAULT_FORMAT);
            byte[] digestBytes=messageDigest.digest(value.getBytes());
            result=Base64.getEncoder().encodeToString(digestBytes);
        } catch (Exception ex) {
            throw new Exception("Exception thrown during hash.", ex);
        }
        return result;
    }
    
    public static UUID generateTimeBasedUUID() {
        return Generators.timeBasedGenerator().generate();
    }
    
    public static UUID generateRandomBasedUUID() {
        return Generators.randomBasedGenerator(new Random()).generate();
    }
    
    public static KeyPair GenerateRSAKey() throws Exception {
        KeyPair result=null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);

            // Generate the KeyPair
            result = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new Exception("Exception thrown during generate RSA key.", ex);
        }
        return result;
    }
    public static String generatePasswordSalt() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);
        String result=Base64.getEncoder().encodeToString(randomBytes);
        return result;
    }
}
