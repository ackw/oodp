package Class;

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