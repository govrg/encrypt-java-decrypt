import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Scanner;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncryptJavaDecrypt {

    private static final String ALGO = "AES";
    private byte[] keyValue;

    public EncryptJavaDecrypt (String key) {
        keyValue = key.getBytes();
    }

    public String encrypt (String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public String decrypt (String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decoderValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decoderValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static void main(String[] args){
        String s1, s2, s3 = "";
        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Insert your 16 digits key ");
            s1 = in.nextLine();
            EncryptJavaDecrypt aes = new EncryptJavaDecrypt(s1);
            System.out.println("Insert your string to be encrypted ");
            s2 = in.nextLine();
            if(s2.isEmpty()) {
                s2 = "Empty data";
            }
            String encdata = aes.encrypt(s2);
            System.out.println("encrypt data " + encdata);
            System.out.println("Insert your encrypted string to be decrypted ");
            s3 = in.nextLine();
            if(s3.isEmpty()) {
                s3 = encdata;
            }
            String decdata = aes.decrypt(s3);
            System.out.println("decrypt data " + decdata);
        } catch (Exception e){
            Logger.getLogger(EncryptJavaDecrypt.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
