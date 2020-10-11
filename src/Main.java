import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// https://dev.to/awwsmm/how-to-encrypt-a-password-in-java-42dh

// https://www.mindrot.org/projects/jBCrypt/
// https://bcrypt-generator.com

public class Main {
    private static int success = 0;

    public static void main(String[] args) throws FileNotFoundException {
        timecheck();
        while(success!=1) {
            login();
        }
    }
    
    public static void timecheck(){
        // check for login before and after allowed registration period

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
    }


    // method 1: bcrypt - hashing works, but issues with salt version
    public static void decryptpw(String inputPw, String[] sArray){
        BCrypt BCrypt = new BCrypt();

        System.out.println(sArray[1]);
        boolean matched = BCrypt.checkpw(inputPw, sArray[1]);
        System.out.println("match: " + matched);
    }


    // method 2 - incomplete
    private static int ITERATIONS;
    private static int KEY_LENGTH;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RAND = new SecureRandom();

    public static Optional<String> generateSalt (final int length) {
        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public static Optional<String> hashPassword (String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, 512, 64);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    
    public static void login() {
        // user login details
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String inputUser = keyboard.nextLine();
        
        // check hashed password using bcrypt
        System.out.print("Enter Password: ");
        String inputPw = keyboard.nextLine();

        try {
            Scanner scan = new Scanner(new File("src/user.txt"));

            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                String[] sArray = s.split(",");

                // decryptpw(inputPw, sArray);

                if (inputUser.equals(sArray[0]) && inputPw.equals(sArray[1])) {
                    success = 1;
                    String role = "admin";

                    if (role.equals(sArray[2])){
                        System.out.print("admin login success!");
                    }else{
                        System.out.print("user login success!");
                        role = "user";
                    }
                }
            }

            if (success == 0) {
                System.out.println("login fail! try again!");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Error!" + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}