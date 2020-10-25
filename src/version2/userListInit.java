import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class userListInit {

   public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> userList = new ArrayList<Object>();
        User u;
        u = new Student("Sum Ting Wong", "user3", "qwe", false, 19234234, 'F', "SG");
        userList.add(u);
        u = new Admin("Admin", "admin", "admin", true);
        userList.add(u);
        u = new Student("Low Mai Kai", "user4", "qwe", false, 18492841, 'M', "MY");
        userList.add(u);

        //Writing arraylist to the file
        try {
            FileOutputStream fos = new FileOutputStream("./src/data/userList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(userList);
            oos.flush();
            oos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }

        //Reading arraylist from the file
        try {
            FileInputStream fis = new FileInputStream("./src/data/userList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            returnlist = (ArrayList<Object>) ois.readObject();
            ois.close();

            System.out.println(returnlist);
            
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}


