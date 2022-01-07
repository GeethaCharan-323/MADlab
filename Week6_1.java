import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import java.io.*;

public class Week6_1 extends MIDlet implements CommandListener{
  public StreamConnection sc=null;
  public InputStream is=null;
  public DataInputStream dis=null;
  public OutputStream os=null;
  public DataOutputStream dos=null;

  public String url="socket://www.google.com:443";
  public StringBuffer results;

  public Display display;
  public Form form;
  public StringItem item;


	public Week6_1(){
		super();
	}
	public void startApp(){
		display=Display.getDisplay(this);
    results= new StringBuffer();
    form=new Form("SOCKET");

    try{
      sc= (StreamConnection) Connector.open(url);
      os= sc.openOutputStream();
      dos= new DataOutputStream(os);
      dos.writeChars("GET /index.htm HTTP/1.0 \n");
      dos.flush();

      is=sc.openInputStream();
      dis= new DataInputStream(is);

      int inputChar;
      while ( (inputChar = dis.read()) != -1) {
        results.append((char) inputChar);
      }

      item = new StringItem(null, results.toString());
      form.append(item);
      display.setCurrent(form);
    }
    catch(IOException e){
      System.err.println("Exception caught:" + e);
    }
    finally {
      try {
        if (dis != null)  dis.close();
      } catch(Exception e){}
      try {
         if (dos != null)  dos.close();
      } catch(Exception e){}
      try {
         if (os != null)   os.close();
      } catch(Exception e){}
      try {
         if (is != null)   is.close();
      } catch(Exception e){}
      try {
          if (sc != null)   sc.close();
      } catch(Exception e){}
    }
  }
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c,Displayable d){

	}
}
