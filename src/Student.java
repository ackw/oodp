import java.io.*;
import java.util.*;

// student main page

public class Student {
    public static void studentMain(){
        int choice = 0;

        while(choice<1 || choice>6) {
        System.out.print("Choose one of the options (1-6):\n");
        System.out.print("(1) Add Course\n" +
                        "(2) Drop Course\n" +
                        "(3) Check/Print Courses Registered\n"+
                        "(4) Check Vacancies Available\n"+
                        "(5) Change Index Number of Course\n"+
                        "(6) Swop Index Number with Another Student\n");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You're in the Add Course Page.");
                    break;
                case 2:
                    System.out.println("You're in the Drop Course Page.");
                    break;
                case 3:
                    System.out.println("Check/Print Courses Registered.");
                    break;
                case 4:
                    System.out.println("Check Vacancies Available.");
                    Course.courseList();
                    break;
                case 5:
                    System.out.println("Change Index Number of Course.");
                    break;
                case 6:
                    System.out.println("Swop Index Number with Another Student.");
                    break;
                default:
                    System.out.println("Enter again!\n");
            }
        }
    }
}