package Class;
import java.io.Serializable;
import java.util.*;

/**
 Represents each Student.
 Each student extends user, contains its own unique matric number.
 Other variables includes gender, nationality, its related schoolID, max AU, as well as the current AU.
 By default, a student's max AU is 21 and initial starting AU is 0.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class Student extends User implements Serializable
{
    private String matricNumber;
    private char gender;
    private String nationality;
    private String schoolID;
    private int maxAUs;
    private int currentAUs;

    public Student()
    {
        super();
    }
    
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

    
    /** 
     * @param getSchoolID(
     * @return String
     */
    public String getMatricNumber(){return matricNumber;}
    
    /** 
     * @param getSchoolID(
     */
    public void setMatricNumber(String m){matricNumber = m;}

    
    /** 
     * @param getSchoolID(
     * @return char
     */
    public char getGender(){return gender;}
    
    /** 
     * @param getSchoolID(
     */
    public void setGender(char g){gender = g;}

    
    /** 
     * @param getSchoolID(
     * @return String
     */
    public String getNationality(){return nationality;}
    
    /** 
     * @param getSchoolID(
     */
    public void setNationality(String nat){nationality = nat;}
    
    /** 
     * @return String
     */
    public String getSchoolID() {
        return schoolID;
    }

    
    /** 
     * @return int
     */
    public int getMaxAUs(){
        return maxAUs;
    }
    
    /** 
     * @param maxAUs
     */
    public void setMaxAUs(int maxAUs){
        this.maxAUs = maxAUs;
    }
    
    /** 
     * @return int
     */
    public int getCurrentAUs(){
        return currentAUs;
    }
    
    /** 
     * @param c1
     */
    public void setCurrentAUs(int c1){
        this.currentAUs = c1;
    }
    
    /** 
     * @return String
     */
    public String toString()
    {
       return super.toString() + "\nMatric Number: " + matricNumber + "\nGender: " + gender + "\nNationality: " + nationality;
    }
}