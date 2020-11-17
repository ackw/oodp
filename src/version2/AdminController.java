public class AdminController{

    UserController userController = new UserController();

    public void displayMenu(){
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

    public void selectChoice(){
        Admin a = (Admin) userController.getCurrentUser();
    }

    

}