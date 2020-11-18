import java.util.*;

public class AdminController{

    UserController userController = new UserController();
    Scanner s1 = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("Menu");
        System.out.println("=====");
        System.out.println("1. Edit student access period");
        System.out.println("DONE 2. Add a student (name, matric number, gender, nationality, etc)");
        System.out.println("DONE 3. Add/Update a course (course code, school, its index numbers and vacancy).");
        System.out.println("DONE 4. Check available slot for an index number (vacancy in a class)");
        System.out.println("DONE 5. Print student list by index number.");
        System.out.println("DONE 6. Print student list by course (all students registered for the selected course).");
        System.out.println("DONE 9. Logout");
        System.out.println("DONE 0. Exit.");
        System.out.print("Enter choice: ");
    }

    public void selectChoice() {
        Admin a = (Admin) userController.getCurrentUser();
        int choice = 9;
        int index = 0;
        while (choice != 0) {
            displayMenu();
            choice = s1.nextInt();
            s1.nextLine();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println();
                    System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
                    addUpdateCourse(userController.getCourseList());
                    System.out.println();
                    break;
                default:
                    break;
            }
        }
    }

    public void addUpdateCourse(ArrayList courseList) {
        Scanner sc = new Scanner(System.in);
        Course course = null;

        System.out.print("Add or Update Course: ");
        String choice = sc.nextLine();
        String add = "ADD";
        String upd = "UPDATE";

        if (choice.toUpperCase().equals(add)) {
            Course cor;

            System.out.print("Course: ");
            String a = sc.nextLine();
            System.out.print("School: ");
            String b = sc.nextLine();
            System.out.print("Index: ");
            int c = sc.nextInt();
            System.out.print("Vacancies: ");
            int d = sc.nextInt();

            cor = new Index(a, b, c, d);

            courseList.add(cor);
            userController.editCourseList();

        } else if (choice.toUpperCase().equals(upd)) {
            System.out.println("Update!\n");

            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
            for (int i = 0; i < courseList.size(); i++) {
                course = (Course) courseList.get(i);
                System.out.printf("%-15s %-10s %-10s %-10s\n", course.getCourseCode(), course.getSchool(),
                        ((Index) course).getIndexNumber(), ((Index) course).getVacancies());
            }

            System.out.println("Which index to update? ");
            int a = sc.nextInt();

            // will only allow admin to update vacancies
            System.out.println("Update vacancies: ");
            int b = sc.nextInt();

            for (int i = 0; i < courseList.size(); i++) {
                Course cos = (Course) courseList.get(i);
                if (a == ((Index) cos).getIndexNumber()) {
                    ((Index) cos).setVacancies(b);
                    System.out.println("Updated!");
                    System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                    System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                            ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                    break;
                }
            }

            userController.editCourseList();

        }else{
            System.out.print("nope!");
        }
    }
}