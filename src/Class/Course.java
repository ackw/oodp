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
    /** 
     * courseCode for the course, should be unique.
     */
    protected String courseCode;
    /** 
     * Full name for the course.
     */
    protected String school;
    /** 
     * The weighted academic unit for each course.
     */
    protected int academicUnits;


    public Course()
    {
    }
    /** 
     * Constructor to add course
     * @param c CourseCode (CZ2001 CZ2002 ...)
     * @param s School (SCSE, SPMS etc)
     * @param a Academic Unit (Academic unit weight for each course)
     */
    public Course(String c, String s, int a){
        courseCode = c;
        school = s;
        academicUnits = a;
    }
    public String getCourseCode(){return courseCode;}
    public void setCourseCode(String c){courseCode = c;}

    public String getSchool(){return school;}
    public void setSchool(String s){school = s;}

    public int getAcademicUnits(){return academicUnits;}
    public void setAcademicUnits(int academicUnits) {this.academicUnits = academicUnits;}

    /** 
     * This method returns the variables of related Course.
     * @return String
     */
    public String toString()
    {
       return "Course Name: " + courseCode + "\nSchool: " + school;
    }



}