package version2;
import java.util.*;


public class Course
{
    protected String courseCode;
    protected String school;
    protected ArrayList indexList = new ArrayList();

    public Course()
    {
    }

    public Course(String c, String s)
    {
        courseCode = c;
        school = s;
    }

    public String getCourseCode(){return courseCode;}
    public void setCourseCode(String c){courseCode = c;}

    public String getSchool(){return school;}
    public void setSchool(String s){school = s;}

    public String toString()
    {
       return "Course Code: " + courseCode + "\nUsername: " + school;
    }

    //public addIndex()
    //public dropIndex()



}