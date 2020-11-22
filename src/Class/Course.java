package Class;
import java.util.*;
import java.io.Serializable;

public class Course implements Serializable
{
    protected String courseCode;
    protected String school;
    protected int academicUnits;


    public Course()
    {
    }

    public Course(String c, String s, int a)
    {
        courseCode = c;
        school = s;
        academicUnits = a;
    }

    public String getCourseCode(){return courseCode;}
    public void setCourseCode(String c){courseCode = c;}

    public String getSchool(){return school;}
    public void setSchool(String s){school = s;}

    public int getAcademicUnits(){return academicUnits;}
    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
    }
    
    //public ArrayList<Index> getIndexList(){return indexList;}

    public String toString()
    {
       return "Course Name: " + courseCode + "\nSchool: " + school;
    }
    

    //public addIndex()
    //public dropIndex()



}