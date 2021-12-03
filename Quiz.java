import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Quiz extends MIDlet implements CommandListener{
	public Display display;
	public Form q1,q2,q3,q4,q5,res;
	public ChoiceGroup c1,c2,c3,c4,c5;
	public Command next,back,submit,exit;
	public List l1,l2,l3,l4,l5;
	public int count=0;
	public StringItem si;

	public Quiz(){
		super();
	}
	public void startApp(){
		display=Display.getDisplay(this);
		
		//commands
		next=new Command("NEXT",Command.OK,1);
		back=new Command("BACK",Command.BACK,1);
		submit=new Command("SUBMIT",Command.OK,1);
		exit=new Command("EXIT",Command.EXIT,1);

		l1=new List("TCP Maximum Header Length?", Choice.IMPLICIT);
		l1.append("10B", null);
      	l1.append("20B", null);
      	l1.append("40B", null);
      	l1.append("60B", null); //index 3 (60B) is answer
      	l1.addCommand(next);
      	l1.setCommandListener(this);

      	l2=new List("C programming language is", Choice.IMPLICIT);
		l2.append("Regular Language", null);
      	l2.append("CFL", null);
      	l2.append("CSL", null);
      	l2.append("Recursive", null); //index 2 (CSL) is answer
      	l2.addCommand(next);
      	l2.setCommandListener(this);

      	l3=new List("SMTP Port No ?", Choice.IMPLICIT);
		l3.append("23", null);
      	l3.append("80", null);
      	l3.append("111", null);
      	l3.append("25", null);   //index 3(25) is answer
      	l3.addCommand(submit);
      	l3.setCommandListener(this);

		display.setCurrent(l1);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c,Displayable d){
		if(c==next){
			if(d==l1){
				if(l1.getSelectedIndex()==3) count++; //indexing starts from 0
				display.setCurrent(l2);
			}
			else if(d==l2){
				if(l2.getSelectedIndex()==2) count++;
				display.setCurrent(l3);
			}
		}
		if(c==submit){
			if(l3.getSelectedIndex()==3) count++;
			displayScore();
		}
		if(c==back){
			if(d==l2) display.setCurrent(l1);
			else if(d==l3) display.setCurrent(l2);
		}
		if(c==exit){
			destroyApp(false);
		}
		
	}
	public void displayScore(){
		res = new Form("RESULT");
		si = new StringItem("Your Score is",String.valueOf(count));
		res.append(si);
		res.addCommand(exit);
		res.setCommandListener(this);
		display.setCurrent(res);
	}
}