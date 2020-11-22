import java.util.*;

public class UserInterface{
    
    private static User currentUser;
    private static UserController userControl;
    private static AdminController adminControl;
    private static StudentController studentControl;
    public static void main(String[] args){
        Scanner s1 = new Scanner(System.in);
        int choice = 0;

        userControl = new UserController();
        userControl.loadUserList();
        userControl.loadCourseList();
        userControl.loadRegisterStudentList();
        userControl.loadSchoolList();
        

        while(choice != 2){
            System.out.println("1. Login (Username chew0393 for sending student email)");
            System.out.println("2. Exit");
            System.out.print("Choice: ");
            try{
                choice = s1.nextInt();
            } catch(Exception e){
                System.out.println("Invalid input. Please try again.");
            }
            s1.nextLine();

            switch(choice){
                case 1:
                    currentUser = userControl.login();
                    if(currentUser != null){
                        System.out.printf("Welcome %s!\n\n", currentUser.getName());
                        setController();
                        printMenu();
                    }
                    break;
                case 2:
                    userControl.editCourseList();
                    userControl.editRegisterStudentList();
                    userControl.editUserList();
                    break;
                default:
                    break;
            }
        }
    }

    private static void setController() {
        if(currentUser instanceof Admin){
            adminControl = new AdminController();
        }
        else if(currentUser instanceof Student){
            studentControl = new StudentController();
        }
    }

    private static void printMenu() {
        if(currentUser instanceof Admin){
            adminControl.selectChoice();
        }
        else if(currentUser instanceof Student){
            studentControl.selectChoice();
        }
    }
}