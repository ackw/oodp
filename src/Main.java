import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// https://dev.to/awwsmm/how-to-encrypt-a-password-in-java-42dh
// https://www.mindrot.org/projects/jBCrypt/
// https://bcrypt-generator.com

public class Main {
    private static int success = 0; //login success

    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {

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

        System.out.println(sArray[2]);
        boolean matched = BCrypt.checkpw(inputPw, sArray[1]);
        System.out.println("match: " + matched);
    }



    // method 2: SHA-1
        public static void check(String[] sArray) throws NoSuchAlgorithmException
        {
            String inputPw = "password";
            byte[] salt = getSalt();

            String securePassword = shaOne(inputPw, salt);
            System.out.println("hashed/" + securePassword);

            System.out.println(sArray[1]);
            boolean matched = securePassword.equals(sArray[1]);
            System.out.println("match: " + matched);
        }

        public static String shaOne(String inputPw, byte[] salt)
        {
            String generatedPassword = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(salt);
                byte[] bytes = md.digest(inputPw.getBytes());
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            return generatedPassword;
        }

        //Add salt
        public static byte[] getSalt() throws NoSuchAlgorithmException
        {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        }

    // method 3 - incomplete
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
        Scanner k = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String inputUser = k.nextLine();
        
        // check hashed password using bcrypt
        System.out.print("Enter Password: ");
        String inputPw = k.nextLine();

        try {
            Scanner scan = new Scanner(new File("src/data/user.txt"));
            skipLine(scan, 1);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                String[] sArray = s.split(",");

                // dummy valid period
                int endValid = 5;
                int startValid = 3;
                int validPeriod = 4;

                // for password hash
                // decryptpw(inputPw, sArray);
                // check(sArray);

                if (inputUser.equals(sArray[1]) && inputPw.equals(sArray[2])) {
                    success = 1;
                    String role = "admin";

                    if (role.equals(sArray[3])){
                        System.out.println("\nadmin login success!");
                    }else{
                        System.out.println("\nuser login success!");
                        role = "user";
                            if (validPeriod >= startValid && validPeriod <= endValid){
                                System.out.println("you are allowed to register for courses now.\n");
                                Student.studentMain();
                            }else{
                                System.out.println("you are not allowed to register for courses now.\n");
                            }
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
    public static void skipLine(Scanner scan, int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(scan.hasNextLine()) {
                scan.nextLine();
            }
        }
    }
}