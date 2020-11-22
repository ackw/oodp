import java.util.*;
import java.io.Serializable;

public class Index extends Course implements Serializable
{
    protected int indexNumber;
    protected int vacancies;

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
       return super.toString() + "\nIndex Number: " + indexNumber + "\nVacancies: " + vacancies;
    }
}