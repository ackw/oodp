package Class;
import java.util.*;
import java.io.Serializable;

public class Index extends Course implements Serializable
{
    protected int indexNumber;
    private int vacancies;
    protected Schedule schedule;

    public Index()
    {
        super();
    }

    public Index(String c, String s,int a, int i, int v, Schedule d)
    {
        super(c, s, a);
        indexNumber = i;
        vacancies = v;
        schedule = d;
    }

    public Schedule getSchedule(){return schedule;}
    public void setSchedule(Schedule d){schedule = d;}
	public int getIndexNumber(){return indexNumber;}
    public void setIndexNumber(int i){indexNumber = i;}
    public int getVacancies(){return vacancies;}
    public void setVacancies(int v){vacancies = v;}

    public String toString()
    {
       return super.toString() + "\nIndex Number: " + indexNumber + "\nVacancies: " + vacancies;
    }


    

}