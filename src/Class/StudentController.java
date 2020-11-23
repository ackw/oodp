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


/**
 Represents the functions of a student.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
 @version 1.0
 @since 2020-11-23
*/

public class StudentController {

    UserController userController = new UserController();
    Scanner s1 = new Scanner(System.in);
    Console console = System.console();
    public static int emails = 0;

    /**
     * Displays Student menu
     */
    public void displayMenu(){
        System.out.println("Menu");
        System.out.println("=====");
        System.out.println("1. Add Course");
        System.out.println("2. Drop Course");
        System.out.println("3. Check/Print Courses Registered");
        System.out.println("4. Check Vacancies Available");
        System.out.println("5. Change Index Number of Course");
        System.out.println("6. Swop Index Number with Another Student");
        System.out.println("0. Logout");
        System.out.print("\nEnter choice: ");
    }
    /**
     * This method calls displayMenu() and prompts for choice of function
     */
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
                    System.out.println("\n1. *Add Course");
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
                    if (checkAccessPeriod(s.getSchoolID()) == false) {
                        break;
                    }
                    System.out.println("\n2. Drop Course");
                    if(checkRegisterStudentList(s) != 1){
                        printCoursesRegistered(s);
                        try{
                            index = promptIndex();
                            System.out.println(dropCourse(index, s));
                        } catch(Exception e){
                            System.out.println("Error, try again. ");
                        }
                        break;
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.println("\n3. Check/Print Courses Registered");
                    printCoursesRegistered(s);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("\n4. Check Vacancies Available");
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
                    if (checkAccessPeriod(s.getSchoolID()) == false) {
                        break;
                    }
                    System.out.println("\n5. Change Index Number of Course");
                    System.out.printf("\nHere's the list of all currently registered courses:\n====================================================");
                    printCoursesRegistered(s);
                    try{
                        index = promptIndex();
                        System.out.printf("\nHere's the list of all courses:\n===============================");
                        userController.showCourseInfo();
                        System.out.print("Enter new index number:");
                        int newIndex = s1.nextInt();

                        if(checkSchedule(newIndex, s))
                        {
                            System.out.printf("Adding of new course failed.\n\n");
                            break;
                        }

                        System.out.println(changeIndexNumber(index, newIndex, s));

                    } catch(Exception e){
                        System.out.println("Error, try again. ");
                    }
                    System.out.println();
                    break;
                case 6:
                    if (checkAccessPeriod(s.getSchoolID()) == false) {
                        break;
                    }
                    System.out.println("\n6. Swop Index Number with Another Student");
                    System.out.printf("\n%s's currently registered courses:\n====================================================", s.getName());
                    printCoursesRegistered(s);
                    System.out.print("Enter the index you'd wish to change: ");
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

                    if(checkSchedule(index2, s))
                        {
                            System.out.printf("Adding of new course failed.\n\n");
                            break;
                        }

                    System.out.println(swopIndexStudent(s, index, s2, index2));
                    System.out.println();
                    break;
                case 0:
                    break;
                default: break;
            }
        }
    }


    /**
     * This method takes in the index and current user (Student s) and adds index to student.
     * @param index Index to be added
     * @param s Student that current wants to add a course.
     * @return String Relevant message that is returned to user. Could be either success or error message.
     */
    public String addCourse(int index, Student s) {
        ArrayList registerStudentList = userController.getRegisterStudentList();
        ArrayList waitList = userController.getWaitList();
        RegisterStudent r;
        WaitList w;
        Course c;
        Index ind = userController.findIndex(index);
        int courseAU = 0;
        if(ind == null){
            return "Invalid index entered. Please try again.";
        }
        for(int i = 0; i < registerStudentList.size(); i++)
        {
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            User u = r.getUser();
            courseAU = c.getAcademicUnits();
            if(u.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                return "You have previously registered for this module. Please choose a different course.";

            if(u.getUsername().equals(s.getUsername()) && c.getCourseCode().equals(ind.getCourseCode()))
                return "You are not allowed to register multiple index of the same course.";
            if (u.getUsername().equals(s.getUsername()) && (s.getCurrentAUs() + c.getAcademicUnits() > s.getMaxAUs())){
                return "Unable to register, will exceed maximum AUs!";
            }
        }

        if(checkSchedule(index, s))
            return "Adding of new course failed.";

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
        return "";
    }


    /**
     * This method takes in the index and current user (Student s) and drops index from student.
     * @param index Index to be added.
     * @param s Student that wishes to drop the course.
     * @return String Relevant message that is returned to user. Could be either success or error message.
     */
    public String dropCourse(int index, Student s){
        ArrayList registerStudentList = userController.getRegisterStudentList();
        ArrayList waitList = userController.getWaitList();
        RegisterStudent r;
        RegisterStudent r2;
        int courseAU2 = 0;
        WaitList w;
        Course c;
        User u;
        boolean checkIndex = false;
        Index ind = userController.findIndex(index);
        if(ind == null){
            return "Invalid index entered. Please try again.";
        }

        for(int i = 0; i < registerStudentList.size(); i++){
            r = (RegisterStudent)registerStudentList.get(i);
            c = r.getCourse();
            u = r.getUser();
            courseAU2 = c.getAcademicUnits();
            if(u.getUsername().equals(s.getUsername()) && index == ((Index)c).getIndexNumber())
                checkIndex = true;
        }

        if(!checkIndex)
            return "You have not registered for this index. Please try again.";

        for(int j = 0; j < registerStudentList.size(); j++){
            r = (RegisterStudent)registerStudentList.get(j);
            ind = (Index)r.getCourse();
            if(index == ind.getIndexNumber())
            {
                if(s.getUsername().equals(((User)r.getUser()).getUsername()))
                {
                    registerStudentList.remove(j);
                    int newVacancy = ((Index)ind).getVacancies()+1;
                    ((Index)ind).setVacancies(newVacancy);
                    userController.editRegisterStudentList();
                    userController.editCourseList();

                    System.out.println("You have successfully dropped the course.");

                    // send email for current user
                    emails = 2;
                    String name = s.getName();
                    String usern = s.getUsername();
                    String code = ind.getCourseCode();
                    int num = ind.getIndexNumber();

                    Email(name, code, num, usern, registerStudentList);

                    // waitlist
                    for(int k = 0; j < waitList.size(); k++){
                        w = (WaitList)waitList.get(k);
                        ind = (Index)w.getCourse();

                        if(index == ind.getIndexNumber())
                        {
                            Student t = (Student)w.getUser();

                            // register student
                            for(int q = 0; q < registerStudentList.size(); q++)
                            {
                                r2 = (RegisterStudent)registerStudentList.get(k);
                                c = r2.getCourse();
                                u = r2.getUser();
                            }

                            r2 = new RegisterStudent(t, ind);
                            registerStudentList.add(r2);
                            int newVacancy2 = ((Index)ind).getVacancies()-1;
                            ((Index)ind).setVacancies(newVacancy2);
                            userController.editRegisterStudentList();
                            userController.editCourseList();

                            waitList.remove(j);

                            int minusAU = s.getCurrentAUs() - courseAU2;
                            s.setCurrentAUs(minusAU);

                            // send email for waitlist user
                            emails = 4;
                            String name2 = t.getName();
                            String usern2 = t.getUsername();
                            String code2 = ind.getCourseCode();
                            int num2 = ind.getIndexNumber();
                            Email(name2, code2, num2, usern2, waitList);
                        }
                        
                    }
                    return "";
                }
            }
        }
        return null;
    }


    /**
     * This method sending an email notification to the users involved, including details such as name, course and index.
     * @param name Name of email recipient.
     * @param course Course of email recipient.
     * @param index Index number of email recipient.
     * @param usern Username of email recipient.
     * @param registerStudentList Registered students data list.
     */
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
            case 5: // change index confirmation
                subject = "Course Allocation";
                msg = "Dear " + name + ", \n\n Congrats! Your index number for " + course + " has been updated to " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n ** This is an automated email. Please do not reply. **";
                pr = "";
                break;
            case 6: // swop index confirmation
                subject = "Course Allocation";
                msg = "Dear " + name + ", \n\n Congrats on the successful swop! Your index number has been updated to " + index +". Please check your degree audit. \n\n Regards, \n The NTU Registry \n ** This is an automated email. Please do not reply. **";
                pr = "An email confirmation has been sent to "+ name +"'s email.";
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


    /**
     * This index takes in both index choices, current and new, as well as current user Student s.
     * This processes the checks and swopping of index for the student.
     * @param indexChoice Current index
     * @param newIndexChoice New index to change to
     * @param s Current user
     * @return String Relevant message to be returned back to user. Could be both error or success message.
     */
    private String changeIndexNumber(int indexChoice, int newIndexChoice, Student s) {
        Index oldIndex = userController.findIndex(indexChoice);
        Index newIndex = userController.findIndex(newIndexChoice);
        if(!oldIndex.getCourseCode().equals(newIndex.getCourseCode()))
            return "New index choice must be the same course as old index choice.";

        if(oldIndex == null || newIndex == null)
            return "Invalid index entered. Please try again.";

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
            return "You have not registered for this index. Please try again.";

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
        }

        r = new RegisterStudent(s, newIndex);
        registerStudentList.add(r);
        newVacancy = ((Index)newIndex).getVacancies()-1;
        ((Index)newIndex).setVacancies(newVacancy);
        userController.editRegisterStudentList();
        userController.editCourseList();

        dropCourse(indexChoice, s);

        emails = 5;
        String name = s.getName();
        String usern = s.getUsername();
        String code = newIndex.getCourseCode();
        int num = newIndex.getIndexNumber();

        Email(name, code, num, usern, registerStudentList);

        String returnMsg = String.format("Successfully switched index %d with %d", indexChoice, newIndexChoice);
        return returnMsg;
    }


    /**
     * This method processes the printing for all courses currently registered by Student s that is passed into the method.
     * @param s
     */
    public void printCoursesRegistered(Student s){
        RegisterStudent r;
        Course c;
        User u;
        ArrayList<RegisterStudent> registerStudentList = userController.getRegisterStudentList();
        int totalAU = 0;
        if(checkRegisterStudentList(s) == 1) {//checks if student has any courses registered
            return;
        }
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


    /**
     * This method performs the validation checks for printCoursesRegistered(Student s).
     * It helps to check if user has registered for any modules.
     * In the case the user has courses registered, 0 is returned to user. 
     * Alternatively, 1 is returned to user if user has no courses registered.
     * @param s Student S to be checked for.
     * @return int 0 is returned if user has courses registered, 1 is returned if user has none.
     */
    public int checkRegisterStudentList(Student s){
        RegisterStudent r;
        Course c;
        User u;
        boolean checkUser = false;
        ArrayList<RegisterStudent> registerStudentList = userController.getRegisterStudentList();
        if(registerStudentList.size() == 0){
            System.out.println("You have not registered for any courses. Please choose a different option.");
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
                System.out.println("You have not registered for any courses. Please choose a different option.");
                return 1;
            }
        }
        return 0;
    }


    /**
     * This method performs the swopping of index between students.
     * @param s  Current logged in student
     * @param index Index of currently logged in student to swop with.
     * @param s2 Student to swop index with.
     * @param index2 Index of student to swop index with.
     * @return String Relevant message will be returned, both error or success messages.
     */
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

        if(!i1.getCourseCode().equals(i2.getCourseCode()))
            return "Both new and old index entered must be of the same course.";

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
            return "Both index numbers entered are the same. Please choose a different index.";
        if(!checkIndex && checkIndex2)
            return "You have not registered for this index. Please try again.";
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

        emails = 6;
        String name = s.getName();
        String usern = s.getUsername();
        String code = "";
        int num = index;

        Email(name, code, num, usern, registerStudentList);

        String name2 = s2.getName();
        String usern2 = s2.getUsername();
        String code2 = "";
        int num2 = index2;

        Email(name2, code2, num2, usern2, registerStudentList);

        String returnMsg = String.format("Swopped %s's index of %d with %s's index of %d.", s.getName(), index, s2.getName(), index2);
        return returnMsg;
    }


    /**
     * @return int
     */
    public int promptIndex(){
        Scanner s1 = new Scanner(System.in);
        System.out.print("\nEnter index number: ");
        int index = s1.nextInt();
        return index;
    }

    /**
     * This method is called whenever access period has to be checked.
     * It takes in school variable that is passed in and returns boolean.
     * @param school School value that is passed in.
     * @return boolean Return TRUE if valid access period, return FALSE if invalid or null.
     */
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
        else if (end.isBefore(curr)) {
            System.out.printf("Your access period is over. You are not allowed to register for courses now.\n\n");
            return false;
        }
        else if (start.isAfter(curr)) {
            System.out.printf("Your access period has not started. You are not allowed to register for courses now.\n\n");
            return false;
        }
        else{
            System.out.printf("Not in access period.\n\n");
            return false;
        }

    }


    /**
     * This method is called whenever schedule has to be checked.
     * It takes in school variable that is passed in along with index to check with and returns boolean.
     * @param index Index whose schedule to check with
     * @param s Student to check if new index will clash with student's currently existing registered courses.
     * @return boolean Return TRUE if there are clash, return FALSE if there are no clashes.
     */
    public boolean checkSchedule(int index, Student s)
    {
        ArrayList<RegisterStudent> registerStudentList = userController.getRegisterStudentList();
        RegisterStudent r;
        Index currentIndex;
        Student u;
        Index ind = userController.findIndex(index);
        Schedule newSchedule = ind.getSchedule();
        for(int i = 0; i < registerStudentList.size(); i++){
            r = (RegisterStudent)registerStudentList.get(i);
            u = (Student)r.getUser();
            currentIndex = (Index)r.getCourse();

            if(u.getUsername().equals(s.getUsername())){
                if(newSchedule.checkConflict(currentIndex.getSchedule()) == true)
                    return true;
            }
        }
        return false;
    }
}
