/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author kacpe
 */


public class Hash {
    
    public static byte[] genSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
    public static String hashPass(String input, byte[] salt) throws NoSuchAlgorithmException{
        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        
        byte[] bytes = md.digest(input.getBytes());
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i< bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
        }
        
        generatedPassword = sb.toString();
        
        return generatedPassword;
    }
    
    public static SecretKey generator() throws NoSuchAlgorithmException, InvalidKeySpecException{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey originalKey = keyGenerator.generateKey();
        return originalKey;
        }
    
    public static GCMParameterSpec vector(){
       byte[] iv = new byte[12];
       new SecureRandom().nextBytes(iv);
       return new GCMParameterSpec(128, iv);
    }
    
    public static String aesEncrypt(String input, SecretKey key,GCMParameterSpec vector, String algorithm ) throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, vector);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }
    
    public static String aesDecrypt(String input, SecretKey key, String algorithm, GCMParameterSpec vector) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException{
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, vector);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(plainText);
    }
    
}
