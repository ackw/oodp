import java.io.ObjectOutputStream ;
import java.io.OutputStream ;
import java.io.IOException ;

public class NoHeaderObjectOutputStream extends ObjectOutputStream {
    public NoHeaderObjectOutputStream(OutputStream os) throws IOException {
      super(os);
    }
    @Override 
    protected void writeStreamHeader() throws IOException {  
    // TODO Auto-generated method stub  
    System.out.println("I am called");  
    super.writeStreamHeader();
    }  
  }