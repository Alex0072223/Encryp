import javax.crypto.*;
import java.io.*;
import java.security.*;

public class Main {
    public static void main(String[] args) throws Exception {
     /*   // String text = "secret!!secret!!secret!!secret!!";
       // Path text = Path.of("c:\\projects\\note.txt");
        File text = new File("C://SomeDir", "Hello.txt");
        long s = (byte) text.length();

        // Generate new key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        Key key = keygen.generateKey();
        // Encrypt with key
        String transformation = "AES/ECB/PKCS5Padding";
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(s.getBytes());
        System.out.println(DatatypeConverter.printHexBinary(encrypted));
        // Decrypt with key
        cipher.init(Cipher.DECRYPT_MODE, key);
        String result = new String(cipher.doFinal(encrypted));
        System.out.println(result);


*/

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        Key sec = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        System.out.println("key from start" + sec);



        try {



            FileInputStream fis = new FileInputStream("C:/Users/alex/Desktop/dns650.cs");
            FileOutputStream fos = new FileOutputStream("encrypted.cs");
            encrypt(cipher,sec, fis, fos);


            FileInputStream fis2 = new FileInputStream("encrypted.cs");
            FileOutputStream fos2 = new FileOutputStream("decrypted.cs");
            decrypt(cipher,sec, fis2, fos2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void encrypt(Cipher cipher, Key key, InputStream is, OutputStream os)
            throws Throwable {
        onlyEnc(cipher,key, is, os);
    }

    public static void decrypt(Cipher cipher, Key key, InputStream is, OutputStream os)
            throws Throwable {
        onlyDec(cipher, key, is, os);
    }
   /* public static Key kj() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);
        Key secretKey = keyGenerator.generateKey();
        System.out.println(secretKey);
        return secretKey;
    }*/
    public static void onlyEnc(Cipher cipher, Key key, InputStream is,
                               OutputStream os) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
       // Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");
        System.out.println("key from enc" + key);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        doCopy(cis, os);

    }

    public static void onlyDec(Cipher cipher, Key key, InputStream is,
                               OutputStream os) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        System.out.println("key from dec" + key);
       // Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        CipherOutputStream cos = new CipherOutputStream(os, cipher);
        doCopy(is, cos);

    }
/*
    public static void encryptOrDecrypt(Key key, int mode, InputStream is,
                                        OutputStream os) throws Throwable {
        Key sc = key;
        System.out.println(sc);


        Cipher cipher = Cipher.getInstance("Blowfish/CFB/NoPadding");

        if (mode == Cipher.ENCRYPT_MODE) {

            cipher.init(Cipher.ENCRYPT_MODE, sc);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {

            cipher.init(Cipher.DECRYPT_MODE, sc);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }*/

    public static void doCopy(InputStream is, OutputStream os)
            throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }
    }

