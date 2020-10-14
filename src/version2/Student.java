import java.util.*;

public class Student extends User
{
    private int matricNumber;
    private char gender;
    private String nationality;
    private ArrayList registeredCourseList = new ArrayList();

    public Student()
    {
        super();
    }
    
    public Student(String n, String u, String p, Boolean t, int m, char g, String nat)
    {
        super(n, u, p, t);
        matricNumber = m;
        gender = g;
        nationality = nat;
    }

    public int getMatricNumber(){return matricNumber;}
    public void setMatricNumber(int m){matricNumber = m;}

    public char getGender(){return gender;}
    public void setGender(char g){gender = g;}

    public String getNationality(){return nationality;}
    public void setNationality(String nat){nationality = nat;}

    public String toString()
    {
       return super.toString() + "\nMatric Number: " + matricNumber + "\nGender: " + gender + "\nNationality: " + nationality;
    }

    public void addRegisteredCourse(RegisterStudent rs) { registeredCourseList.add(rs);}
    public void dropRegisteredCourse(RegisterStudent rs) {
        System.out.println(registeredCourseList.get(0));
        registeredCourseList.remove(rs);
    }
        
	public ArrayList getRegisteredCourse() { return registeredCourseList; }


}