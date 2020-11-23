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
    /** 
     *  User that's registered to the course on waitlist.
     */
    private User user;
    /** 
     *  Course that is registered to the user on waitlist.
     */  
    private Course course;

    public WaitList(){}

    /** 
     * Constructor for WaitList.
     * @param u User that's registered to the course on waitlist.
     * @param c Course that is registered to the user on waitlist
     */
    public WaitList(User u, Course c)
    {
        user = u;
        course = c; // registered index
    }

    public User getUser(){return user;}
    public void setUser(User u){user = u;}

    public Course getCourse(){return course;}
    public void setCourse(Course c){course = c;}

    /** 
     * This method returns the variables of related WaitList.
     * @return String
     */
    public String toString()
    {
       return "User: " + user + "\nIndex: " + course;
    }
    
}
