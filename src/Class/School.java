package Class;
import java.util.*;
import java.io.Serializable;
import java.time.*;

/**
 Represents each School.
 Each school contains its own start/end access period which can be modified to limit access for students.
 They also contain a unique school ID, as well as it's name.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
 @version 1.0
 @since 2020-11-23
*/

public class School implements Serializable {
    /** 
     *  School ID (SCSE / SPMS etc)
     */
    private String schoolID;
    /** 
     *  Full name of the school (School of ...)
     */
    private String name;
    /** 
     *  This indicates the starting dateTime where students from this school can access add drop.
     */
    private LocalDateTime startAccess;
    /** 
     *  This indicates the ending dateTime where students from this school can acesss add drop.
     */
    private LocalDateTime endAccess;

    public School()
    {
    }

    /** 
     * Constructor to create a school.
     * @param schoolID SchoolID (SCSE / SPMS etc)
     * @param name Full name of the school (School of ....)
     * @param startAccess This indicates the starting dateTime where students from this school can access add drop.
     * @param endAccess This indicates the ending dateTime where students from this school can acesss add drop.
     */
    public School(String SchoolID, String name, LocalDateTime startAccess, LocalDateTime endAccess) {
        this.schoolID = SchoolID;
        this.name = name;
        this.startAccess = startAccess;
        this.endAccess = endAccess;
    }

    
    public String getSchoolID() {return schoolID;}
    public void setSchoolID(String school) {this.schoolID = school;}
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public LocalDateTime getStartAccess() {return startAccess;}
    public void setStartAccess(LocalDateTime startAccess) {this.startAccess = startAccess;}

    public LocalDateTime getEndAccess() {return endAccess;}
    public void setEndAccess(LocalDateTime endAccess) {this.endAccess = endAccess;}

}

