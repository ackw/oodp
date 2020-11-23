package Class;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 Initializes the initial dummy data for School.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
 @version 1.0
 @since 2020-11-23
*/
public class schoolListInit {

    
    /** 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> schoolList = new ArrayList<Object>();

        //Writing arraylist to the file
        School sch = new School();
        String start = "2020-11-22 11:30";
        String end = "2020-11-25 14:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime timeStart = LocalDateTime.parse(start, formatter);
        LocalDateTime timeEnd = LocalDateTime.parse(end, formatter);

        sch = new School("SCSE", "School of Computer Science and Engineering", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("MAE", "School of Mechanical and Aerospace Engineering", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("MSE", "School of Materials Science and Engineering", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("SCBE", "School of Chemical and Biomedical Engineering ", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("EEE", "School of Electrical and Electronic Engineering", timeStart, timeEnd);
        schoolList.add(sch);
        start = "2020-11-25 14:30";
        end = "2020-11-29 18:00";
        timeStart = LocalDateTime.parse(start, formatter);
        timeEnd = LocalDateTime.parse(end, formatter);
        sch = new School("CEE", "School of Civil and Environmental Engineering", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("ADM", "School of Art, Design and Media", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("SOH", "School of Humanities", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("SSS", "School of Social Sciences", timeStart, timeEnd);
        schoolList.add(sch);
        start = "2020-11-29 18:00";
        end = "2020-12-05 14:30";
        timeStart = LocalDateTime.parse(start, formatter);
        timeEnd = LocalDateTime.parse(end, formatter);
        sch = new School("WKW", "Wee Kim Wee School of Communication and Information", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("SBS", "School of Biological Sciences", timeStart, timeEnd);
        schoolList.add(sch);
        sch = new School("SPMS", "School of Physical and Mathematical Sciences", timeStart, timeEnd);
        schoolList.add(sch);



        try {
            FileOutputStream fos = new FileOutputStream("./src/data/schoolList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(schoolList);
            oos.flush();
            oos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
        //Reading arraylist from the file
        try {
            FileInputStream fis = new FileInputStream("./src/data/schoolList");
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