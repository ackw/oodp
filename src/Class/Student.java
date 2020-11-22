package Class;
import java.util.*;
import java.io.Serializable;

public class Student extends User implements Serializable
{
    private String matricNumber;
    private char gender;
    private String nationality;
    private String schoolID;

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
    }

    public String getMatricNumber(){return matricNumber;}
    public void setMatricNumber(String m){matricNumber = m;}

    public char getGender(){return gender;}
    public void setGender(char g){gender = g;}

    public String getNationality(){return nationality;}
    public void setNationality(String nat){nationality = nat;}

    public String getSchoolID() {
        return schoolID;
    }

    public String toString()
    {
       return super.toString() + "\nMatric Number: " + matricNumber + "\nGender: " + gender + "\nNationality: " + nationality;
    }
    
}