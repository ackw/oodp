package Class;
import java.io.Serializable;
import java.util.*;

/**
 Represents each Student.
 Each student extends user, contains its own unique matric number.
 Other variables includes gender, nationality, its related schoolID, max AU, as well as the current AU.
 By default, a student's max AU is 21 and initial starting AU is 0.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
 @version 1.0
 @since 2020-11-23
*/

public class Student extends User implements Serializable
{
    /** 
     *  matricNumber for the student, should be unique.
     */
    private String matricNumber;
    /** 
     *  M = Male, F = Female
     */
    private char gender;
    /** 
     *  Nationality for student.
     */
    private String nationality;
    /** 
     *  SchoolID should match list of schoolIDs in school list.
     */
    private String schoolID;
    /** 
     *  Maximum academic units each student is allowed to take. By default is 21.
     */
    private int maxAUs;
    /** 
     *  Current academic units that this student has.
     */
    private int currentAUs;

    public Student()
    {
        super();
    }
    /** 
     * Constructor to create a Student.
     * @param n Name -- Passed from User
     * @param u Username -- Passed from User
     * @param p Password -- Passed from User
     * @param t Type -- Passed from User
     * @param m Matriculation Number - Should be unique.
     * @param g Gender (M = Male / F = Female)
     * @param nat Nationality for student 
     * @param s SchoolID - School that the Student is registered under, shold match School list data.
     * Note: maxAUs is set as 21 as default, with currentAUs default as 0.
     */
    public Student(String n, String u, String p, Boolean t, String m, char g, String nat, String s)
    {
        super(n, u, p, t);
        matricNumber = m;
        gender = g;
        nationality = nat;
        schoolID = s;
        maxAUs = 21;
        currentAUs = 0;
    }

    public String getMatricNumber(){return matricNumber;}
    public void setMatricNumber(String m){matricNumber = m;}

    public char getGender(){return gender;}
    public void setGender(char g){gender = g;}

    public String getNationality(){return nationality;}
    public void setNationality(String nat){nationality = nat;}
    
    public String getSchoolID() {return schoolID;}
    public int getMaxAUs(){return maxAUs;}
    
    public void setMaxAUs(int maxAUs){this.maxAUs = maxAUs;}
    public int getCurrentAUs(){return currentAUs;}
    
    public void setCurrentAUs(int c1){this.currentAUs = c1;}
    
    /** 
     * This method returns the variables of related Student.
     * @return String
     */
    public String toString()
    {
       return super.toString() + "\nMatric Number: " + matricNumber + "\nGender: " + gender + "\nNationality: " + nationality;
    }
}