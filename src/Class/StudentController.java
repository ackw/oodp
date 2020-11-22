package Class;
import java.text.ParseException;
import java.time.LocalDateTime.*;
import java.time.*;
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

public class StudentController {

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
        ArrayList waitList = userController.getWaitList();
        RegisterStudent r;
        WaitList w;
        Course c;
        Index ind = userController.findIndex(index);
        int courseAU = 0;
        if(ind == null){
            return "Index not found.";
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
            if (u.getUsername().equals(s.getUsername()) && (s.getCurrentAUs() + c.getAcademicUnits() > s.getMaxAUs())){
                return "Unable to register, will exceed maximum AUs!";
            }
        }
    
        // waitlist
        if(ind.getVacancies() < 1){
            System.out.println("This course is full at the moment. You'll be added to the waiting list.");
            emails = 3;
            String name = s.getName();
            String usern = s.getUsername();
            String code = ind.getCourseCode();
            int num = ind.getIndexNumber();
    
            Email(name, code, num, usern, registerStudentList);
    
            w = new WaitList(s, ind);
            waitList.add(w);
            userController.editWaitList();
            // return "This course is full at the moment. You'll be added to the waiting list. Please check your email.";
            return "";
        }
    
        r = new RegisterStudent(s, ind);
        registerStudentList.add(r);
        int newVacancy = ((Index)ind).getVacancies()-1;
        ((Index)ind).setVacancies(newVacancy);
        userController.editRegisterStudentList();
        userController.editCourseList();
    
        System.out.println("You have successfully registered for the course.");
        emails = 1;
        String name = s.getName();
        String usern = s.getUsername();
        String code = ind.getCourseCode();
        int num = ind.getIndexNumber();
    
        Email(name, code, num, usern, registerStudentList);
        int addAU = s.getCurrentAUs() + courseAU;
        s.setCurrentAUs(addAU);

        // return "You have successfully registered for the course."; //implement send cfm emnail
        return "";
    }
    
    public String dropCourse(int index, Student s){
        ArrayList registerStudentList = userController.getRegisterStudentList();
        ArrayList waitList = userController.getWaitList();

        RegisterStudent r;
        WaitList w;
        Course c;
        User u;
        boolean checkIndex = false;
        Index ind = userController.findIndex(index);
        if(ind == null){
            return "Index not found.";
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
                    int newVacancy = ((Index)ind).getVacancies()+1;
                    ((Index)ind).setVacancies(newVacancy);
                    userController.editRegisterStudentList();
                    userController.editCourseList();

                    System.out.println("You have successfully dropped the course.");
    
                    // send email
                    emails = 2; 
                    String name = s.getName();
                    String usern = s.getUsername();
                    String code = ind.getCourseCode();
                    int num = ind.getIndexNumber();
    
                    Email(name, code, num, usern, registerStudentList);
    
                    // waitlist
                    for(int j = 0; j < waitList.size(); j++){
                        w = (WaitList)waitList.get(j);
                        ind = (Index)w.getCourse();
    
                        if(index == ind.getIndexNumber())
                        {
                            Student t = (Student)w.getUser();
                            //addCourse(index, t);

                            // register student
                            for(int q = 0; q < registerStudentList.size(); q++)
                            {
                                r = (RegisterStudent)registerStudentList.get(i);
                                c = r.getCourse();
                                u = r.getUser();
                            }
                        
                            r = new RegisterStudent(t, ind);
                            registerStudentList.add(r);
                            int newVacancy2 = ((Index)ind).getVacancies()-1;
                            ((Index)ind).setVacancies(newVacancy2);
                            userController.editRegisterStudentList();
                            userController.editCourseList();

                            waitList.remove(j);

                            // send email
                            emails = 4;
                            String name2 = t.getName();
                            String usern2 = t.getUsername();
                            String code2 = ind.getCourseCode();
                            int num2 = ind.getIndexNumber();
        
                            Email(name2, code2, num2, usern2, waitList);
                        }
                    }
                    // return "You have successfully dropped the course. Please check your email.";
                    return "";
                }
            }
        }
        return null;
    }

    public static void Email(String name, String course, int index, String usern, ArrayList registerStudentList)
    {
        String recipient = "", subject = "", msg = "", pr = "";

        //email sender
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

        recipient = usern + "@e.ntu.edu.sg";

        switch(emails){
            case 1: // add course
                subject = "Course Allocation";
                msg = "Dear " + name + ", \n\n Congrats! You have been allocated to " + course + ", index " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n ** This is an automated email. Please do not reply. **";
                pr = "An email confirmation has been sent to your email.";
                break;
            case 2: // drop course
                subject = "Course Dropped";
                msg = "Dear " + name + ", \n\n You have been removed from " + course + ", index " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n ** This is an automated email. Please do not reply. **";
                pr = "An email confirmation has been sent to your email.";
                break;
            case 3: // waitlist confirmation
                subject = "Course Waitlist";
                msg = "Dear " + name + ", \n\n You are currently in the waiting list for " + course + ", index " + index +". You will be informed when a slot is available. \n\n Regards, \n The NTU Registry \n ** This is an automated email. Please do not reply. **";
                pr = "An email confirmation has been sent to your email.";
                break;
            case 4: // waitlist success
                subject = "Course Allocation";
                msg = "Dear " + name + ", \n\n Congrats! The wait is over. You have been allocated to " + course + ", index " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n ** This is an automated email. Please do not reply. **";
                pr = "Student " + name + " on the waitlist has been added to " + course + ", index " + index + ".";
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
            // System.out.println("\nAn email notification has been sent.");
            System.out.println(pr);

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
        if(!oldIndex.getCourseCode().equals(newIndex.getCourseCode()))
            return "New index choice must be the same course as old index choice.";

        if(oldIndex == null || newIndex == null)
            return "Invalid index choice. Please try again.";

        ArrayList registerStudentList = userController.getRegisterStudentList();
        ArrayList courseList = userController.getCourseList();
        ArrayList waitList = userController.getWaitList();
        Boolean checkIndex = false;
        RegisterStudent r;
        Course c;
        User u;
        int newVacancy;
        WaitList w;
        
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
                return "User already has this index. Please choose a different index.";
        }

        if(newIndex.getVacancies() < 1){
            emails = 3;
            String name = s.getName();
            String usern = s.getUsername();
            String code = newIndex.getCourseCode();
            int num = newIndex.getIndexNumber();
    
            Email(name, code, num, usern, registerStudentList);
    
            System.out.println("This course is full at the moment. You'll be added to the waiting list.");

            w = new WaitList(s, newIndex);
            waitList.add(w);
            userController.editWaitList();
            return "";

            //return "This course is full at the moment. You'll be added to waiting list."; //implement later
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
        int totalAU = 0;
        if(checkRegisterStudentList(s) == 1) //checks if student has any courses registered before doing anything
            return;
        System.out.printf("\n%-15s %-10s %-10s %-3s\n","Course Code", "School", "Index", "AUs");
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            if(u.getUsername().equals(s.getUsername())){
                System.out.printf("%-15s %-10s %-10s %-3d\n", c.getCourseCode(), c.getSchool(), ((Index)c).getIndexNumber(), ((Index)c).getAcademicUnits());
                totalAU += ((Index)c).getAcademicUnits();
            }
        }
        System.out.println("Total AUs: " + totalAU);
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
        else { System.out.printf("Not in access period.\n\n");
            return false;
        }

    }

    
}