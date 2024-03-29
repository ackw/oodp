package Class;
import java.util.*;
import java.io.Serializable;


/**
 Represents an index. 
 Index extends Course and contains index number and vacancies.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
 @version 1.0
 @since 2020-11-23
*/
public class Index extends Course implements Serializable
{
    /** 
     *  indexNumberfor the index, should be unique.
     */
    protected int indexNumber;

    /** 
     *  Vacancies the index, should be 10 as specified in requirements.
     */
    protected int vacancies;

    /** 
     *  Schedule that's related to each index.
     */
    protected Schedule schedule;

    public Index()
    {
        super();
    }
    /** 
     * Constructor to create an index. 
     * @param c CourseCode (CZ2001 / CZ2002 etc)
     * @param s School (SCSE / SPMS etc)
     * @param a AcademicUnits (AUs)
     * @param i IndexNumber (Should be unique)
     * @param v Vacancy (10 according to requirements)
     * @param d Schedule for each index.
     */
    public Index(String c, String s,int a, int i, int v, Schedule d)
    {
        super(c, s, a);
        indexNumber = i;
        vacancies = v;
        schedule = d;
    }

    public int getIndexNumber(){return indexNumber;}
    public void setIndexNumber(int i){indexNumber = i;}
    public int getVacancies(){return vacancies;}
    public void setVacancies(int v){vacancies = v;}
    public Schedule getSchedule(){return schedule;}
    public void setSchedule(Schedule d){schedule = d;}

    /** 
     * This method returns the variables of related Index.
     * @return String
     */
    public String toString()
    {
       return super.toString() + "\nIndex Number: " + indexNumber + "\nVacancies: " + vacancies;
    }


    

}