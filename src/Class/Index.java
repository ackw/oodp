package Class;
import java.util.*;
import java.io.Serializable;


/**
 Represents an index. 
 Index extends Course and contains index number and vacancies.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

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
	
    /** 
     * @param toString(
     * @return int
     */
    public int getIndexNumber(){return indexNumber;}
    
    /** 
     * @param toString(
     */
    public void setIndexNumber(int i){indexNumber = i;}
    
    /** 
     * @param toString(
     * @return int
     */
    public int getVacancies(){return vacancies;}
    
    /** 
     * @param toString(
     */
    public void setVacancies(int v){vacancies = v;}

    
    /** 
     * @return String
     */
    public String toString()
    {
       return super.toString() + "\nIndex Number: " + indexNumber + "\nVacancies: " + vacancies;
    }


    

}