package Class;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class userListInit {

   
   /** 
    * @param args
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> userList = new ArrayList<Object>();
        User u;
        u = new Admin("Givmi Eh", "user1", "688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6", true);
        userList.add(u);
        u = new Student("Wu Liu Qi", "user2", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1923871A", 'F', "SG", "SCSE");
        userList.add(u);
        // u = new Student("Sum Ting Wong", "user3", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, 19234234, 'F', "SG", "andrelchew@icloud.com");
        u = new Student("Sum Ting Wong", "user3", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1928765F", 'F', "SG", "SCSE");
        userList.add(u);
        u = new Student("Low Mai Kai", "user4", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U2032423H", 'M', "MY", "SCBE");
        userList.add(u);
        u = new Student("Won Jae", "chew0393", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1824273H", 'F', "KR", "SOH");
        userList.add(u);
        u = new Student("Lim Teh Peng", "user5", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1748672A", 'M', "SG", "SCSE");
        userList.add(u);
        u = new Student("Lim Teh Oh", "user6", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1768378B", 'M', "MY", "SCSE");
        userList.add(u);
        u = new Student("Lim Teh See", "user7", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1672839M", 'F', "MY", "SOH");
        userList.add(u);
        u = new Student("Ho Lee Fok", "user8", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1830487J", 'M', "SG", "SBS");
        userList.add(u);
        u = new Student("Mike Hawk", "user9", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1792874B", 'M', "KR", "MAE");
        userList.add(u);
        u = new Student("Dixie Normous", "user10", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1850256D", 'F', "SG", "SCSE");
        userList.add(u);
        u = new Student("Anita Ay", "user11", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1928576B", 'M', "SG", "SCSE");
        userList.add(u);
        u = new Student("Anita Bath", "user12", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U2049687W", 'F', "KR", "SCSE");
        userList.add(u);
        u = new Student("Anita Shawa ", "user13", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1850789B", 'M', "SG", "SOH");
        userList.add(u);
        u = new Student("Anita Kaek", "user14", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1749286H", 'M', "SG", "SOH");
        userList.add(u);
        u = new Student("Johnny Sins", "user15", "489cd5dbc708c7e541de4d7cd91ce6d0f1613573b7fc5b40d3942ccb9555cf35", false, "U1820497K", 'F', "KR", "SCSE");
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