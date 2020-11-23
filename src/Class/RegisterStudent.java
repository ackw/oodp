package Class;
import java.io.Serializable;
import java.util.*;

/**
 Represents the data when a user registers for a course.. 
 Contains the relevant course and the user.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class RegisterStudent implements Serializable
{
    private User user;
    private Course course;

    public RegisterStudent(){}
    /** 
     * Constructor for RegisterStudent.
     * @param u User that's registered to the course.
     * @param c Course that is registered to the user.
     */
    public RegisterStudent(User u, Course c){
        user = u;
        course = c; 
    }

    
    public User getUser(){return user;}
    public void setUser(User u){user = u;}

    public Course getCourse(){return course;}
    public void setCourse(Course c){course = c;}

    
    /** 
     * This method returns the variables of related RegisterStudent.
     * @return String
     */
    public String toString()
    {
       return "User: " + user + "\nIndex: " + course;
    }
    
}
