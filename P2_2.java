import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class P2_2 extends MIDlet implements CommandListener{
	private Display display;
	private List list;
	private Command select,exit;

	public P2_2(){
		super();
	}
	public void startApp(){

		//2_2 is displaying the menu commandlisteners

		display=Display.getDisplay(this);
		list=new List("MENULIST",List.EXCLUSIVE);
		list.append("cut",null);
		list.append("copy",null);
		list.append("paste",null);
		list.append("delete",null);
		list.append("select_all",null);
		list.append("unselect_all",null);

		select= new Command("SELECT",Command.OK,1);
		exit=new Command("EXIT",Command.EXIT,1);
		list.addCommand(select);
		list.addCommand(exit);
		list.setCommandListener(this);

		display.setCurrent(list);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}
	public void commandAction(Command c, Displayable d){
		if(c==exit){
			destroyApp(false);
		}
		if(c==select){
			String temp="You selected "+list.getString(list.getSelectedIndex());
			if(list.getSelectedIndex()==4){
				temp="You selected CUT, COPY, PASTE, DELETE";
			}
			if(list.getSelectedIndex()==5){
				temp="You selected NONE";
			}
			Alert alert = new Alert("SELECTED OPTION",temp,null,null);
			display.setCurrent(alert);
		}
	}
}
