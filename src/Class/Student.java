package Class;
import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable
{
    private String matricNumber;
    private char gender;
    private String nationality;
    private Calendar startDate, endDate;
    private String schoolID;
    private int maxAUs;
    private int currentAUs;

    public Student()
    {
        super();
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
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
     * @param getSchoolID(
     * @return Calendar
     */
    public Calendar getStartDate(){return startDate;} 
    
    /** 
     * @param getSchoolID(
     */
    public void setStartDate(Calendar d){startDate = d;}

    
    /** 
     * @param getSchoolID(
     * @return Calendar
     */
    public Calendar setEndDate(){return endDate;} 
    
    /** 
     * @param getSchoolID(
     */
    public void setEndDate(Calendar d){endDate = d;}

    
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
    
    
    /** 
     * @return String
     */
    public String dateToString()
    {
        return "startDate: " + startDate.get(Calendar.DATE) + "/" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.YEAR) + "\n endDate: " + endDate.get(Calendar.DATE) + "/" + endDate.get(Calendar.MONTH) + "/" + endDate.get(Calendar.YEAR);
    }
}