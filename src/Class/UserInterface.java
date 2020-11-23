package Class;
import java.util.*;


/**
 Performs the initial initializing of everything.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/
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
        userControl.loadScheduleList();
        
        //displays the initial menu
        while(choice != 2){
            System.out.println("\n1. Login (Username chew0393 for sending student email)");
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

    /** 
     * This method sets up controller respective to the type of user that's logged in.
     */
    private static void setController() {
        if(currentUser instanceof Admin){
            adminControl = new AdminController();
        }
        else if(currentUser instanceof Student){
            studentControl = new StudentController();
        }
    }

    /** 
     * This method prints menu based on the type of user.
     */
    private static void printMenu() {   
        if(currentUser instanceof Admin){
            adminControl.selectChoice();
        }
        else if(currentUser instanceof Student){
            studentControl.selectChoice();
        }
    }
}