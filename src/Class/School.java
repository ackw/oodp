package Class;
import java.util.*;
import java.io.Serializable;
import java.time.*;

/**
 Represents each School.
 Each school contains its own start/end access period which can be modified to limit access for students.
 They also contain a unique school ID, as well as it's name.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public class School implements Serializable {
    private String schoolID;
    private String name;
    private LocalDateTime startAccess;
    private LocalDateTime endAccess;

    public School()
    {
    }

    public School(String SchoolID, String name, LocalDateTime startAccess, LocalDateTime endAccess) {
        this.schoolID = SchoolID;
        this.name = name;
        this.startAccess = startAccess;
        this.endAccess = endAccess;
    }

    
    /** 
     * @return String
     */
    public String getSchoolID() {
        return schoolID;
    }

    
    /** 
     * @param school
     */
    public void setSchoolID(String school) {
        this.schoolID = school;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @return LocalDateTime
     */
    public LocalDateTime getStartAccess() {
        return startAccess;
    }

    
    /** 
     * @param startAccess
     */
    public void setStartAccess(LocalDateTime startAccess) {
        this.startAccess = startAccess;
    }

    
    /** 
     * @return LocalDateTime
     */
    public LocalDateTime getEndAccess() {
        return endAccess;
    }

    
    /** 
     * @param endAccess
     */
    public void setEndAccess(LocalDateTime endAccess) {
        this.endAccess = endAccess;
    }

}

