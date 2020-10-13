package version2;
import java.util.*;
import java.io.*;

public class StarsPlanner
{
    public static void main(String[] args)
    {
        Scanner s1 = new Scanner(System.in);
        ArrayList userList = new ArrayList();
        ArrayList courseList = new ArrayList();
        ArrayList registerStudentList = new ArrayList(); //will use this later
        initUserList(userList);
        initCourseList(courseList);
        User currentUser = login(userList);
        System.out.printf("Hello %s!\n\n", currentUser.getName());
        Boolean userType = currentUser.getType();  //TRUE = admin, FALSE = student;

        int repeatMenu = 1;
        int menuChoice = 0;

        while(repeatMenu == 1 && userType)
        {
            displayMenu(userType);
            menuChoice = s1.nextInt();
            switch(menuChoice)
            {
                case 1: System.out.println();
                        System.out.println("1. Edit student access period");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 2: System.out.println();
                        System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
                        addStudent(userList);
                        System.out.println();
                        System.out.println(userList);
                        break;
                case 3: System.out.println();
                        System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 4: System.out.println();
                        System.out.println("4. Check available slot for an index number (vacancy in a class)");
                        checkAvailSlotIndex(courseList);
                        System.out.println();
                        break;
                case 5: System.out.println();
                        System.out.println("5. Print student list by index number.");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 6: System.out.println();
                        System.out.println("6. Print student list by course (all students registered for the selected course).");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;      
                case 0: repeatMenu = 0;
                        s1.close();
                        break;
            }
        }

            
        while(repeatMenu == 1 && !userType)
        {
            displayMenu(userType);
            menuChoice = s1.nextInt();
            switch(menuChoice)
            {
                case 1: System.out.println();
                        System.out.println("1. *Add Course");
                        //addCourse();
                        System.out.println();
                        break;
                case 2: System.out.println();
                        System.out.println("2. Drop Course");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 3: System.out.println();
                        System.out.println("3. Check/Print Courses Registered");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 4: System.out.println();
                        System.out.println("4. Check Vacancies Available");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 5: System.out.println();
                        System.out.println("5. Change Index Number of Course");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;
                case 6: System.out.println();
                        System.out.println("6. Swop Index Number with Another Student");
                        //editStudentAccessPeriod();
                        System.out.println();
                        break;      
                case 0: repeatMenu = 0;
                        s1.close();
                        break;
            }
            
        }   
    }

    public static void initUserList(ArrayList userList)
    {
        User u;
        u = new Admin("Gan Swee Huang", "user1", "asd", true);
        userList.add(u);
        u = new Admin("Heng Siow Keat", "user2", "zxc", true);
        userList.add(u);
        u = new Student("Barbara", "user3", "qwe", false, 19234234, 'F', "SG");
        userList.add(u);
    }

    public static void initCourseList(ArrayList courseList)
    {
        Course c;
        c = new Index("CZ2002", "SCSE", 20021, 20);
        courseList.add(c);
        c = new Index("CZ2002", "SCSE", 20022, 25);
        courseList.add(c);
        c = new Index("CZ2002", "SCSE", 20023, 30);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE", 20031, 20);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE", 20032, 30);
        courseList.add(c);
    }

    public static void displayMenu(boolean userType)
    {
        if(userType) //if TRUE = admin, display admin menu
        {
            System.out.println("Menu");
            System.out.println("=====");
            System.out.println("1. Edit student access period");
            System.out.println("DONE 2. Add a student (name, matric number, gender, nationality, etc)");
            System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
            System.out.println("DONE 4. Check available slot for an index number (vacancy in a class)");
            System.out.println("5. Print student list by index number.");
            System.out.println("6. Print student list by course (all students registered for the selected course).");
            System.out.println("0. Exit.");
            System.out.print("Enter choice: ");
        }
        else // if FALSE = student, display student menu
        {
            System.out.println("Menu");
            System.out.println("=====");
            System.out.println("1. *Add Course");
            System.out.println("2. Drop Course");
            System.out.println("3. Check/Print Courses Registered");
            System.out.println("4. Check Vacancies Available");
            System.out.println("5. Change Index Number of Course");
            System.out.println("6. Swop Index Number with Another Student");
            System.out.println("0. Exit.");
            System.out.print("Enter choice: ");
        }

    
    }
    public static User login(ArrayList userList) 
    {
        Scanner s1 = new Scanner(System.in);
        Console console = System.console();
        User u = null;
        Boolean success = false;
        // user login details
        while(!success)
        {
            System.out.print("Enter Username: ");
            String inputUsername = s1.nextLine();
            
            // check hashed password using bcrypt
            char[] password = console.readPassword("Enter Password: ");
            String inputPw = new String(password);

            for(int i = 0; i < userList.size(); i++)
            {
                u = (User) userList.get(i);
                if(u.getUsername().equals(inputUsername) && u.getPassword().equals(inputPw))
                {
                    success = true;
                    break;
                }
            }
            if(!success)
                System.out.println("Invalid login details");
        }
        return u;
        
    }

    public static void addStudent(ArrayList userList)
    {
        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter Name:");
        String name = s1.nextLine();
        System.out.println("Enter Username:");
        String username = s1.nextLine();
        System.out.println("Enter Password:");
        String password = s1.nextLine();
        System.out.println("Enter Matric No:");
        int matricNumber = s1.nextInt();
        System.out.println("Enter Gender:");
        char gender = s1.next().charAt(0);
        System.out.println("Enter Nationality:");
        String nationality = s1.nextLine();
        User u = new Student(name, username, password, false, matricNumber, gender, nationality);
        userList.add(u);
    }

    public static void checkAvailSlotIndex(ArrayList courseList)
    {
        Course c = null;
        Scanner s1 = new Scanner(System.in);
        System.out.print("Enter index number: ");
        int indexChoice = s1.nextInt();
        System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");
        for(int i = 0; i < courseList.size(); i++)
        {
            c = (Course) courseList.get(i);
            if(indexChoice == ((Index)c).getIndexNumber())
                System.out.printf("%-15s %-10s %-10s %-10s\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber(), ((Index)c).getVacancies());
        }
    }

    public static void addCourse(ArrayList courseList, ArrayList userList, ArrayList registerStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        //supposed to pass from login directly to here...
        //but since i havent do login i'll just prompt user
        //TOBECHANGED


    }
}