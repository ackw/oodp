package Class;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 Initializes the initial dummy data for Schedules.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class scheduleListInit {

   
   /** 
    * @param args
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> scheduleList = new ArrayList<Object>();

        //Writing arraylist to the file
        Schedule s;
        s = new Schedule(20021, "Monday", "ODD", "Tuesday", "Wednesday", LocalTime.of(8,30,00), LocalTime.of(12,30,00), LocalTime.of(8,30,00));
        scheduleList.add(s);
        s = new Schedule(20022, "Thursday", "BOTH", "Tuesday", "Friday", LocalTime.of(12,30,00), LocalTime.of(15,30,00), LocalTime.of(8,00,00));
        scheduleList.add(s);
        s = new Schedule(20031, "Wednesy", "EVEN", "Monday", "Wednesday", LocalTime.of(8,30,00), LocalTime.of(16,00,00), LocalTime.of(14,30,00));
        scheduleList.add(s);
        s = new Schedule(20032, "Tuesday", "ODD", "Friday", "Monday", LocalTime.of(10,30,00), LocalTime.of(8,00,00), LocalTime.of(10,30,00));
        scheduleList.add(s);
        s = new Schedule(20241, "Monday", "EVEN", "Thursday", "Thursday", LocalTime.of(14,30,00), LocalTime.of(12,30,00), LocalTime.of(8,00,00));
        scheduleList.add(s);
        s = new Schedule(20242, "Monday", "EVEN", "Tuesday", "Friday", LocalTime.of(8,00,00), LocalTime.of(13,00,00), LocalTime.of(17,00,00));
        scheduleList.add(s);
        
        try {
            FileOutputStream fos = new FileOutputStream("./src/data/scheduleList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(scheduleList);
            oos.flush();
            oos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }

        //Reading arraylist from the file
        try {
            FileInputStream fis = new FileInputStream("./src/data/scheduleList");
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