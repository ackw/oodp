import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable
{
    private int matricNumber;
    private char gender;
    private String nationality;
    private ArrayList registeredCourseList = new ArrayList();
    private Calendar startTime, endTime;

    public Student()
    {
        super();
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
    }
    
    public Student(String n, String u, String p, Boolean t, int m, char g, String nat)
    {
        super(n, u, p, t);
        matricNumber = m;
        gender = g;
        nationality = nat;
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
    }

    public int getMatricNumber(){return matricNumber;}
    public void setMatricNumber(int m){matricNumber = m;}

    public char getGender(){return gender;}
    public void setGender(char g){gender = g;}

    public String getNationality(){return nationality;}
    public void setNationality(String nat){nationality = nat;}

    public Calendar getStartTime(){return startTime;} 
    public void setStartTime(Calendar d){startTime = d;}

    public Calendar setEndTime(){return endTime;} 
    public void setEndTime(Calendar d){endTime = d;}

    

    public String toString()
    {
       return super.toString() + "\nMatric Number: " + matricNumber + "\nGender: " + gender + "\nNationality: " + nationality;
    }
}