/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

/**
 *
 * @author kacpe
 */
public class KeyStoreDb {
    public static void saveKey(SecretKey key, String alias) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException{
        char [] pwd = "haslmaslo".toCharArray();
        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(null, pwd);
        
        KeyStore.SecretKeyEntry entry = new KeyStore.SecretKeyEntry(key);
        KeyStore.PasswordProtection protection = new KeyStore.PasswordProtection(pwd);
        
        ks.setEntry(alias, entry, protection);
        
        try (FileOutputStream fos = new FileOutputStream("newKeyStoreFileName.jks")) {
            ks.store(fos, pwd);
}

    }
    
    public static SecretKey loadKey(String alias) throws FileNotFoundException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException{
        KeyStore ks = KeyStore.getInstance("JCEKS");
         char [] pwd = "haslmaslo".toCharArray();
        
        try(FileInputStream fis = new FileInputStream("newKeyStoreFileName.jks")){
            ks.load(fis, pwd);
        }
        KeyStore.PasswordProtection protection = new KeyStore.PasswordProtection(pwd);
        KeyStore.SecretKeyEntry entry = (KeyStore.SecretKeyEntry) ks.getEntry(alias, protection);
        return entry.getSecretKey();
    }
    
}
