package Class;
import java.io.Serializable;
import java.util.*;

public class RegisterStudent implements Serializable
{
    private User user;
    private Course course;

    public RegisterStudent()
    {
        
    }

    public RegisterStudent(User u, Course c)
    {
        user = u;
        course = c; // registered index
    }

    
    /** 
     * @param toString(
     * @return User
     */
    public User getUser(){return user;}
    
    /** 
     * @param toString(
     */
    public void setUser(User u){user = u;}

    
    /** 
     * @param toString(
     * @return Course
     */
    public Course getCourse(){return course;}
    
    /** 
     * @param toString(
     */
    public void setCourse(Course c){course = c;}

    
    /** 
     * @return String
     */
    public String toString()
    {
       return "User: " + user + "\nIndex: " + course;
    }
    
}
