package Class;

/**
 Represents an admin user.
 @author Pow Liang Hong / Remus Neo / Nicky Lee / Andrel Chew / Malcolm Pang
 @version 1.0
 @since 2020-11-20
*/

public class Admin extends User
{
    public Admin()
    {
        super();
    }
    
    /** 
     * Constructor for Admin
     * @param n Name
     * @param u Username
     * @param p Password
     * @param t Type -- TRUE = Admin, FALSE = Student
     */
    public Admin(String n, String u, String p, Boolean t)
    {
        super(n, u, p, t);
    }

    
    /** 
     * This method returns the variables of related Admin.
     * @return String
     */
    public String toString()
    {
       return super.toString();
    }

}