public class Admin extends User
{
    public Admin()
    {
        super();
    }
    
    public Admin(String n, String u, String p, Boolean t)
    {
        super(n, u, p, t);
    }

    public String toString()
    {
       return super.toString();
    }

    // public editAccess();
    // public addStudent();
    // public addCourse();
    // public modifyCourse();
    // public checkAvailableSlot();
    // public printStudentListIndex();   may shift to main interface...
    // public printStuidentListCourse();

}