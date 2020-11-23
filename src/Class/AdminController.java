package Class;

import java.time.*;
import java.util.*;
import java.time.format.*;
import java.text.ParseException;
import java.io.*;


public class AdminController{

    UserController userController = new UserController();
    Scanner s1 = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("Menu");
        System.out.println("=====");
        System.out.println("DONE 1. Edit student access period");
        System.out.println("DONE 2. Add a student (name, matric number, gender, nationality, etc)");
        System.out.println("DONE 3. Add/Update a course (course code, school, its index numbers and vacancy).");
        System.out.println("DONE 4. Check available slot for an index number (vacancy in a class)");
        System.out.println("DONE 5. Print student list by index number.");
        System.out.println("DONE 6. Print student list by course (all students registered for the selected course).");
        System.out.println("DONE 0. Exit.");
        System.out.print("Enter choice: ");
    }

    public void selectChoice() {
        Admin a = (Admin) userController.getCurrentUser();
        int choice = 9;
        int index = 0;
        
        while (choice != 0) {
            displayMenu();
            choice = s1.nextInt();
            s1.nextLine();
            switch (choice) {
                case 1:
                    displaySchoolAccessPeriod();
                    System.out.println(editAccessPeriod());
                    break;
                case 2:
                    System.out.println();
                    System.out.println("2. Add new student:");
                    userController.showSchoolInfo();
                    System.out.println(addStudent());
                    System.out.println("");
                    break;
                case 3:
                    System.out.println();
                    System.out.println("3. Add/Update a course (course code, school, its index numbers and vacancy).");
                    addUpdateCourse(userController.getCourseList(), userController.getScheduleList());
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("4. Check available slot for an index number (vacancy in a class)");
                    try{
                        System.out.print("Enter index number: "); 
                        index= s1.nextInt();
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
                    System.out.println("5. Print student list by index number.");
                    userController.showCourseInfo();
                    System.out.print("Enter index: ");
                    index = s1.nextInt();
                    printStudentListIndex(index);

                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("6. Print student list by course (all students registered for the selected course).");
                    userController.showCourseInfo();
                    printStudentListCourse();
                    System.out.println();
                    break;
                default:
                    break;
            }
        }
    }

    private void printStudentListCourse() {
        Scanner s1 = new Scanner(System.in);
        String courseID;
        ArrayList courseList = userController.getCourseList();
        ArrayList registerStudentList = userController.getRegisterStudentList();
        Course c = null;
        Boolean checkExist = false;
        RegisterStudent rs;
        int count = 0;
        Student s;

        System.out.print("Enter course code: ");
        courseID = s1.nextLine().toUpperCase();
        for(int i = 0; i < courseList.size(); i++){
            c = (Course)courseList.get(i);
            if(c.getCourseCode().equals(courseID)){
                checkExist = true;
                break;
            }
        }
        if(!checkExist){
            System.out.println("Invalid course ID. Please try again.");
            return;
        }
        for(int i = 0; i < registerStudentList.size(); i++){
            rs = (RegisterStudent) registerStudentList.get(i);
            if(rs.getCourse().getCourseCode().equals(c.getCourseCode())){
                count = -~count; 
                if(count == 1)
                    System.out.printf("\n%-20s %-7s %-10s\n", "Name", "Gender", "Nationality");
                s = (Student)rs.getUser();
                System.out.printf("%-20s %-7s %-10s\n", s.getName(), s.getGender(), s.getNationality());
                
            }
            if(count == 0)
                System.out.println("There are no students currently registered for this course code.");

        }
    }

    private void printStudentListIndex(int index) {
        Index ind;
        ArrayList<RegisterStudent> registerStudentList = userController.getRegisterStudentList();
        RegisterStudent rs;
        Student s;
        int count = 0;
        if(userController.findIndex(index) == null){
            System.out.println("Invalid index.");
            return;
        }
        System.out.printf("Students in index %d:\n", index);
        for(int i = 0; i < registerStudentList.size(); i++){
            rs = registerStudentList.get(i);
            ind = (Index)rs.getCourse();
            if(ind.getIndexNumber() == index){
                count -= 0xffffffff;
                if(count == 1)
                    System.out.printf("%-15s %-20s %-10s %-7s %-10s\n","Matric Number", "Name", "SchoolID", "Gender", "Nationality");
                s = (Student)rs.getUser();
                System.out.printf("%-15s %-20s %-10s %-7s %-10s\n", s.getMatricNumber(), s.getName(), s.getSchoolID(), s.getGender(), s.getNationality());
            }
        }
        if(count == 0)
            System.out.println("There are no students currently registered in the index.");
    }

    public void addUpdateCourse(ArrayList courseList, ArrayList scheduleList) {
        int option = 9;
        Scanner sc = new Scanner(System.in);
        Course course = null;
        School school = null;
        Boolean checkExist = false;
        ArrayList schoolList = userController.getSchoolList();

        System.out.print("Add or Update Course: ");
        String choice = sc.nextLine();
        String add = "ADD";
        String upd = "UPDATE";

        if (choice.toUpperCase().equals(add)) {
            Course cor;
            Schedule s;

            try {
                System.out.print("Course: ");
                String a = s1.nextLine().toUpperCase();
                for(int i = 0; i < courseList.size(); i++){
                    cor = (Course)courseList.get(i);
                    if(cor.getCourseCode().equals(a)){
                        checkExist = true;
                        break;
                    }
                }

                if(checkExist){
                    System.out.println("Course ID already exists. Please try again.");
                    return;
                }

                System.out.print("School: ");
                String b = s1.nextLine().toUpperCase();
                for(int i = 0; i < schoolList.size(); i++){
                    school = (School)schoolList.get(i);
                    if(school.getSchoolID().equals(b)){
                        checkExist = true;
                        break;
                    }
                }

                if(!checkExist){
                    System.out.println("Invalid School ID. Please try again.");
                    return;
                }

                System.out.print("Index: ");
                int c = sc.nextInt();

                for (int i = 0; i < courseList.size(); i++) {
                    Course cos = (Course) courseList.get(i);
                    if (c == ((Index) cos).getIndexNumber()) {
                        System.out.println("Index already exists!");
                        return;
                    }
                }

                System.out.print("Academic Units: ");
                int z = sc.nextInt();

                System.out.print("Vacancies: ");
                int d = sc.nextInt();
                System.out.print("Day of the week for lab (1 for Monday, 2 for Tuesday, 3 for Wednesday, 4 for Thursday, 5 for Friday): ");
                int labDay = sc.nextInt();

                System.out.print("Lab Start Time (24 Hours Format, E.g. 1300): ");
                String e = sc.next();
                LocalTime labTime = LocalTime.parse(e, DateTimeFormatter.ofPattern("HHmm"));
                System.out.print("Lab Week Type (1 for ODD, 2 for EVEN, 3 for BOTH): ");
                int labType = sc.nextInt();
                        
                System.out.print("Day of the week for lecture (1 for Monday, 2 for Tuesday, 3 for Wednesday, 4 for Thursday, 5 for Friday): ");
                int lecDay = sc.nextInt();
                System.out.print("Lecture Start Time (24 Hours Format, E.g. 1300): ");
                String f = sc.next();
                LocalTime lecTime = LocalTime.parse(f, DateTimeFormatter.ofPattern("HHmm"));

                System.out.print("Day of the week for tutorial (1 for Monday, 2 for Tuesday, 3 for Wednesday, 4 for Thursday, 5 for Friday): ");
                int tutDay = sc.nextInt();
                System.out.print("Tutorial Start Time (24 Hours Format, E.g. 1300): ");
                String g = sc.next();
                LocalTime tutTime = LocalTime.parse(g, DateTimeFormatter.ofPattern("HHmm"));

                if(isConflict(labDay, lecDay, tutDay, labTime, lecTime, tutTime)){
                    return;
                }

                s = new Schedule(c, numDay(labDay), oddEven(labType), numDay(lecDay), numDay(tutDay), labTime, lecTime, tutTime);
                scheduleList.add(s);
                cor = new Index(a, b, z, c, d, s); //tobeimplemented: better variable names
                courseList.add(cor);
                
                System.out.println("Added!");
                System.out.printf("\n%-15s %-10s %-10s %-10s %-15s\n", "Course Code", "School", "Index", "Vacancies", "Academic Units");
                System.out.printf("\n%-15s %-10s %-10s %-10s %-15s\n", a, b, c, d, z);
                
                userController.editCourseList();
                userController.editScheduleList();
              }

              catch(Exception e) {
                System.out.println("Invalid input!");
              }

        } else if (choice.toUpperCase().equals(upd)) {
            System.out.println("Update!\n");

            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
            for (int i = 0; i < courseList.size(); i++) {
                course = (Course) courseList.get(i);
                System.out.printf("%-15s %-10s %-10s %-10s\n", course.getCourseCode(), course.getSchool(),
                        ((Index) course).getIndexNumber(), ((Index) course).getVacancies());
            }
            
            System.out.print("Which index to update? ");
            int input = sc.nextInt();
            
            if(userController.findIndex(input) == null){
                System.out.println("Invalid index number. Please try again.");
                return;
            }

            System.out.printf("\nUpdate Options\n");
            System.out.println("==============");
            System.out.println("1. Update Course Code");
            System.out.println("2. Update School");
            System.out.println("3. Update Index");
            System.out.println("4. Update Vacancies");
            System.out.print("Select your option: ");

            option = s1.nextInt();
            s1.nextLine();

            switch (option) {
                case 1:
                    System.out.print("New course code: ");
                    String a = sc.next();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Course) cos).setCourseCode(a);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                case 2:
                    System.out.print("New school: ");
                    String b = sc.next();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Course) cos).setSchool(b);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                case 3:
                    System.out.print("New index: ");
                    int c = sc.nextInt();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (c == ((Index) cos).getIndexNumber()) {
                            System.out.println("Index already exists!");
                            return;
                        }
                    }

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Index) cos).setIndexNumber(c);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                case 4:
                    System.out.print("New vacancies: ");
                    int d = sc.nextInt();

                    for (int i = 0; i < courseList.size(); i++) {
                        Course cos = (Course) courseList.get(i);
                        if (input == ((Index) cos).getIndexNumber()) {
                            ((Index) cos).setVacancies(d);
                            System.out.println("Updated!");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", "Course Code", "School", "Index", "Vacancies");
                            System.out.printf("\n%-15s %-10s %-10s %-10s\n", cos.getCourseCode(), cos.getSchool(),
                                    ((Index) cos).getIndexNumber(), ((Index) cos).getVacancies());
                            break;
                        }
                    }
                    userController.editCourseList();
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }else{
            System.out.print("nope!");
        }
    }

    public void displaySchoolAccessPeriod(){
        ArrayList<School> schoolList = userController.getSchoolList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("");
        for(int i = 0; i < schoolList.size(); i++){
            System.out.println("School ID: " + schoolList.get(i).getName());
            System.out.println("School Name: " + schoolList.get(i).getSchoolID());
            if(schoolList.get(i).getStartAccess() != null && schoolList.get(i).getEndAccess() != null){
                System.out.println("Access Start Date: " + schoolList.get(i).getStartAccess().format(formatter));
                System.out.println("Access End Date: " + schoolList.get(i).getEndAccess().format(formatter));
            }
            else{
                System.out.println("Access Start Date: Not set.");
                System.out.println("Access End Date: Not set.");
            }
            System.out.println("");
        }
    }

    public LocalDateTime convertDate(String s){
        LocalDateTime date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try{
            date = LocalDateTime.parse(s, formatter);
        } catch(DateTimeParseException e){
            return null;
        }
        return date;
    }

    public String editAccessPeriod(){
        Scanner s1 = new Scanner(System.in);
        String schoolID, startDate, endDate;
        School school;
        LocalDateTime sd, ed;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.print("Enter the school ID: ");
        schoolID = s1.nextLine();
        school = userController.findSchool(schoolID); 
        System.out.println("");
        if(school == null)
            return "School not found.";
        if (school.getStartAccess() != null)
            System.out.printf("%s's current start date is: %s\n", school.getSchoolID(), school.getStartAccess().format(formatter));
        else
            System.out.printf("%s's current start date is: Not set.\n");
        if (school.getEndAccess() != null)
            System.out.printf("%s's current end date is: %s\n\n", school.getSchoolID(), school.getEndAccess().format(formatter));
        else
            System.out.printf("%s's current start date is: Not set.\n");
        System.out.print("Please enter new start date (yyyy-mm-dd HH:mm) ");
        startDate = s1.nextLine();
        sd = convertDate(startDate);
        if(sd == null)
            return "Error. Please try again in correct format \"yyyy-MM-DD HH:mm\" without the quotes. ";
        System.out.print("Please enter new end date (yyyy-mm-dd HH:mm) ");
        endDate = s1.nextLine();
        ed = convertDate(endDate);
        if(ed == null)
            return "Error. Please try again in correct format \"yyyy-MM-DD HH:mm\" without the quotes. ";
        System.out.println(sd);
        System.out.println(ed);
        school.setStartAccess(sd);
        school.setEndAccess(ed);
        userController.editSchoolList();
        String returnString = String.format("%s's new start date is: %s\n", school.getSchoolID(), school.getStartAccess().format(formatter));
        returnString = returnString + String.format("%s's new end date is: %s\nAccess period successfully updated.\n\n", school.getSchoolID(), school.getEndAccess().format(formatter));
        return returnString;
    }

    public String addStudent(){
        Scanner s1 = new Scanner(System.in);
        School sch;
        String schoolID;
        Console console = System.console();
        ArrayList<User> userList = userController.getUserList();
        System.out.print("Please enter school ID: ");
        schoolID = s1.nextLine();
        sch = userController.findSchool(schoolID);
        if(sch == null){
            return "School ID is invalid. Please try again. ";
        }
        System.out.print("Enter student name: ");
        String name = s1.nextLine();
        System.out.print("Enter Username: ");
        String username = s1.nextLine();
        char[] inputPw = console.readPassword("Enter Password: ");
        String password = new String(inputPw);
        password = userController.encrypt(password);
        System.out.print("Enter Matric No: ");
        String matricNo = s1.nextLine();
        System.out.print("Enter Gender (M/F): ");
        char gender = s1.next().charAt(0);
        s1.nextLine();
        System.out.print("Enter Nationality: ");
        String nationality = s1.nextLine();
        if(!checkInput(matricNo, username))
            return "Username or matric no already exist. Please try again.";
        User u = new Student(name, username, password, false, matricNo, gender, nationality, schoolID);
        userList.add(u);
        userController.editUserList();
        String returnStr = String.format("Student \"%s\" has been successfully added.", u.getName());
        return returnStr;
    }

    public boolean checkInput(String matricNo, String username){
        User u;
        Student s;
        try{
            for(int i = 0; i < userController.getUserList().size(); i++){
                u = userController.getUserList().get(i);
                if(u.getUsername().equals(username))
                    return false;
                if(!u.getType()){
                    s = (Student)userController.getUserList().get(i);
                    if(s.getMatricNumber().equals(matricNo))
                        return false;
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;

    }

    public String numDay(int num){
        String day = "";

        switch(num){
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            default:
            break;
        }
        return day;
    }

    public String oddEven(int num){
        String value = "";

        switch(num){
            case 1:
                value = "ODD";
                break;
            case 2:
                value = "EVEN";
                break;
            case 3:
                value = "BOTH";
                break;
            default:
            break;
        }
        return value;
    }

    public Boolean isConflict(int day1, int day2, int day3, LocalTime t1start, LocalTime t2start, LocalTime t3start){
        LocalTime t1stop = t1start.plusHours(2);
        LocalTime t2stop = t2start.plusHours(2);
        LocalTime t3stop = t3start.plusHours(1);

        if(day1 == day2){
            if((t1start.isBefore(t2stop)) && (t1stop.isAfter(t2start))){
                System.out.println("Invalid input! There is conflict between lecture and lab schedules!");
                return true;
            }
        }

        if(day1 == day3){
            if((t1start.isBefore(t3stop)) && (t1stop.isAfter(t3start))){
                System.out.println("Invalid input! There is conflict between lab and tutorial schedules!");
                return true;
            }
        }

        if(day2 == day3){
            if((t2start.isBefore(t3stop)) && (t2stop.isAfter(t3start))){
                System.out.println("Invalid input! There is conflict between lecture and tutorial schedules!");
                return true;
            }
        }
        return false;
    }
}