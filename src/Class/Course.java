package Class;
import java.util.*;
import java.io.Serializable;

public class Course implements Serializable
{
    protected String courseCode;
    protected String school;

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
    
    //public ArrayList<Index> getIndexList(){return indexList;}

    public String toString()
    {
       return "Course Name: " + courseCode + "\nSchool: " + school;
    }
    

    //public addIndex()
    //public dropIndex()



}