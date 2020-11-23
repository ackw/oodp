package Class;

/**
 Represents an admin user.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-20
*/

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

    
    /** 
     * @return String
     */
    public String toString()
    {
       return super.toString();
    }

}