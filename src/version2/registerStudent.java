package version2;

public class registerStudent
{
    private User user;
    private Index index;

    public registerStudent()
    {
    }

    public registerStudent(User u, Index i)
    {
        user = u;
        index = i;
    }

    public User getUser(){return user;}
    public void setUser(User u){user = u;}

    public Index getIndex(){return index;}
    public void setIndex(Index i){index = i;}

    public String toString()
    {
       return "User: " + user + "\nIndex: " + index;
    }
    
}