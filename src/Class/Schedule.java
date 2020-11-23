package Class;

import java.io.Serializable;
import java.time.LocalTime;

/**
 <to be filled>
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class Schedule extends Index implements Serializable {
    private String labDay;
    private String labType;
    private String lecDay;
    private String tutDay;
    private LocalTime startLab;
    private LocalTime endLab;
    private LocalTime startLec;
    private LocalTime endLec;
    private LocalTime startTut;
    private LocalTime endTut;

    public Schedule(int indexNumber, String labDay, String labType, String lecDay, String tutDay, LocalTime startLab, LocalTime startLec, LocalTime startTut){
        this.indexNumber = indexNumber;
        this.labDay = labDay;
        this.labType = labType;
        this.lecDay = lecDay;
        this.tutDay = tutDay;

        this.startLab = startLab;
        this.startLec = startLec;
        this.startTut = startTut;

        endLab = startLab.plusHours(2);
        endLec = startLec.plusHours(2);
        endTut = startTut.plusHours(1);
    }

    
    /** 
     * @param toString(
     * @return String
     */
    public String labDay() {return labDay;}
    
    /** 
     * @param toString(
     */
    public void setlabDay(String labDay){this.labDay = labDay;}
    
    /** 
     * @param toString(
     * @return String
     */
    public String labType() {return labType;}
    
    /** 
     * @param toString(
     */
    public void setlabType(String labType){this.labType = labType;}
    
    /** 
     * @param toString(
     * @return String
     */
    public String lecDay(){return lecDay;}
    
    /** 
     * @param toString(
     */
    public void setlecDay(String lecDay){this.lecDay = lecDay;}
    
    /** 
     * @param toString(
     * @return String
     */
    public String tutDay(){return tutDay;}
    
    /** 
     * @param toString(
     */
    public void settutDay(String tutDay){this.tutDay = tutDay;}
    
    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime startLab(){return startLab;}
    
    /** 
     * @param toString(
     */
    public void setstartLab(LocalTime startLab){this.startLab = startLab;}
    
    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime endLab(){return endLab;}
    
    /** 
     * @param toString(
     */
    public void setendLab(LocalTime endLab){this.endLab = endLab;}
    
    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime startLec(){return startLec;}
    
    /** 
     * @param toString(
     */
    public void setstartLec(LocalTime startLec){this.startLec = startLec;}
    
    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime endLec(){return endLec;}
    
    /** 
     * @param toString(
     */
    public void setendLec(LocalTime endLec){this.endLec = endLec;}
    
    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime startTut(){return startTut;}
    
    /** 
     * @param toString(
     */
    public void setstartTut(LocalTime startTut){this.startTut = startTut;}
    
    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime endTut(){return endTut;}
    
    /** 
     * @param toString(
     */
    public void setendTut(LocalTime endTut){this.endTut = endTut;}

    
    /** 
     * @return String
     */
    public String toString()
    {
       return "\nIndex: " + indexNumber 
       + "\nLab Schedule: " + startLab + " - " + endLab + " on " + labDay +  " (" + labType + ")"
       + "\nLecture Schedule: " + startLec + " - " + endLec + " on " + lecDay
       + "\nTutorial Schedule: " + startTut + " - " + endTut + " on " + tutDay;
    }
}