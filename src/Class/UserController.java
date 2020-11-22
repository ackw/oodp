package Class;
import java.util.*;
import java.io.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class UserController{
    private final static String userListPath = "./src/data/userList";
    private final static String courseListPath = "./src/data/courseList";
    private final static String registerStudentPath = "./src/data/registerStudentList";
    private final static String waitListPath = "./src/data/waitList";
    private final static String schoolListPath = "./src/data/schoolList";
    
    private static ArrayList<User> userList = new ArrayList<User>();
    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private static ArrayList<RegisterStudent> registerStudentList = new ArrayList<RegisterStudent>();
    private static ArrayList<WaitList> waitList = new ArrayList<WaitList>();
    private static ArrayList<School> schoolList = new ArrayList<School>();
    private static User currentUser;

    public User getCurrentUser(){
        return currentUser;
    }
    public ArrayList<User> getUserList(){
        return userList;
    }
    public ArrayList<Course> getCourseList(){
        return courseList;
    }
    public ArrayList<RegisterStudent> getRegisterStudentList(){
        return registerStudentList;
    }

    public ArrayList<WaitList> getWaitList(){
        return waitList;
    }

    public ArrayList<School> getSchoolList(){
        return schoolList;
    }

    public void loadUserList(){
        try {
            FileInputStream fis = new FileInputStream(userListPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void editUserList(){
        try {
            FileOutputStream fos = new FileOutputStream(userListPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(userList);
            oos.close();
            fos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadCourseList(){
        try {
            FileInputStream fis = new FileInputStream(courseListPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            courseList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void editCourseList(){
        try {
            FileOutputStream fos = new FileOutputStream(courseListPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(courseList);
            oos.close();
            fos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadRegisterStudentList(){
        try {
            FileInputStream fis = new FileInputStream(registerStudentPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            registerStudentList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void editRegisterStudentList(){
        try {
            FileOutputStream fos = new FileOutputStream(registerStudentPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(registerStudentList);
            oos.close();
            fos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadWaitList(){
        try {
            FileInputStream fis = new FileInputStream(waitListPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            waitList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void loadSchoolList(){
        try {
            FileInputStream fis = new FileInputStream(schoolListPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            schoolList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
            }
        catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void editSchoolList(){
        try {
            FileOutputStream fos = new FileOutputStream(schoolListPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(schoolList);
            oos.close();
            fos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editWaitList(){
        try {
            FileOutputStream fos = new FileOutputStream(waitListPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);   
            oos.writeObject(waitList);
            oos.close();
            fos.close();
            }
    
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }



    public User login(){
        Scanner s1 = new Scanner(System.in);
        Console console = System.console();
        User u = null;
        Boolean success = false;

        // user login details
        while(!success)
        {
            System.out.print("Enter Username: ");
            String inputUsername = s1.nextLine();

            // run encrypt() to check if its the same as stored
            char[] password = console.readPassword("Enter Password: ");
            String inputPw = new String(password);
            inputPw = encrypt(inputPw);


            for(int i = 0; i < userList.size(); i++)
            {
                u = (User) userList.get(i);

                //System.out.print("to hash " + encrypt(inputPw));

                //if(u.getUsername().equals(inputUsername) && inputPw.equals("688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6"))
                if(u.getUsername().equals(inputUsername) && u.getPassword().equals(inputPw))
                {
                    success = true;
                    break;
                }
            }
            if(!success)
                System.out.println("Invalid login details");
        }
        currentUser = u;
        return u;
    }

    public User login(String inputUsername, String inputPw){
        Scanner s1 = new Scanner(System.in);
        User u = null;
        inputPw = encrypt(inputPw);
        boolean checkLogin = false;

        for(int i = 0; i < userList.size(); i++)
        {
            u = (User) userList.get(i);
            //System.out.print("to hash " + encrypt(inputPw));
            //if(u.getUsername().equals(inputUsername) && inputPw.equals("688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6"))
            if(u.getUsername().equals(inputUsername) && u.getPassword().equals(inputPw)){
                checkLogin = true;
                break;
            }
        }
        if(!checkLogin){
            System.out.println("Invalid login details. Please try again.");
            return null;
        }

        return u;
    }

    public static String encrypt(String plainPw) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(plainPw.getBytes());

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			plainPw = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return plainPw;
	}

    public void showCourseInfo(){
        Index index;
        System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");

        for(int i = 0; i < getCourseList().size(); i++){
            index = (Index)getCourseList().get(i);
            System.out.printf("%-15s %-10s %-10s %-10s\n", index.getCourseCode(), index.getSchool(), index.getIndexNumber(), index.getVacancies());
            
        }
    }

    public void showSchoolInfo(){
        School sch;
        System.out.println("SchoolID School Name");
        System.out.println("======== ============");
        for(int i = 0; i < getSchoolList().size(); i++){
            sch = (School)getSchoolList().get(i);
            System.out.printf("%-8s %s\n", sch.getSchoolID(), sch.getName());
        }
    }

    public Index findIndex(int index){
        Index ind = null;
        for(int i = 0; i < courseList.size(); i++){
            ind = (Index)courseList.get(i);
            if(ind.getIndexNumber() == index){
                return ind;
            }  
        }
        ind = null;
        return ind;
    }

    public School findSchool(String school){
        School sch = null;
        for(int i = 0; i < schoolList.size(); i++){
            sch = (School)schoolList.get(i);
            if(sch.getSchoolID().equals(school.toUpperCase())){
                return sch;
            }  
        }
        sch = null;
        return sch;
    }

    


    
}