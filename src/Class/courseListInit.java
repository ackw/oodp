package Class;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 Initializes the initial dummy data for courses.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class courseListInit {

   
   /** 
    * @param args
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> courseList = new ArrayList<Object>();

        //Writing arraylist to the file
        Course c;
        c = new Index("CZ2002", "SCSE",3 ,  20021, 10);
        courseList.add(c);
        c = new Index("CZ2002", "SCSE",3,  20022, 10);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE",3,  20031, 10);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE",3,  20032, 10);
        courseList.add(c);
        c = new Index("CZ2001", "SCSE",3,  20011, 10);
        courseList.add(c);
        c = new Index("CZ2001", "SCSE",3,  20012, 10);
        courseList.add(c);
        c = new Index("HG2024", "SOH",3,  20241, 10);
        courseList.add(c);
        c = new Index("HG2024", "SOH",3,  20242, 10);
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
            returnlist = (ArrayList) ois.readObject();
            ois.close();

            System.out.println(returnlist);
            
            } 
        catch (Exception e) {
                e.printStackTrace();
            }
    }
}