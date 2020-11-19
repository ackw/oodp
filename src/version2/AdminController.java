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
                case 4:
                    System.out.println();
                    System.out.println("4. Check available slot for an index number (vacancy in a class)");
                    checkAvailSlotIndex(userController.getCourseList());
                    System.out.println();
                    break;
                default:
                    break;
            }
        }
    }

    public void addUpdateCourse(ArrayList courseList) {
        int option = 9;
        Scanner sc = new Scanner(System.in);
        Course course = null;

        System.out.print("Add or Update Course: ");
        String choice = sc.nextLine();
        String add = "ADD";
        String upd = "UPDATE";

        if (choice.toUpperCase().equals(add)) {
            Course cor;

            try {
                System.out.print("Course: ");
                String a = sc.nextLine();
                System.out.print("School: ");
                String b = sc.nextLine();
                System.out.print("Index: ");
                int c = sc.nextInt();
                System.out.print("Vacancies: ");
                int d = sc.nextInt();

                for (int i = 0; i < courseList.size(); i++) {
                    Course cos = (Course) courseList.get(i);
                    if (c == ((Index) cos).getIndexNumber()) {
                        System.out.println("Index already exists!");
                        return;
                    }
                }
                cor = new Index(a, b, c, d);
                courseList.add(cor);

                System.out.println("Updated!");
                System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                System.out.printf("\n%-15s %-10s %-10s %-10s\n", a, b, c, d);
                
                userController.editCourseList();
              }

              catch(Exception e) {
                System.out.println("Invalid input!");
              }

        } else if (choice.toUpperCase().equals(upd)) {
            System.out.println("Update!\n");

            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
            for (int i = 0; i < courseList.size(); i++) {
                course = (Course) courseList.get(i);
                System.out.printf("%-15s %-10s %-10s %-10s\n", course.getCourseCode(), course.getSchool(),
                        ((Index) course).getIndexNumber(), ((Index) course).getVacancies());
            }
            
            System.out.println("Which index to update? ");
            int input = sc.nextInt();

            System.out.println("Update Options");
            System.out.println("=====");
            System.out.println("1. Update Course Code");
            System.out.println("2. Update School");
            System.out.println("3. Update Index");
            System.out.println("4. Update Vacancies");

            option = s1.nextInt();
            s1.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Update course code: ");
                    String a = sc.next();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Course) cos).setCourseCode(a);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                case 2:
                    System.out.println("Update school: ");
                    String b = sc.next();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Course) cos).setSchool(b);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                case 3:
                    System.out.println("Update index: ");
                    int c = sc.nextInt();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (c == ((Index) cos).getIndexNumber()) {
                            System.out.println("Index already exists!");
                            return;
                        }
                    }

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Index) cos).setIndexNumber(c);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                case 4:
                    System.out.println("Update vacancies: ");
                    int d = sc.nextInt();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Index) cos).setVacancies(d);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }else{
            System.out.print("nope!");
        }
    }

    public static void checkAvailSlotIndex(ArrayList courseList) 
    {
        Course c = null;

        Scanner s1 = new Scanner(System.in);
        int indexChoice = promptIndexNumber(courseList);
        if(indexChoice == 1){
            return;
        }

        System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");
        for(int i = 0; i < courseList.size(); i++)
        {
            c = (Course) courseList.get(i);
            if(indexChoice == ((Index)c).getIndexNumber())
                System.out.printf("%-15s %-10s %-10s %-10s\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber(), ((Index)c).getVacancies());
        }
    }

    public static int promptIndexNumber(ArrayList courseList)
    {
        String s;
        int check = 1;
        Scanner s1 = new Scanner(System.in);
        Course c;
        int indexChoice = 0;
            
        do{
            try{
                System.out.print("Enter the index number: ");
                indexChoice = s1.nextInt();
                for(int i = 0; i < courseList.size(); i++){
                    c = (Course) courseList.get(i);
                    if(indexChoice == ((Index)c).getIndexNumber()){
                        check = 2;
                        break;
                    }
                    check = 1;
                }
                if(check != 2){
                    System.out.println("hehe invalid index. try again");
                    return check;
                }
            }
            catch(Exception e){
                System.out.println("Invalid index number. Please retry.");
                s = s1.nextLine();
                return 1;
            }
        }while(check == 1);
        return indexChoice;
    }
}