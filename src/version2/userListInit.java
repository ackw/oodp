import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class userListInit {

   public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> userList = new ArrayList<Object>();
        User u;
        u = new Admin("Ho Lee Fok", "user1", "688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6", true);
        userList.add(u);
        u = new Admin("Wu Liu Qi", "user2", "688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6", true);
        userList.add(u);
        // u = new Student("Sum Ting Wong", "user3", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, 19234234, 'F', "SG", "andrelchew@icloud.com");
        u = new Student("Sum Ting Wong", "user3", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, 19234234, 'F', "SG");
        userList.add(u);
        u = new Student("Low Mai Kai", "user4", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, 18492841, 'M', "MY");
        userList.add(u);
        u = new Student("Won Jae", "chew0393", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, 19678909, 'M', "KR");
        userList.add(u);

       School sch = new School("SCSE");
       String start = "2020-11-01 11:30";
       String end = "2020-11-01 14:30";
       String s1 = "School of Computer Science and Engineering";

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       LocalDateTime scseStart = LocalDateTime.parse(start, formatter);
       LocalDateTime scseEnd = LocalDateTime.parse(end, formatter);
       sch.setStartAccess(scseStart);
       sch.setEndAccess(scseEnd);
       sch.setName(s1);

       School sch2 = new School("SOH");
       String start2 = "2020-11-03 09:30";
       String end2 = "2020-11-03 12:30";
       String s2 = "School of Humanities";

       LocalDateTime sohStart = LocalDateTime.parse(start2, formatter);
       LocalDateTime sohEnd = LocalDateTime.parse(end2, formatter);
       sch2.setStartAccess(sohStart);
       sch2.setEndAccess(sohEnd);

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