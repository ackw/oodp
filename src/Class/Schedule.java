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
    
    public String labDay() {return labDay;}
    public void setlabDay(String labDay){this.labDay = labDay;}
    public String labType() {return labType;}
    public void setlabType(String labType){this.labType = labType;}
    public String lecDay(){return lecDay;}
    public void setlecDay(String lecDay){this.lecDay = lecDay;}
    public String tutDay(){return tutDay;}
    public void settutDay(String tutDay){this.tutDay = tutDay;}
    public LocalTime startLab(){return startLab;}
    public void setstartLab(LocalTime startLab){this.startLab = startLab;}
    public LocalTime endLab(){return endLab;}
    public void setendLab(LocalTime endLab){this.endLab = endLab;}
    public LocalTime startLec(){return startLec;}
    public void setstartLec(LocalTime startLec){this.startLec = startLec;}
    public LocalTime endLec(){return endLec;}
    public void setendLec(LocalTime endLec){this.endLec = endLec;}
    public LocalTime startTut(){return startTut;}
    public void setstartTut(LocalTime startTut){this.startTut = startTut;}
    public LocalTime endTut(){return endTut;}
    public void setendTut(LocalTime endTut){this.endTut = endTut;}

    public String toString()
    {
       return "\nIndex: " + indexNumber 
       + "\nLab Schedule: " + startLab + " - " + endLab + " on " + labDay +  " (" + labType + ")"
       + "\nLecture Schedule: " + startLec + " - " + endLec + " on " + lecDay
       + "\nTutorial Schedule: " + startTut + " - " + endTut + " on " + tutDay;
    }

    public boolean checkConflict(Schedule s){

        if(isConflict(s.labDay, s.startLab, s.endLab , startLab, endLab)){
			System.out.println("Error! Lab clash with existing course(s) lab.");
			return true;
		}

		if(isConflict(s.labDay, s.startLab, s.endLab, startTut, endTut)){
			System.out.println("Error! Lab clash with existing course(s) tutorial.");
			return true;
		}

		if(isConflict(s.labDay, s.startLab, s.endLab, startLec, endLec)){
			System.out.println("Error! Lab clash with existing course(s) lecture.");
			return true;
		}

		if(isConflict(s.lecDay, s.startLec, s.endLec, startLab, endLab)){
			System.out.println("Error! Lecture clash with existing course(s) lab.");
			return true;
        }
        
		if(isConflict(s.lecDay, s.startLec, s.endLec, startTut, endTut)){
			System.out.println("Error! Lecture clash with existing course(s) tutorial.");
			return true;
        }
        
		if(isConflict(s.lecDay, s.startLec, s.endLec, startLec, endLec)){
			System.out.println("Error! Lecture clash with existing course(s) lecture.");
			return true;
        }
        
		if(isConflict(s.tutDay, s.startTut, s.endTut, startLab,endLab)){
			System.out.println("Error! Tutorial clash with existing course(s) lab.");
			return true;
        }
        
		if(isConflict(s.tutDay, s.startTut, s.endTut, startTut, endTut)){
			System.out.println("Error! Tutorial clash with existing course(s) tutorial.");
			return true;
        }
        
		if(isConflict(s.tutDay, s.startTut, s.endTut, startLab, endLab)){
			System.out.println("Error! Tutorial clash with existing course(s) lab.");
			return true;
		}
        return false;
     }
     
     public boolean isConflict(String day, LocalTime t1, LocalTime t2, LocalTime d1, LocalTime d2)
     {
        if(day != labDay && day != lecDay && day != tutDay)
            return false;
        
        if((t1.isBefore(d2)) && (t2.isAfter(d1)))
            return true;

        return false;
     }
}