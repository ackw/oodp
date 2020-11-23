package Class;

import java.io.Serializable;
import java.time.LocalTime;

/**
 Schedule of lessons for each available indexes.
 Lessons included in the schedule are LAB, LECTURE, and TUTORIAL.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
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
    public String getLabDay() {return labDay;}

    /** 
     * @param toString(
     */
    public void setLabDay(String labDay){this.labDay = labDay;}
    
    /** 
     * @param toString(
     * @return String
     */
    public String getLabType() {return labType;}

    /** 
     * @param toString(
     */
    public void setLabType(String labType){this.labType = labType;}

    /** 
     * @param toString(
     * @return String
     */
    public String getLecDay(){return lecDay;}

    /** 
     * @param toString(
     */
    public void setLecDay(String lecDay){this.lecDay = lecDay;}

    /** 
     * @param toString(
     * @return String
     */
    public String getTutDay(){return tutDay;}

    /** 
     * @param toString(
     */
    public void setTutDay(String tutDay){this.tutDay = tutDay;}

    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime getStartLab(){return startLab;}

    /** 
     * @param toString(
     */
    public void setStartLab(LocalTime startLab){this.startLab = startLab;}

    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime getEndLab(){return endLab;}

    /** 
     * @param toString(
     */
    public void setEndLab(LocalTime endLab){this.endLab = endLab;}

    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime getStartLec(){return startLec;}

    /** 
     * @param toString(
     */
    public void setStartLec(LocalTime startLec){this.startLec = startLec;}

    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime getEndLec(){return endLec;}

    /** 
     * @param toString(
     */
    public void setEndLec(LocalTime endLec){this.endLec = endLec;}

    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime getStartTut(){return startTut;}

    /** 
     * @param toString(
     */
    public void setStartTut(LocalTime startTut){this.startTut = startTut;}

    /** 
     * @param toString(
     * @return LocalTime
     */
    public LocalTime getEndTut(){return endTut;}

    /** 
     * @param toString(
     */
    public void setEndTut(LocalTime endTut){this.endTut = endTut;}

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

    public boolean checkConflict(Schedule s){

        if(isConflictLab(s.labDay, s.labType, s.startLab, s.endLab, startLab, endLab)){
			System.out.println("Error! " + s.getIndexNumber()  + " LAB" + "(" + s.labType + ")" + " clash with " + indexNumber  + " LAB" + "(" + labType + ").");
			return true;
        }
        
        if(isConflict(s.labDay, lecDay, s.startLab, s.endLab, startLec, endLec)){
            System.out.println("Error! " + s.getIndexNumber() + " LAB clash with " + indexNumber + " LECTURE.");
			return true;
        }
        
        if(isConflict(s.labDay, tutDay, s.startLab, s.endLab, startTut, endTut)){
            System.out.println("Error! " + s.getIndexNumber() + " LAB clash with " + indexNumber + " TUTORIAL.");
			return true;
		}

		if(isConflict(s.lecDay, labDay, s.startLec, s.endLec, startLab, endLab)){
            System.out.println("Error! " + s.getIndexNumber() + " LECTURE clash with " + indexNumber + " LAB.");
			return true;
        }

        if(isConflict(s.lecDay, lecDay, s.startLec, s.endLec, startLec, endLec)){
            System.out.println("Error! " + s.getIndexNumber() + " LECTURE clash with " + indexNumber + " LECTURE.");
			return true;
        }
        
        if(isConflict(s.lecDay, tutDay, s.startLec, s.endLec, startTut, endTut)){
            System.out.println("Error! " + s.getIndexNumber() + " LECTURE clash with " + indexNumber + " TUTORIAL.");
			return true;
        }
        
		if(isConflict(s.tutDay, labDay, s.startTut, s.endTut, startLab, endLab)){
            System.out.println("Error! " + s.getIndexNumber() + " TUTORIAL clash with " + indexNumber + " LAB.");
			return true;
        }

        if(isConflict(s.tutDay, lecDay, s.startTut, s.endTut, startLec, endLec)){
            System.out.println("Error! " + s.getIndexNumber() + " TUTORIAL clash with " + indexNumber + " LECTURE.");
			return true;
        }

        if(isConflict(s.tutDay, tutDay, s.startTut, s.endTut, startTut, endTut)){
            System.out.println("Error! " + s.getIndexNumber() + " TUTORIAL clash with " + indexNumber + " TUTORIAL.");
			return true;
        }
        return false;
     }
     
     public boolean isConflict(String currentDay, String newDay, LocalTime currentStartTime, LocalTime currentEndTime, LocalTime newStartTime, LocalTime newEndTime)
     {  
        if(!currentDay.equals(newDay)){
            return false;
        }

        //System.out.print("currentDay is " + currentDay + " newDay is " + newDay);
            
        if((currentStartTime.isBefore(newEndTime)) && (currentEndTime.isAfter(newStartTime)))
            return true;

        return false;
     }

     public boolean isConflictLab(String currentLabDay, String currentLabType, LocalTime currentStartTime, LocalTime currentEndTime, LocalTime newStartTime, LocalTime newEndTime)
     {  
        if(!currentLabDay.equals(labDay)){
            return false;
        }

        if(currentLabType.equals(labType)){
            if((currentStartTime.isBefore(newEndTime)) && (currentEndTime.isAfter(newStartTime)))
                return true;
        }
            
        return false;
     }
}