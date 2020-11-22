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

public class schoolListInit {

    public static void main(String[] args) throws IOException {
        ArrayList<Object> returnlist = new ArrayList<Object>();
        ArrayList<Object> schoolList = new ArrayList<Object>();

        //Writing arraylist to the file
        School sch = new School();
        String start = "2020-11-22 11:30";
        String end = "2020-11-22 14:30";
        String s1 = "School of Computer Science and Engineering";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime scseStart = LocalDateTime.parse(start, formatter);
        LocalDateTime scseEnd = LocalDateTime.parse(end, formatter);
        sch.setSchoolID("SCSE");
        sch.setStartAccess(scseStart);
        sch.setEndAccess(scseEnd);
        sch.setName(s1);
        schoolList.add(sch);

        School sch2 = new School();
        String start2 = "2020-11-03 09:30";
        String end2 = "2020-11-03 12:30";
        String s2 = "School of Humanities";

        LocalDateTime sohStart = LocalDateTime.parse(start2, formatter);
        LocalDateTime sohEnd = LocalDateTime.parse(end2, formatter);
        sch2.setSchoolID("SOH");
        sch2.setStartAccess(sohStart);
        sch2.setEndAccess(sohEnd);
        sch2.setName(s2);
        schoolList.add(sch2);

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