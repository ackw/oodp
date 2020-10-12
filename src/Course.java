import java.io.*;
import java.util.*;

public class Course {
    public static void courseList(){
        try {
            Scanner scan = new Scanner(new File("src/course.txt"));
            Main.skipLine(scan, 1);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                String[] sArray = s.split(",");

                for (int i = 0; i < sArray.length; i++) {
                    System.out.println(sArray[i]);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
