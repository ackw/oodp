package Class;
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

    public Index(String c, String s,int a, int i, int v)
    {
        super(c, s, a);
        indexNumber = i;
        vacancies = v;
    }

	
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