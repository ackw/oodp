import java.util.*;
import java.io.*;

public class StarsPlanner
{
    private static int regCount = 0; //check if course is added;
    public static int i = 5; // index for course retrieval

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

        while(repeatMenu == 1)
        {
            displayMenu(userType);
            menuChoice = s1.nextInt();
            switch(menuChoice)
            {
                case 1: if(userType)
                        {
                            System.out.println();
                            System.out.println("1. Edit student access period");
                            editStudentAccessPeriod();
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("1. *Add Course");
                            addCourse(courseList, currentUser, registerStudentList);
                            System.out.println();
                        }
                        break;
                case 2: if(userType)
                        {
                            System.out.println();
                            System.out.println("2. Add a student (name, matric number, gender, nationality, etc)");
                            addStudent(userList);
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("2. Drop Course");
                            dropCourse(courseList, currentUser, registerStudentList);
                            System.out.println();
                        }
                        break;
                case 3: if(userType)
                        {
                            System.out.println();
                            System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
                            addUpdateCourse(courseList);
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("3. Check/Print Courses Registered");
                            checkCoursesRegistered(currentUser, registerStudentList);
                            System.out.println();
                        }
                        break;
                case 4: if(userType)
                        {
                            System.out.println();
                            System.out.println("4. Check available slot for an index number (vacancy in a class)");
                            checkAvailSlotIndex(courseList);
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("4. Check Vacancies Available");
                            checkAvailSlotIndex(courseList);
                            System.out.println();
                        }
                        break;
                case 5: if(userType)
                        {
                            System.out.println();
                            System.out.println("5. Print student list by index number.");
                            printStudentByIndexNumber(registerStudentList);
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("5. Change Index Number of Course");
                            changeIndexNumber(courseList, currentUser, registerStudentList);
                            System.out.println();
                        }
                        break;
                case 6: if(userType)
                        {
                            System.out.println();
                            System.out.println("6. Print student list by course (all students registered for the selected course).");
                            printStudentListCourse(registerStudentList);
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("6. Swop Index Number with Another Student");
                            swopIndexStudent(courseList, currentUser, registerStudentList, userList);
                            System.out.println();
                        }
                        break;
                case 9: System.out.println();
                        System.out.println("6. Logout");
                        System.out.println("You have successfully logged out.");

                        System.out.print("Do you want to exit the program? (Y/N)");
                        Scanner s = new Scanner(System.in);
                        String choose = s.nextLine();

                        String no = "NO";
                        String n = "N";

                        if (choose.toUpperCase().equals(no) || choose.toUpperCase().equals(n)){
                            currentUser = login(userList);
                            System.out.printf("Hello %s!\n\n", currentUser.getName());
                            userType = currentUser.getType();
                        }else{
                            repeatMenu = 0;
                            System.out.println("Stars Planner closing...");
                            System.out.println("Goodbye.");
                            break;
                        }
                        System.out.println();
                        break;
                case 0: repeatMenu = 0;
                        System.out.println("Stars Planner closing...");
                        s1.close();
                        System.out.println("Goodbye.");
                        break;
            }
        }
    }

    public static void initUserList(ArrayList userList)
    {
        User u;
        u = new Admin("Ho Lee Fok", "user1", "asd", true);
        userList.add(u);
        u = new Admin("Wu Liu Qi", "user2", "asd", true);
        userList.add(u);
        u = new Student("Sum Ting Wong", "user3", "qwe", false, 19234234, 'F', "SG");
        userList.add(u);
        u = new Student("Low Mai Kai", "user4", "qwe", false, 18492841, 'M', "MY");
        userList.add(u);
    }

    public static void initCourseList(ArrayList courseList)
    {
        Course c;
        c = new Index("CZ2002", "SCSE", 20021, 20);
        courseList.add(c);
        c = new Index("CZ2002", "SCSE", 20022, 25);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE", 20031, 20);
        courseList.add(c);
        c = new Index("CZ2003", "SCSE", 20032, 30);
        courseList.add(c);
        c = new Index("HG2024", "SOH", 20241, 15);
        courseList.add(c);
        c = new Index("HG2024", "SOH", 20242, 20);
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
            System.out.println("DONE 3. Add/Update a course (course code, school, its index numbers and vacancy).");
            System.out.println("DONE 4. Check available slot for an index number (vacancy in a class)");
            System.out.println("DONE 5. Print student list by index number.");
            System.out.println("DONE 6. Print student list by course (all students registered for the selected course).");
            System.out.println("DONE 9. Logout");
            System.out.println("DONE 0. Exit.");
            System.out.print("Enter choice: ");
        }
        else // if FALSE = student, display student menu
        {
            System.out.println("Menu");
            System.out.println("=====");
            System.out.println("DONE 1. *Add Course");
            System.out.println("DONE 2. Drop Course");
            System.out.println("DONE 3. Check/Print Courses Registered");
            System.out.println("DONE 4. Check Vacancies Available");
            System.out.println("DONE 5. Change Index Number of Course");
            System.out.println("6. Swop Index Number with Another Student");
            System.out.println("DONE 9. Logout");
            System.out.println("DONE 0. Exit.");
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

    // to include validation if student has already registered
    public static void addCourse(ArrayList courseList, User currentUser, ArrayList registerStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        int indexChoice = 0;
        Course c = null;
        RegisterStudent r;
        System.out.print("Enter the index number: ");
        indexChoice = s1.nextInt();

        System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");
        for(int i = 0; i < courseList.size(); i++)
        {
            c = (Course) courseList.get(i);
            if(indexChoice == ((Index)c).getIndexNumber())
            {
                System.out.printf("%-15s %-10s %-10s %-10s\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber(), ((Index)c).getVacancies());
                break;
            }
        }
        System.out.print("Confirm (Y/N)? ");

        if(s1.next().toUpperCase().charAt(0)  == 'Y')
        {
            r = new RegisterStudent(currentUser, c);
            registerStudentList.add(r);
            int newVacancy = ((Index)c).getVacancies()-1;
            ((Index)c).setVacancies(newVacancy);
            System.out.println("Successfully added course!");
        }
        else
            System.out.println("Unsuccessful. Bye!");
    }

    // arraylists:
    // COURSE = admin will modify
    // RegisteredStudent = student with registered index

    public static void dropCourse(ArrayList courseList, User currentUser, ArrayList registerStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        int indexChoice = 0;
        RegisterStudent r;
        Index ind;
        checkCoursesRegistered(currentUser, registerStudentList);
        
        System.out.print("Enter the index number: ");
        indexChoice = s1.nextInt();

        System.out.print("Confirm (Y/N)? ");
        if(s1.next().toUpperCase().charAt(0)  == 'Y')
        {
            for(int i = 0; i < registerStudentList.size(); i++)
            {
                r = (RegisterStudent)registerStudentList.get(i);
                ind = (Index)r.getCourse();
                if(indexChoice == ind.getIndexNumber())
                {
                    if(currentUser.getUsername().equals(((User)r.getUser()).getUsername()))
                    {
                        registerStudentList.remove(i);
                        int newVacancy = ind.getVacancies()+1;
                        ind.setVacancies(newVacancy);
                        System.out.println("Successfully dropped course!");
                    }
                }
            }
        }
        else
            System.out.println("Unsuccessful. Bye!");
    }


    public static void checkCoursesRegistered(User currentUser, ArrayList registeredStudentList)
    {
        RegisterStudent r;
        Course c;
        User u;

        System.out.printf("\n%-15s %-10s %-10s\n","Course Code", "School", "Index");
        for(int i = 0; i < registeredStudentList.size(); i++)
        {
            r = (RegisterStudent)registeredStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(currentUser.getUsername()))
                System.out.printf("%-15s %-10s %-10s\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber());
        }
    }

    public static void printStudentByIndexNumber(ArrayList registerStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        int indexChoice;
        RegisterStudent r;
        Student s;
        System.out.print("Enter index number: ");
        indexChoice = s1.nextInt();
        System.out.printf("%-15s %-20s %-7s %-10s\n","Matric Number", "Name", "Gender", "Nationality");
        for(int i =0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            if(indexChoice == ((Index)r.getCourse()).getIndexNumber())
            {
                s = (Student)r.getUser();
                System.out.printf("%-15s %-20s %-7s %-10s\n", s.getMatricNumber(), s.getName(), s.getGender(), s.getNationality());
            }
        }
    }

    public static void printStudentListCourse(ArrayList registerStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        String courseChoice;
        RegisterStudent r;
        Student s;
        System.out.print("Enter course code: ");
        courseChoice = s1.nextLine();
        System.out.printf("%-15s %-20s %-7s %-10s\n","Matric Number", "Name", "Gender", "Nationality");
        for(int i =0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            if(courseChoice.equals(((Index)r.getCourse()).getCourseCode()))
            {
                s = (Student)r.getUser();
                System.out.printf("%-15s %-20s %-7s %-10s\n", s.getMatricNumber(), s.getName(), s.getGender(), s.getNationality());
            }
        }
    }


    public static void addUpdateCourse(ArrayList courseList)
    {
        Scanner sc = new Scanner(System.in);
        Course course = null;

        System.out.print("Add or Update Course: ");
        String choice = sc.nextLine();
        String add = "ADD";
        String upd = "UPDATE";

        if(choice.toUpperCase().equals(add)) {
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
            i++;
        }
        else if(choice.toUpperCase().equals(upd)){
            System.out.println("Update!\n");

            System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");
            for(int i = 0; i < courseList.size(); i++){
                course = (Course) courseList.get(i);
                System.out.printf("%-15s %-10s %-10s %-10s\n", course.getCourseCode(), course.getSchool(), ((Index)course).getIndexNumber(), ((Index)course).getVacancies());
            }

            System.out.println("Which index to update? ");
            int a = sc.nextInt();

            // will only allow admin to update vacancies
            System.out.println("Update vacancies: ");
            int b = sc.nextInt();

            for(int i = 0; i < courseList.size(); i++){
                Course cos = (Course) courseList.get(i);
                if(a == ((Index)cos).getIndexNumber())                
                {
                    ((Index)cos).setVacancies(b);
                    System.out.println("Updated!");
                    System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");
                    System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(), ((Index)cos).getIndexNumber(), ((Index)cos).getVacancies());
                    break;
                }   
            }
        }else{
            System.out.print("nope!");
        }
    }

    // not added input validation for wrong date format etc. + check if student is able to access planner
    public static void editStudentAccessPeriod(){
        int accessStartDate = 200820;
        int accessEndDate = 250820;
        int accessStartTime = 1100;
        int accessEndTime = 1700;

        System.out.println("Student Access Period is " + accessStartDate + " to " + accessEndDate + ", from " + accessStartTime + " to " + accessEndTime + ".");
        Scanner sc = new Scanner(System.in);
        System.out.print("Edit start date (DDMMYY): ");
        accessStartDate = sc.nextInt();
        System.out.print("Edit end date (DDMMYY): ");
        accessEndDate = sc.nextInt();
        System.out.print("Edit start time (0000): ");
        accessStartTime = sc.nextInt();
        System.out.print("Edit end time (0000): ");
        accessEndTime = sc.nextInt();

        System.out.println("\nEdited Student Access Period is " + accessStartDate + " to " + accessEndDate + ", from " + accessStartTime + " to " + accessEndTime + ".");
    }

    // to check if new index is in the registered courses list
    public static void changeIndexNumber(ArrayList courseList, User currentUser, ArrayList registeredStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        int indexChoice = 0;
        Course c = null;
        RegisterStudent r;
        RegisterStudent rs;
        Index ind;
        int newVacancy;

        checkCoursesRegistered(currentUser, registeredStudentList);
        System.out.print("Which index do you wish to change? ");
        indexChoice = s1.nextInt();

        System.out.print("Enter new index: ");
        int indexChoiceNew = s1.nextInt();

        System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");
        for(int j = 0; i < courseList.size(); j++)
        {
            c = (Course) courseList.get(j);
            if(indexChoiceNew == ((Index)c).getIndexNumber())
            {
                System.out.printf("%-15s %-10s %-10s %-10s\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber(), ((Index)c).getVacancies());
                break;
            }
        }
        System.out.print("Confirm (Y/N)? ");
        if(s1.next().toUpperCase().charAt(0)  == 'Y')
        {
            rs = new RegisterStudent(currentUser, c);
            registeredStudentList.add(rs);
            newVacancy = ((Index)c).getVacancies()-1;
            ((Index)c).setVacancies(newVacancy);

            for(int i = 0; i < registeredStudentList.size(); i++)
            {
                r = (RegisterStudent)registeredStudentList.get(i);
                ind = (Index)r.getCourse();
                if(indexChoice == ind.getIndexNumber())
                {
                    if(currentUser.getUsername().equals(((User)r.getUser()).getUsername()))
                    {
                        registeredStudentList.remove(i);
                        newVacancy = ind.getVacancies()+1;
                        ind.setVacancies(newVacancy);
                    }
                }
            }   
        }
        else
            System.out.println("Unsuccessful. Bye!");
    }

    public static void swopIndexStudent(ArrayList courseList, User currentUser, ArrayList registeredStudentList, ArrayList userList)
    {
        Scanner s1 = new Scanner(System.in);
        Course c, tempCourse;
        RegisterStudent r;
        User u;

        checkCoursesRegistered(currentUser, registeredStudentList);
        System.out.printf("%s's courses:!\n\n", currentUser.getName());
        System.out.print("Which of your index do you wish to change with? ");
        int indexChoice = s1.nextInt();

        System.out.println("Please provide your peer's login particulars:");
        User peer = login(userList);

        checkCoursesRegistered(peer, registeredStudentList);
        System.out.printf("%s's courses:!\n\n", peer.getName());
        System.out.print("Which of peer's index do you wish to change with? ");
        int peerChoice = s1.nextInt();

        //confirm with user

        for(int i = 0; i < registeredStudentList.size(); i++)
        {
            r = (RegisterStudent)registeredStudentList.get(i);
            u = r.getUser();
            c = r.getCourse();
            if(u.getUsername().equals(currentUser.getUsername()) && indexChoice == ((Index)c).getIndexNumber())
            {
                System.out.println("found to change to peer");
                System.out.println(r.toString());
                r.setUser(peer);
                System.out.println(r.toString());
            }
            if(u.getUsername().equals(peer.getUsername()) && peerChoice == ((Index)c).getIndexNumber())
            {
                System.out.println("found to change to current");
                System.out.println(r.toString());
                r.setUser(currentUser);
                System.out.println(r.toString());
            }
        }   

        



        

    }
}