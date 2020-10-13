package version2;
import java.util.*;

public class Index extends Course
{
    private int indexNumber;
    private int vacancies;
    private ArrayList registeredStudents = new ArrayList();

    public Index()
    {
        super();
    }

    public Index(String c, String s, int i, int v)
    {
        super(c, s);
        indexNumber = i;
        vacancies = v;
    }

    public int getIndexNumber(){return indexNumber;}
    public void setIndexNumber(int i){indexNumber = i;}
    public int getVacancies(){return vacancies;}
    public void setVacancies(int v){vacancies = v;}

    public String toString()
    {
       return super.toString() + "\n Index Number: " + indexNumber + "\nVacancies: " + vacancies;
    }


    

}