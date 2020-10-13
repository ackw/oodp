
package version2;
import java.util.*;

public class Student extends User
{
    private int matricNumber;
    private char gender;
    private String nationality;
    private ArrayList registeredIndexList = new ArrayList();

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

    //to be implemented
    // public void addRegisteredIndex(Index index) { registeredIndexList.add(index); }
	// public ArrayList getRegisteredIndexList() { return registeredIndexList; }


}