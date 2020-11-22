
import java.util.*;
import java.io.Serializable;
import java.time.*;

public class School implements Serializable {
    private String schoolID;
    private String name;
    private LocalDateTime startAccess;
    private LocalDateTime endAccess;

    public School()
    {
    }

    public School(String SchoolID) {
        this.schoolID = schoolID;
        this.name = name;
        this.startAccess = null;
        this.endAccess = null;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String school) {
        this.schoolID = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartAccess() {
        return startAccess;
    }

    public void setStartAccess(LocalDateTime startAccess) {
        this.startAccess = startAccess;
    }

    public LocalDateTime getEndAccess() {
        return endAccess;
    }

    public void setEndAccess(LocalDateTime endAccess) {
        this.endAccess = endAccess;
    }

}

