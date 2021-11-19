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
		display=Display.getDisplay(this);
		
		//commands
		next=new Command("NEXT",Command.OK,1);
		back=new Command("BACK",Command.BACK,1);
		submit=new Command("SUBMIT",Command.OK,1);
		exit=new Command("EXIT",Command.EXIT,1);

		l1=new List("Question 1", Choice.IMPLICIT);
		l1.append("Item1", null);
      	l1.append("Item2", null);
      	l1.append("Item3", null);
      	l1.append("Item4", null);
      	l1.addCommand(next);
      	l1.setCommandListener(this);

      	l2=new List("Question 2", Choice.IMPLICIT);
		l2.append("Item1", null);
      	l2.append("Item2", null);
      	l2.append("Item3", null);
      	l2.append("Item4", null);
      	l2.addCommand(next);
      	l2.setCommandListener(this);

      	l3=new List("Question 3", Choice.IMPLICIT);
		l3.append("Item1", null);
      	l3.append("Item2", null);
      	l3.append("Item3", null);
      	l3.append("Item4", null);
      	l3.addCommand(submit);
      	l3.setCommandListener(this);

			/*
		//Question 1
		q1=new Form("TCP Maximum Header Length?");
		c1=new ChoiceGroup("",Choice.IMPLICIT);
		c1.append("10B",null);
		c1.append("20B",null);
		c1.append("40B",null);
		c1.append("60B",null);
		q1.append(c1);
		q1.addCommand(next);
		q1.setCommandListener(this);

		//Question 2
		q2=new Form("C programming language is");
		c2=new ChoiceGroup("",Choice.EXCLUSIVE);
		c2.append("Regular Language",null);
		c2.append("CFL",null);
		c2.append("CSL",null);
		c2.append("Recursive",null);
		q2.append(c2);
		q2.addCommand(back);
		q2.addCommand(next);
		q2.setCommandListener(this);

		//Question 3
		q3=new Form("SMTP Port No ?");
		c3=new ChoiceGroup("",Choice.EXCLUSIVE);
		c3.append("23",null);
		c3.append("80",null);
		c3.append("110",null);
		c3.append("25",null);
		q3.append(c3);
		q3.addCommand(back);
		q3.addCommand(submit);
		q3.setCommandListener(this);

		*/
	}
	public void startApp(){
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
				if(l1.getSelectedIndex()==1) count++;
				display.setCurrent(l2);
			}
			else if(d==l2){
				if(l2.getSelectedIndex()==2) count++;
				display.setCurrent(l3);
			}
		}
		if(c==back){
			if(d==q2) display.setCurrent(q1);
			else if(d==q3) display.setCurrent(q2);
		}
		if(c==submit){
			if(l3.getSelectedIndex()==3) count++;
			displayScore();
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