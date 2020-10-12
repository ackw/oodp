import java.util.Scanner;

public class Admin {
    public static void adminMain(){
        int choice = 0;

        while(choice<1 || choice>6) {
            System.out.print("Choose one of the options (1-6):\n");
            System.out.print("(1) Edit student access period\n" +
                            "(2) Add a student\n" +
                            "(3) Add/Update a course\n"+
                            "(4) Check available slot for an index number\n"+
                            "(5) Print student list by index number.\n"+
                            "(6) Print student list by course\n");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Edit student access period page.");
                    break;
                case 2:
                    System.out.println("Add a student page.");
                    break;
                case 3:
                    System.out.println("Add/Update a course page.");
                    break;
                case 4:
                    System.out.println("Check available slot for an index number.");
                    break;
                case 5:
                    System.out.println("Print student list by index number.");
                    break;
                case 6:
                    System.out.println("Print student list by course.");
                    break;
                default:
                    System.out.println("Enter again!\n");
            }
        }
    }
}
