import java.util.*;


public class StudentController{

    UserController userController = new UserController();
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
        int index = 0;
        while(choice != 0){
            AdminController ac = new AdminController();
            displayMenu();
            choice = s1.nextInt();
            s1.nextLine();
            switch(choice){
                case 1: 
                    System.out.println();
                    System.out.println("1. *Add Course");
                    userController.showCourseInfo();
                    try{
                        index = promptIndex();
                        System.out.println(addCourse(index, s));
                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
                    System.out.println();
                    break;
                case 2: 
                    System.out.println();
                    System.out.println("2. Drop Course");
                    printCoursesRegistered(s);
                    try{
                        index = promptIndex();
                        System.out.println(dropCourse(index, s));
                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
                    //sendWaitlistNotif(currentUser, registerStudentList);
                    System.out.println();
                    break;
                case 3: 
                    System.out.println();
                    System.out.println("3. Check/Print Courses Registered");
                    printCoursesRegistered(s);
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("4. Check Vacancies Available");
                    try{
                        index = promptIndex();
                        Index ind = null;
                        if(userController.findIndex(index) != null){
                            ind = userController.findIndex(index);
                            System.out.printf("Vacancies for %d: %d\n", ind.getIndexNumber(), ind.getVacancies());
                        }
                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
                    
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
            
            if(c.getCourseCode().equals(ind.getCourseCode()))
                return "You are not allowed to register multiple index of the same course.";
        }

        if(ind.getVacancies() < 1){
            return "This course is full at the moment. You'll be added to waiting list. //tobeimplemented";
        }
        
        r = new RegisterStudent(s, ind);
        registerStudentList.add(r);
        int newVacancy = ((Index)ind).getVacancies()-1;
        ((Index)ind).setVacancies(newVacancy);
        userController.editRegisterStudentList();
        userController.editCourseList();
        //send cfm email?? to be implemented
        return "You have successfully registered for the course. // tobeimplemented send cfm email";
    }
    public String dropCourse(int index, Student s){
        ArrayList registerStudentList = userController.getRegisterStudentList();
        RegisterStudent r;
        Course c;
        User u;
        boolean checkIndex = false;
        Index ind = userController.findIndex(index);
        if(ind == null){
            return "can't find index la11";
        }

        for(int i = 0; i < registerStudentList.size(); i++){
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                checkIndex = true;
        }

        if(!checkIndex)
            return "User does not have this index. Please choose a different index.";

        for(int i = 0; i < registerStudentList.size(); i++){
            r = (RegisterStudent)registerStudentList.get(i);
            ind = (Index)r.getCourse();
            if(index == ind.getIndexNumber())
            {
                if(s.getUsername().equals(((User)r.getUser()).getUsername()))
                {
                    registerStudentList.remove(i);
                    System.out.println("Current: " + ind.getVacancies());
                    int newVacancy = ((Index)ind).getVacancies()+1;
                    ((Index)ind).setVacancies(newVacancy);
                    System.out.println("Current: " + ind.getVacancies());
                    System.out.println("Successfully dropped course!");
                    //send email
                    userController.editRegisterStudentList();
                    userController.editCourseList();
                    //send cfm email?? to be implemented
                    return "You have successfully dropped the course. // tobeimplemented send cfm email";
                }
            }
        }
        return "";
    }

    public void printCoursesRegistered(Student s){
        RegisterStudent r;
        Course c;
        User u;
        ArrayList<RegisterStudent> registerStudentList = userController.getRegisterStudentList();
        if(checkRegisterStudentList(s) == 1) //checks if student has any courses registered before doing anything
            return;
        System.out.printf("\n%-15s %-10s %-10s\n","Course Code", "School", "Index");
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(s.getUsername()))
                System.out.printf("%-15s %-10s %-10s\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber());
        }
    }

    public int checkRegisterStudentList(Student s){
        RegisterStudent r;
        Course c;
        User u;
        boolean checkUser = false;
        ArrayList<RegisterStudent> registerStudentList = userController.getRegisterStudentList();
        if(registerStudentList.size() == 0){
            System.out.println("User does not have any courses registered. Please choose a different option.");
            return 1;
        }
        else{
            for(int i = 0; i < registerStudentList.size(); i++)
            {
                r = (RegisterStudent)registerStudentList.get(i);
                c = r.getCourse();
                u = r.getUser();
                if(u.getUsername().equals(s.getUsername()))
                    checkUser = true;
            }
            if(!checkUser){
                System.out.println("User does not have any courses registered. Please choose a different option.");
                return 1;
            }
        }
        return 0;
    }

    public int promptIndex(){
        Scanner s1 = new Scanner(System.in);
        System.out.print("Enter index number: ");
        int index = s1.nextInt();
        return index;
    }
}