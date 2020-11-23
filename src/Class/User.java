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
    /** 
     *  Name of each user.
     */
    protected String name;
    /** 
     *  Username of each user, should be unique. This is used to login.
     */
    protected String username;
    /** 
     *  Password of each user. This is used to login.
     */
    protected String password;
    /** 
     *  Indicates type of user. TRUE = Admin, FALSE = Student.
     */
    protected Boolean type;

    public User()
    {
    }

    /** 
     * Constructor to create a user.
     * @param n Name of each user
     * @param u Username of each user, should be unique. This is used to login.
     * @param p Password of each user. This is used to login.
     * @param t Indicates type of user. TRUE = Admin, FALSE = Student.
     */
    public User(String n, String u, String p, Boolean t)
    {
        name = n;
        username = u;
        password = p;
        type = t;
    }
    
    public String getName(){return name;}
    public void setName(String n){name = n;}

    public String getUsername(){return username;}
    public void setUsername(String u){username = u;}

    public String getPassword(){return password;}
    public void setPassword(String p){password = p;}

    public Boolean getType(){return type;}
    public void setType(Boolean t){type = t;}

    /** 
     * This method returns the variables of related User.
     * @return String
     */
    public String toString()
    {
       return "Name: " + name + "\nUsername: " + username + "\nPassword: " + password + "\nType: " + type;
    }
    
}