import java.util.*;
import java.io.*;


public class UserController{
    private final static String userListPath = "./src/data/userList";
    private final static String courseListPath = "./src/data/courseList";
    private final static String registerStudentPath = "./src/data/registerStudentList";
    
    private static ArrayList<User> userList = new ArrayList<User>();
    private static ArrayList<Course> courseList = new ArrayList<Course>();
    private static ArrayList<RegisterStudent> registerStudentList = new ArrayList<RegisterStudent>();
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
        currentUser = u;
        return u;
    }

    public void showCourseInfo(){
        Index index;
        System.out.printf("\n%-15s %-10s %-10s %-10s\n","Course Code", "School", "Index", "Vacancies");

        for(int i = 0; i < getCourseList().size(); i++){
            index = (Index)getCourseList().get(i);
            System.out.printf("%-15s %-10s %-10s %-10s\n", index.getCourseCode(), index.getSchool(), index.getIndexNumber(), index.getVacancies());
            
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

    


    
}