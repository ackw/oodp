package Class;
import java.io.Serializable;
import java.util.*;

/**
 Represents a waitlist.
 A waitlist contains a user and a course.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class WaitList implements Serializable
{
    private User user;
    private Course course;

    public WaitList()
    {
        
    }

    public WaitList(User u, Course c)
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
