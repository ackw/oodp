package Class;
import java.io.Serializable;


/**
 Represents a user.
 Contains its unique username, its name, and password. 
 Type represents whether its admin or not,  TRUE being admin, FALSE being not an admin.
 @author Pow Liang Hong / Remus / Nicky / Andrel / Malcolm 
 @version 1.0
 @since 2020-11-23
*/

public abstract class User implements Serializable
{
    protected String name;
    protected String username;
    protected String password;
    protected Boolean type;

    public User()
    {
    }

    public User(String n, String u, String p, Boolean t)
    {
        name = n;
        username = u;
        password = p;
        type = t;
    }
    
    
    /** 
     * @param toString(
     * @return String
     */
    public String getName(){return name;}
    
    /** 
     * @param toString(
     */
    public void setName(String n){name = n;}

    
    /** 
     * @param toString(
     * @return String
     */
    public String getUsername(){return username;}
    
    /** 
     * @param toString(
     */
    public void setUsername(String u){username = u;}

    
    /** 
     * @param toString(
     * @return String
     */
    public String getPassword(){return password;}
    
    /** 
     * @param toString(
     */
    public void setPassword(String p){password = p;}

    
    /** 
     * @param toString(
     * @return Boolean
     */
    public Boolean getType(){return type;}
    
    /** 
     * @param toString(
     */
    public void setType(Boolean t){type = t;}

    
    /** 
     * @return String
     */
    public String toString()
    {
       return "Name: " + name + "\nUsername: " + username + "\nPassword: " + password + "\nType: " + type;
    }
    
}