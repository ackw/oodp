import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// imports for email
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class StarsPlanner
{
    private static int regCount = 0; //check if course is added;
    public static int i = 5; // index for course retrieval
    public static ArrayList<Object> userList = new ArrayList<Object>();
    public static ArrayList<Object> courseList = new ArrayList<Object>();
    public static ArrayList<Object> registerStudentList = new ArrayList<Object>();

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
        //test
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
                            editStudentAccessPeriod(userList);
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
                            //sendWaitlistNotif(currentUser, registerStudentList);
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
                        System.out.print("Do you want to exit the program? (Y/N) ");
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
        // if(new File("./src/data/userList").isFile()){ //check if file exists
        //     try {
        //         FileInputStream fis = new FileInputStream("./src/data/userList");
        //         ObjectInputStream ois = new ObjectInputStream(fis);
        //         userList = (ArrayList<Object>) ois.readObject();
        //         ois.close();
        
        //         } 
        //     catch (Exception e) {
        //             e.printStackTrace();
        //         }
        // }
    }

    public static void initCourseList(ArrayList courseList)
    {
        // if(new File("./src/data/registerStudentList").isFile()){ //check if file exists
        //     try {
        //         FileInputStream fis = new FileInputStream("./src/data/courseList");
        //         ObjectInputStream ois = new ObjectInputStream(fis);
        //         courseList = (ArrayList<Object>) ois.readObject();
        //         ois.close();
        //         } 

        //     catch (Exception e) {
        //             e.printStackTrace();
        //         }
        // }
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

        try {
            FileInputStream fis = new FileInputStream("./src/data/userList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            }
        catch (Exception e) {
                e.printStackTrace();
            }

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

        try {
            FileInputStream fis = new FileInputStream("./src/data/userList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            }
        catch (Exception e) {
                e.printStackTrace();
            }

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
        s1.nextLine();
        System.out.println("Enter Nationality:");
        String nationality = s1.nextLine();
        User u = new Student(name, username, password, false, matricNumber, gender, nationality);
        userList.add(u);

        try {
            FileOutputStream fos = new FileOutputStream("./src/data/userList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            oos.flush();
            oos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void checkAvailSlotIndex(ArrayList courseList) 
    {
        Course c = null;
        Scanner s1 = new Scanner(System.in);
        int indexChoice = promptIndexNumber(courseList);

        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            }
        catch (Exception e) {
                e.printStackTrace();
            }

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
        User u;
        indexChoice = promptIndexNumber(courseList);
        String response;

        if(new File("./src/data/registerStudentList").isFile()){ //check if file exists
            try {
                FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
                ObjectInputStream ois = new ObjectInputStream(fis);
                registerStudentList = (ArrayList<Object>) ois.readObject();
                ois.close();
                
                }
            catch (Exception e) {
                    e.printStackTrace();
                }
        }

        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            } 
        catch (Exception e) {
                e.printStackTrace();
            }

        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(currentUser.getUsername()) && indexChoice == ((Index)c).getIndexNumber()){
                System.out.println("User already has this module. Please choose a different index.");
                return;
            }
        }
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
        
        do{
            System.out.print("Confirm (Y/N)? ");
            response = s1.next();
        }while(!((response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N"))));

        if(response.equalsIgnoreCase("Y"))
        {
            r = new RegisterStudent(currentUser, c);
            registerStudentList.add(r);
            int newVacancy = ((Index)c).getVacancies()-1;
            ((Index)c).setVacancies(newVacancy);
            try {
                FileOutputStream fos = new FileOutputStream("./src/data/courseList");
                ObjectOutputStream oos = new ObjectOutputStream(fos);   
                oos.writeObject(courseList);
                oos.flush();
                oos.close();
                }
            catch(Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Successfully added course!");
        }
        else
            System.out.println("Course not added. Bye!");

        try {
            FileOutputStream fos = new FileOutputStream("./src/data/registerStudentList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(registerStudentList);
            oos.close();
            }
        
        catch(Exception ex) {
            ex.printStackTrace();
            }
    }

    public static void dropCourse(ArrayList courseList, User currentUser, ArrayList registerStudentList) 
    {
        Scanner s1 = new Scanner(System.in);
        int indexChoice = 0;
        RegisterStudent r;
        Index ind;
        Course c;
        User u;
        String response;

        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            } 
        catch (Exception e) {
                e.printStackTrace();
            }
        
        try {
            FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList<Object>) ois.readObject();
            ois.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }

        if(checkRegisterStudentList(currentUser, registerStudentList) == 1)
            return;

        checkCoursesRegistered(currentUser, registerStudentList);
        indexChoice = promptIndexNumber(courseList);

        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(!(u.getUsername().equals(currentUser.getUsername()) && indexChoice == ((Index)c).getIndexNumber())){
                System.out.println("User does not have this index. Please choose a different index.");
                return;
            }
        }

        do{
            System.out.print("Confirm (Y/N)? ");
            response = s1.next();
        }while(!((response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N"))));

        if(response.equalsIgnoreCase("Y"))
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
                        try {
                            FileOutputStream fos = new FileOutputStream("./src/data/registerStudentList");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);   
                            oos.writeObject(registerStudentList);
                            oos.close();
                            }
                        
                        catch(Exception ex) {
                            ex.printStackTrace();
                            }
                    }
                }
            }
        }
        else
            System.out.println("Unsuccessful. Bye!");
    }

    public static void checkCoursesRegistered(User currentUser, ArrayList registerStudentList) 
    {
        RegisterStudent r;
        Course c;
        User u;

        if(checkRegisterStudentList(currentUser, registerStudentList) == 1)
            return;
            
        try {
            FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            }
        catch (Exception e) {
                e.printStackTrace();
            }

        System.out.printf("\n%-15s %-10s %-10s\n","Course Code", "School", "Index");
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
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

        try {
            FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            }
        catch (Exception e) {
                e.printStackTrace();
            }

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

        try {
            FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            }
        catch (Exception e) {
                e.printStackTrace();
            }

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

        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            } 
        catch (Exception e) {
                e.printStackTrace();
            }

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
            i++;
            
            courseList.add(cor);

            try {
                FileOutputStream fos = new FileOutputStream("./src/data/courseList");
                ObjectOutputStream oos = new ObjectOutputStream(fos);   
                oos.writeObject(courseList);
                oos.close();
                }
        
            catch(Exception ex) {
                ex.printStackTrace();
            }

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

            try {
                FileOutputStream fos = new FileOutputStream("./src/data/courseList");
                ObjectOutputStream oos = new ObjectOutputStream(fos);   
                oos.writeObject(courseList);
                oos.close();
                }
        
            catch(Exception ex) {
                ex.printStackTrace();
            }

        }else{
            System.out.print("nope!");
        }
    }

    public static void editStudentAccessPeriod(ArrayList userList){
        int startDate, startMonth, startYear, endDate, endMonth, endYear;
        startDate = startMonth = startYear = endDate = endMonth = endYear = 0;
        int dateCheck = 0;
        int matricNumber;
        Calendar newDate = null; 
        User u = null;
        Scanner s1 = new Scanner(System.in);

        System.out.print("Please input the student's matric number: ");
        matricNumber = s1.nextInt();
        
        while(dateCheck == 0)
        {
            System.out.print("Enter new start date (DD MM YYYY): ");
            startDate = s1.nextInt();
            startMonth = s1.nextInt();
            startYear = s1.nextInt();  
            System.out.print("Enter new end date (DD MM YYYY): ");
            endDate = s1.nextInt();
            endMonth = s1.nextInt();
            endYear = s1.nextInt(); 
            if(startDate < 1 || startDate > 31 || startMonth > 12 || startMonth < 1 || startYear < 1900 || startYear > 2021 || endDate < 1 || endDate > 31 || endMonth > 12 || endMonth < 1 || endYear < 1900 || endYear > 2021)
            {
                System.out.println("Invalid date entered. Please check and re-enter.");
            }
            else
                dateCheck = 1;
        }    
        for(int i = 0; i < userList.size(); i++)
        {
            u = (User)userList.get(i);
            if(u instanceof Student)
            {
                if(((Student)u).getMatricNumber() == matricNumber)
                {
                    System.out.println(((Student)u).dateToString());
                    newDate = new GregorianCalendar(startYear, startMonth, startDate);
                    ((Student)u).setStartDate(newDate);
                    newDate = new GregorianCalendar(endYear, endMonth, endDate);
                    ((Student)u).setEndDate(newDate);
                    break;
                }
            }
        }
        System.out.println(((Student)u).dateToString());

        
    }

    // to check if new index is in the registered courses list
    public static void changeIndexNumber(ArrayList courseList, User currentUser, ArrayList registerStudentList)
    {
        Scanner s1 = new Scanner(System.in);
        int indexChoice = 0;
        Course c = null;
        RegisterStudent r;
        RegisterStudent rs;
        Index ind;
        int newVacancy;

        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            } 
        catch (Exception e) {
                e.printStackTrace();
            }
        
        try {
            FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList<Object>) ois.readObject();
            ois.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }

        checkCoursesRegistered(currentUser, registerStudentList);
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
            registerStudentList.add(rs);
            newVacancy = ((Index)c).getVacancies()-1;
            ((Index)c).setVacancies(newVacancy);

            for(int i = 0; i < registerStudentList.size(); i++)
            {
                r = (RegisterStudent)registerStudentList.get(i);
                ind = (Index)r.getCourse();
                if(indexChoice == ind.getIndexNumber())
                {
                    if(currentUser.getUsername().equals(((User)r.getUser()).getUsername()))
                    {
                        registerStudentList.remove(i);
                        newVacancy = ind.getVacancies()+1;
                        ind.setVacancies(newVacancy);
                    }
                }
            }   
            try {
                FileOutputStream fos = new FileOutputStream("./src/data/registerStudentList");
                ObjectOutputStream oos = new ObjectOutputStream(fos);   
                oos.writeObject(registerStudentList);
                oos.close();
                }
            
            catch(Exception ex) {
                ex.printStackTrace();
                }
        }
        else
            System.out.println("Unsuccessful. Bye!");
    }

    public static void swopIndexStudent(ArrayList courseList, User currentUser, ArrayList registerStudentList, ArrayList userList)
    {
        Scanner s1 = new Scanner(System.in);
        Course c, tempCourse;
        RegisterStudent r;
        User u;

        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            } 
        catch (Exception e) {
            e.printStackTrace();
            }
        
        try {
            FileInputStream fis = new FileInputStream("./src/data/registerStudentList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList<Object>) ois.readObject();
            ois.close();
            }
        catch (Exception e) {
            e.printStackTrace();
            }
        
        try {
            FileInputStream fis = new FileInputStream("./src/data/userList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<Object>) ois.readObject();
            ois.close();
                
            }
        catch (Exception e) {
            e.printStackTrace();
            }
    

        System.out.printf("\n%s's courses:\n", currentUser.getName());
        checkCoursesRegistered(currentUser, registerStudentList);
        System.out.print("Which of your index do you wish to change with? ");
        int indexChoice = s1.nextInt();

        System.out.println("Please provide your peer's login particulars:");
        User peer = login(userList);

        System.out.printf("\n%s's courses:!\n", peer.getName());
        checkCoursesRegistered(peer, registerStudentList);
        System.out.print("Which of peer's index do you wish to change with? ");
        int peerChoice = s1.nextInt();

        //confirm with user

        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
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
    public static int promptIndexNumber(ArrayList courseList)
    {
        String s;
        int check = 1;
        Scanner s1 = new Scanner(System.in);
        Course c;
        int indexChoice = 0;
        try {
            FileInputStream fis = new FileInputStream("./src/data/courseList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList<Object>) ois.readObject();
            ois.close();
            
            } 
        catch (Exception e) {
                e.printStackTrace();
            }
            
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
                if(check != 2)
                    System.out.println("Invalid index number. Please retry.");
            }
            catch(Exception e){
                System.out.println("Invalid index number. Please retry.");
                s = s1.nextLine();
            }
        }while(check == 1);
        return indexChoice;
    }

    public static int checkRegisterStudentList(User currentUser, ArrayList registerStudentList){
        RegisterStudent r;
        Course c;
        User u;
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
                if(!(u.getUsername().equals(currentUser.getUsername()))){
                    System.out.println("User does not have any courses registered. Please choose a different option.");
                    return 1;
                }
            }
        }
        return 0;
    }
    public static void sendWaitlistNotif(User currentUser, ArrayList registerStudentList)
    {
        //sample
        String course = "CZ2002";
        int index = 20021;

        final String username = "oatarabica@gmail.com";
		final String password = "absolutmunch";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    
            // if student A dropCourse oodp, student B in the waitlist will addCourse into oodp. Edit student A & student B's registered list.
            // send email to student B to inform them of successful allocation!

            // get student B's username from Users data
            // change email recipient to username@e.ntu.edu.sg

            // send them course & index details

        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oatarabica@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("andrelchew@icloud.com")); // to change to student email
            message.setSubject("Course Allocation");
            message.setText("Congrats! You have been allocated to the course " + course + " of index " + index +"! Please check your degree audit.");

            Transport.send(message);

            // run addCourse
            System.out.println("\nStudent added! Email notification sent to next student on waitlist!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            FileOutputStream fos = new FileOutputStream("./src/data/registerStudentList");
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(registerStudentList);
            oos.close();
            }
        
        catch(Exception ex) {
            ex.printStackTrace();
            }
    }
}