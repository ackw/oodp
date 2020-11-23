package Class;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 Initializes the initial dummy data for courses.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
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
        Schedule s;
        s = new Schedule(20011, "Tuesday", "BOTH", "Thursday", "Wednesday", LocalTime.of(14,30,00), LocalTime.of(12,00,00), LocalTime.of(15,00,00));
        c = new Index("CZ2001", "SCSE", 3 ,  20011, 20, s);
        courseList.add(c);
        s = new Schedule(20012, "Wednesday", "ODD", "Thursday", "Wednesday", LocalTime.of(10,30,00), LocalTime.of(8,00,00), LocalTime.of(10,30,00));
        c = new Index("CZ2001", "SCSE", 3 ,  20012, 20, s);
        courseList.add(c);
        s = new Schedule(20021, "Monday", "ODD", "Thursday", "Wednesday", LocalTime.of(8,30,00), LocalTime.of(12,30,00), LocalTime.of(8,30,00));
        c = new Index("CZ2002", "SCSE", 3 ,  20021, 20, s);
        courseList.add(c);
        s = new Schedule(20022, "Monday", "BOTH", "Tuesday", "Friday", LocalTime.of(9,30,00), LocalTime.of(15,30,00), LocalTime.of(8,00,00));
        c = new Index("CZ2002", "SCSE",3,  20022, 25, s);
        courseList.add(c);
        s = new Schedule(20031, "Wednesday", "EVEN", "Monday", "Wednesday", LocalTime.of(8,30,00), LocalTime.of(16,00,00), LocalTime.of(14,30,00));
        c = new Index("CZ2003", "SCSE",3,  20031, 20, s);
        courseList.add(c);
        s = new Schedule(20032, "Monday", "EVEN", "Thursday", "Thursday", LocalTime.of(9,30,00), LocalTime.of(12,30,00), LocalTime.of(8,00,00));
        c = new Index("CZ2003", "SCSE",3,  20032, 30, s);
        courseList.add(c);
        s = new Schedule(20241, "Monday", "ODD", "Friday", "Tuesday", LocalTime.of(8,30,00), LocalTime.of(8,00,00), LocalTime.of(10,30,00));
        c = new Index("HG2024", "SOH",3,  20241, 15, s);
        courseList.add(c);
        s = new Schedule(20242, "Monday", "EVEN", "Tuesday", "Friday", LocalTime.of(8,00,00), LocalTime.of(13,00,00), LocalTime.of(17,00,00));
        c = new Index("HG2024", "SOH",3,  20242, 20, s);
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