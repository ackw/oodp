import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

// imports for email
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class StudentController{

    UserController userController = new UserController();
    Scanner s1 = new Scanner(System.in);
    Console console = System.console();
    public static int emails = 0; // index for email

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
        int choice = 9;
        int index = 0;
        System.out.println(s.getSchoolID());
        
        while(choice != 0){
            AdminController ac = new AdminController();
            displayMenu();
            choice = s1.nextInt();
            s1.nextLine();
            switch(choice){
                case 1:
                    if (checkAccessPeriod(s.getSchoolID()) == false) {
                        break;
                    }
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
                    // if (checkAccessPeriod(s.getSchoolID()) == false) {
                    //     break;
                    // }
                    System.out.println();
                    System.out.println("2. Drop Course");
                    printCoursesRegistered(s);
                    try{
                        index = promptIndex();
                        System.out.println(dropCourse(index, s));
                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
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
                        if(userController.findIndex(index) == null)
                            System.out.println("Invalid index number. Please try again.");
                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
                    System.out.println();
                    break;
                case 5: 
                    System.out.println();
                    System.out.println("5. Change Index Number of Course");
                    System.out.printf("\nHere's the list of all currently registered courses:\n====================================================");
                    printCoursesRegistered(s);
                    try{
                        index = promptIndex();
                        System.out.printf("\nHere's the list of all courses:\n===============================");
                        userController.showCourseInfo();
                        System.out.print("Enter new index number:");
                        int newIndex = s1.nextInt();
                        System.out.println(changeIndexNumber(index, newIndex, s));

                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
                    System.out.println();
                    break;
                case 6: 
                    System.out.println();
                    System.out.println("6. Swop Index Number with Another Student");
                    System.out.printf("\n%s's currently registered courses:\n====================================================", s.getName());
                    printCoursesRegistered(s);
                    System.out.println("Enter the index you'd wish to change: ");
                    index = s1.nextInt();
                    s1.nextLine();
                    System.out.print("Enter username of student you're swopping with: ");
                    String username = s1.nextLine();
                    char[] password = console.readPassword("Enter password of student you're swopping with: ");
                    String inputPw = new String(password);
                    User user = userController.login(username, inputPw);
                    
                    if(user.getUsername() == s.getUsername()){
                        System.out.println("New user cannot be the same as current user. Please try again.");
                    }
                    if(user instanceof Admin){
                        System.out.printf("User is not a student. Please try again.\n\n");
                        break;
                    }
                    Student s2 = (Student) user;
                    System.out.printf("\n%s's currently registered courses:\n====================================================", s2.getName());
                    printCoursesRegistered(s2);
                    System.out.println("Enter the index you'd wish to change with: ");
                    int index2 = s1.nextInt();
                    System.out.println(swopIndexStudent(s, index, s2, index2));
                    System.out.println();
                    break;
                case 0: 
                    System.out.println("");
                    break;
                default: break;
            }
        }
    }

    public String addCourse(int index, Student s) {
        ArrayList registerStudentList = userController.getRegisterStudentList();
        RegisterStudent r;
        Course c;
        Index ind = userController.findIndex(index);
        int courseAU = 0;
        if(ind == null){
            return "can't find index la";
        }
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            User u = r.getUser();
            courseAU = c.getAcademicUnits();
            if(u.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                return "User already has this module. Please choose a different index.";
            
            if(u.getUsername().equals(s.getUsername()) && c.getCourseCode().equals(ind.getCourseCode()))
                return "You are not allowed to register multiple index of the same course.";
            if(u.getUsername().equals(s.getUsername()) && (s.getCurrentAUs() + c.getAcademicUnits() > s.getMaxAUs())){
                return "Unable to register, will exceed maximum AUs!";
            }
        }

        // waitlist
        if(ind.getVacancies() < 1){
            emails = 3;
            // using username to get their email address
            String name = s.getName();
            String usern = s.getUsername();
            String code = ind.getCourseCode();
            int num = ind.getIndexNumber();

            Email(name, code, num, usern, registerStudentList);
            return "This course is full at the moment. You'll be added to waiting list."; //implement later
        }
        
        r = new RegisterStudent(s, ind);
        registerStudentList.add(r);
        int newVacancy = ((Index)ind).getVacancies()-1;
        ((Index)ind).setVacancies(newVacancy);
        userController.editRegisterStudentList();
        userController.editCourseList();
        //send cfm email?? to be implemented

        emails = 1;
        // using username to get their email address
        String name = s.getName();
        String usern = s.getUsername();
        String code = ind.getCourseCode();
        int num = ind.getIndexNumber();

        Email(name, code, num, usern, registerStudentList);
        int addAU = s.getCurrentAUs() + courseAU;
        s.setCurrentAUs(addAU);

        return "You have successfully registered for the course."; //implement send cfm emnail
    }

    public String dropCourse(int index, Student s){
        ArrayList registerStudentList = userController.getRegisterStudentList();
        RegisterStudent r;
        Course c;
        User u;
        int courseAU2 = 0;
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
            courseAU2 = c.getAcademicUnits();
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
                    int newVacancy = ((Index)ind).getVacancies()+1;
                    ((Index)ind).setVacancies(newVacancy);
                    //send email
                    userController.editRegisterStudentList();
                    userController.editCourseList();
                    //send cfm email?? to be implemented

                    // send email to current user
                    emails = 2;
                    // using username to get their email address
                    String name = s.getName();
                    String usern = s.getUsername();
                    String code = ind.getCourseCode();
                    int num = ind.getIndexNumber();

                    Email(name, code, num, usern, registerStudentList);
                    int minusAU = s.getCurrentAUs() - courseAU2;
                    s.setCurrentAUs(minusAU);

                    return "You have successfully dropped the course."; //tobeimplemented send cfm email
                }
            }
        }
        return null;
    }

    public static void Email(String name, String course, int index, String usern, ArrayList registerStudentList)
    {
        String recipient = "", subject = "", msg = "";

        //email sent from
        final String username = "oatarabica@gmail.com";
		final String password = "absolutmunch";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // option 1: add course
        // option 2: drop course
        // option 3: in waitlist
        // option 4: outta waitlist

        recipient = usern + "@e.ntu.edu.sg";

        switch(emails){
            case 1: 
                System.out.println("Sending add course email...");
                subject = "Course Allocation";
                msg = "Dear " + name + ", \n\n Congrats! You have been allocated to " + course + ", index " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n\n ** This is an automated email. Please do not reply. **";
                break;
            case 2: 
                System.out.println("Sending drop course email...");
                subject = "Course Dropped";
                msg = "Dear " + name + ", \n\n You have been removed from " + course + ", index " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n\n ** This is an automated email. Please do not reply. **";
                break;
            case 3: 
                System.out.println("Sending waitlist email...");
                subject = "Course Waitlist";
                msg = "Dear " + name + ", \n\n You are currently in the waiting list for " + course + ", index " + index +". You will be informed when a slot is available. \n\n Regards, \n The NTU Registry \n\n ** This is an automated email. Please do not reply. **";
                break;
            case 4: 
                System.out.println("Sending waitlist email...");
                subject = "Course Allocation";
                msg = "Dear " + name + ", \n\n Congrats! The wait is over. You have been allocated to " + course + ", index " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n\n ** This is an automated email. Please do not reply. **";
                break;
            default:
                break;
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oatarabica@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);
            System.out.println("\nAn email notification has been sent.");

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

    private String changeIndexNumber(int indexChoice, int newIndexChoice, Student s) {
        Index oldIndex = userController.findIndex(indexChoice);
        Index newIndex = userController.findIndex(newIndexChoice);

        if(oldIndex == null || newIndex == null)
            return "Invalid index choice. Please try again.";

        ArrayList registerStudentList = userController.getRegisterStudentList();
        ArrayList courseList = userController.getCourseList();
        Boolean checkIndex = false;
        RegisterStudent r;
        Course c;
        User u;
        int newVacancy;
        
        //checks if user has the courseindex registered
        for(int i = 0; i < registerStudentList.size(); i++){ 
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(s.getUsername()) && indexChoice == ((Index)c).getIndexNumber()){
                checkIndex = true;
            }
        }
        if(!checkIndex)
            return "User does not have this index. Please choose a different index.";

        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(s.getUsername()) && newIndexChoice == ((Index)c).getIndexNumber())
                return "User already has this module. Please choose a different index.";
        }
        if(newIndex.getVacancies() < 1){
            return "This course is full at the moment. You'll be added to waiting list."; //implement later
        }
        
        r = new RegisterStudent(s, newIndex);
        registerStudentList.add(r);
        newVacancy = ((Index)newIndex).getVacancies()-1;
        ((Index)newIndex).setVacancies(newVacancy);
        userController.editRegisterStudentList();
        userController.editCourseList();

        dropCourse(indexChoice, s);

        String returnMsg = String.format("Successfully switched index %d with %d", indexChoice, newIndexChoice);
        return returnMsg;
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

    public String swopIndexStudent(Student s, int index, Student s2, int index2){
        Index i1 = userController.findIndex(index);
        Index i2 = userController.findIndex(index2);
        RegisterStudent r;
        Course c;
        User u;
        Boolean checkIndex, checkIndex2;

        checkIndex = checkIndex2 = false;
        ArrayList registerStudentList = userController.getRegisterStudentList();

        if(i1 == null || i2 == null)
            return "Invalid index entered. Please try again.";

        for(int i = 0; i < registerStudentList.size(); i++){
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                checkIndex = true;
            if(u.getUsername().equals(s2.getUsername()) && index2 == ((Index)c).getIndexNumber())
                checkIndex2 = true;
        }
        if(i2.getIndexNumber() == i1.getIndexNumber())
            return "Both index numbers are the same. Please choose a different index.";
        if(!checkIndex && checkIndex2)
            return "User does not have this index. Please choose a different index.";
        //tobeimplemented add check user does not have 2 of same course after adding
        //do the swop swop swop 
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            u = r.getUser();
            c = r.getCourse();
            if(u.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                r.setUser(s2);
            if(u.getUsername().equals(s2.getUsername()) && index2 == ((Index)c).getIndexNumber())
                r.setUser(s);
        }
        String returnMsg = String.format("Swopped %s's index of %d with %s's index of %d.", s.getName(), index, s2.getName(), index2);
        return returnMsg;
    }

    public int promptIndex(){
        Scanner s1 = new Scanner(System.in);
        System.out.print("Enter index number: ");
        int index = s1.nextInt();
        return index;
    }
    public boolean checkAccessPeriod(String school){
        LocalDateTime start = null;
        LocalDateTime end = null;
        ArrayList schoolList = userController.getSchoolList();


        School sch = new School();
        for(int i = 0; i < schoolList.size(); i++){
            sch = (School) schoolList.get(i);
            if(sch.getSchoolID().equals(school))
                break;
        }

        start = sch.getStartAccess();
        end = sch.getEndAccess();

        LocalDateTime curr = LocalDateTime.now();

        if (start.isBefore(curr) && end.isAfter(curr)) {
            return true;
        }
        else { System.out.println("Not in access period.");
            return false;
        }

    }

    
}
