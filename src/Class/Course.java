package Class;
import java.util.*;
import java.io.Serializable;

/**
 Represents a course.
 A course contains course code, school, and academic units.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

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
    
    /** 
     * @param academicUnits
     * @return String
     */
    public String getCourseCode(){return courseCode;}
    
    /** 
     * @param academicUnits
     */
    public void setCourseCode(String c){courseCode = c;}

    
    /** 
     * @param academicUnits
     * @return String
     */
    public String getSchool(){return school;}
    
    /** 
     * @param academicUnits
     */
    public void setSchool(String s){school = s;}

    
    /** 
     * @param academicUnits
     * @return int
     */
    public int getAcademicUnits(){return academicUnits;}
    
    /** 
     * @param academicUnits
     */
    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
    }
    
    
    /** 
     * @return String
     */
    //public ArrayList<Index> getIndexList(){return indexList;}

    public String toString()
    {
       return "Course Name: " + courseCode + "\nSchool: " + school;
    }
    

    //public addIndex()
    //public dropIndex()



}