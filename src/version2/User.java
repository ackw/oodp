public abstract class User
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
    
    public String getName(){return name;}
    public void setName(String n){name = n;}

    public String getUsername(){return username;}
    public void setUsername(String u){username = u;}

    public String getPassword(){return password;}
    public void setPassword(String p){password = p;}

    public Boolean getType(){return type;}
    public void setType(Boolean t){type = t;}

    public String toString()
    {
       return "Name: " + name + "\nUsername: " + username + "\nPassword: " + password + "\nType: " + type;
    }
    
}