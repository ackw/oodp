import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class arraytest {

   public static void main(String[] args) throws IOException {
        ArrayList<Object> userList = new ArrayList<Object>();
        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter Name:");
        userList.add(s1.next());
        System.out.println("Enter Username:");
        userList.add(s1.next());
        System.out.println("Enter Password:");
        userList.add(s1.next());
        System.out.println("Admin?");
        userList.add(s1.nextBoolean());
        System.out.println("Enter Matric No:");
        userList.add(s1.nextInt());
        System.out.println("Enter Gender:");
        userList.add(s1.next());
        System.out.println("Enter Nationality:");
        userList.add(s1.next());

        //Writing arraylist to the file
        try {
            FileOutputStream fos = new FileOutputStream("./src/data/userinfo");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(userList);

            oos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }

        //Reading arraylist from the file
        try {
            FileInputStream fis = new FileInputStream("./src/data/userinfo");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Object> returnlist = (ArrayList<Object>) ois.readObject();

            System.out.println(returnlist);

            ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}