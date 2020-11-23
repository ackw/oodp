package Class;

import java.io.Serializable;
import java.time.LocalTime;

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
    
    public String getLabDay() {return labDay;}
    public void setLabDay(String labDay){this.labDay = labDay;}
    public String getLabType() {return labType;}
    public void setLabType(String labType){this.labType = labType;}
    public String getLecDay(){return lecDay;}
    public void setLecDay(String lecDay){this.lecDay = lecDay;}
    public String getTutDay(){return tutDay;}
    public void setTutDay(String tutDay){this.tutDay = tutDay;}
    public LocalTime getStartLab(){return startLab;}
    public void setStartLab(LocalTime startLab){this.startLab = startLab;}
    public LocalTime getEndLab(){return endLab;}
    public void setEndLab(LocalTime endLab){this.endLab = endLab;}
    public LocalTime getStartLec(){return startLec;}
    public void setStartLec(LocalTime startLec){this.startLec = startLec;}
    public LocalTime getEndLec(){return endLec;}
    public void setEndLec(LocalTime endLec){this.endLec = endLec;}
    public LocalTime getStartTut(){return startTut;}
    public void setStartTut(LocalTime startTut){this.startTut = startTut;}
    public LocalTime getEndTut(){return endTut;}
    public void setEndTut(LocalTime endTut){this.endTut = endTut;}

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