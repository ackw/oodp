import java.util.*;

public class RegisterStudent
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

    public User getUser(){return user;}
    public void setUser(User u){user = u;}

    public Course getCourse(){return course;}
    public void setCourse(Course c){course = c;}

    public String toString()
    {
       return "User: " + user + "\nIndex: " + course;
    }
    
}
