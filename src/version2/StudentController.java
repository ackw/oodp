import java.util.*;

public class StudentController{

    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    Scanner s1 = new Scanner(System.in);

    public void displayMenu(){
        System.out.println("Menu");
        System.out.println("=====");
        System.out.println("DONE 1. *Add Course");
        System.out.println("DONE 2. Drop Course");
        System.out.println("DONE 3. Check/Print Courses Registered");
        System.out.println("DONE 4. Check Vacancies Available");
        System.out.println("DONE 5. Change Index Number of Course");
        System.out.println("DONE 6. Swop Index Number with Another Student");
        System.out.println("DONE 0. Logout");
        System.out.print("Enter choice: ");
    }

    public void selectChoice(){
        Student s = (Student)userController.getCurrentUser();
        System.out.println(s.getUsername() + "hihi method");
        int choice = 9;
        while(choice != 0){
            AdminController ac = new AdminController();
            displayMenu();
            choice = s1.nextInt();
            s1.nextLine();
            switch(choice){
                case 1: 
                    System.out.println();
                    System.out.println("1. *Add Course");
                    int index = 0;
                    try{
                        index = promptIndex();
                        //System.out.println(addCourse(index, s));
                    } catch(Exception e){
                        System.out.println("Error try again. ");
                    }
                    System.out.println();
                    break;
                case 2: 
                    System.out.println();
                    System.out.println("2. Drop Course");
                    //dropCourse(courseList, currentUser, registerStudentList);
                    //sendWaitlistNotif(currentUser, registerStudentList);
                    System.out.println();
                    break;
                case 3: 
                    System.out.println();
                    System.out.println("3. Check/Print Courses Registered");
                    //checkCoursesRegistered(currentUser, registerStudentList);
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("4. Check Vacancies Available");
                    //checkAvailSlotIndex(courseList);
                    System.out.println();
                    break;
                case 5: 
                    System.out.println();
                    System.out.println("5. Change Index Number of Course");
                    //changeIndexNumber(courseList, currentUser, registerStudentList);
                    System.out.println();
                    break;
                case 6: 
                    System.out.println();
                    System.out.println("6. Swop Index Number with Another Student");
                    //swopIndexStudent(courseList, currentUser, registerStudentList, userList);
                    System.out.println();
                    break;
                case 0: 
                    System.out.println("");
                    break;
                default: break;
            }


        }
    }

    public String addCourse(int index, Student s){
        String returnMsg = "";
        ArrayList registerStudentList = userController.getRegisterStudentList();
        RegisterStudent r;
        Course c;
        Index ind = userController.findIndex(index);
        if(ind == null){
            return "can't find index la";
        }
        
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            if(s.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                return "User already has this module. Please choose a different index.";
        }
        return "You have successfully registered for the course.";
    }

    public int promptIndex(){
        Scanner s1 = new Scanner(System.in);
        userController.showCourseInfo();
        System.out.println("Enter index number: ");
        int index = s1.nextInt();
        return index;
    }
}