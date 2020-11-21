import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable
{
    private int matricNumber;
    private char gender;
    private String nationality;
    private ArrayList registeredCourseList = new ArrayList();
    private Calendar startDate, endDate;
    private String schoolID;

    public Student()
    {
        super();
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
    }
    
    public Student(String n, String u, String p, Boolean t, int m, char g, String nat, String s)
    {
        super(n, u, p, t);
        matricNumber = m;
        gender = g;
        nationality = nat;
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        schoolID = s;
    }

    public int getMatricNumber(){return matricNumber;}
    public void setMatricNumber(int m){matricNumber = m;}

    public char getGender(){return gender;}
    public void setGender(char g){gender = g;}

    public String getNationality(){return nationality;}
    public void setNationality(String nat){nationality = nat;}

    public Calendar getStartDate(){return startDate;} 
    public void setStartDate(Calendar d){startDate = d;}

    public Calendar setEndDate(){return endDate;} 
    public void setEndDate(Calendar d){endDate = d;}

    public String getSchoolID() {
        return schoolID;
    }

    public String toString()
    {
       return super.toString() + "\nMatric Number: " + matricNumber + "\nGender: " + gender + "\nNationality: " + nationality;
    }
    
    public String dateToString()
    {
        return "startDate: " + startDate.get(Calendar.DATE) + "/" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.YEAR) + "\n endDate: " + endDate.get(Calendar.DATE) + "/" + endDate.get(Calendar.MONTH) + "/" + endDate.get(Calendar.YEAR);
    }
}