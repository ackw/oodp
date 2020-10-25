import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class courseListInit {

   public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> courseList = new ArrayList<Object>();

        //Writing arraylist to the file
        Course c;
        c = new Index("CZ2002", "SCSE", 20021, 20);
        courseList.add(c);
        c = new Index("CZ2002", "SCSE", 20022, 25);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE", 20031, 20);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE", 20032, 30);
        courseList.add(c);
        c = new Index("HG2024", "SOH", 20241, 15);
        courseList.add(c);
        c = new Index("HG2024", "SOH", 20242, 20);
        courseList.add(c);

        try {
            FileOutputStream fos = new FileOutputStream("./src/data/courseList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(courseList);
            oos.flush();
            oos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }

        //Reading arraylist from the file
        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            returnlist = (ArrayList<Object>) ois.readObject();
            ois.close();

            System.out.println(returnlist);
            
            } 
        catch (Exception e) {
                e.printStackTrace();
            }
    }
}