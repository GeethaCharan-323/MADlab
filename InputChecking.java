import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class InputChecking extends MIDlet implements CommandListener{
	public Display display;
	public Form form;
	public TextField tf;
	public Command check,exit;

	public InputChecking(){
		display=Display.getDisplay(this);
		form = new Form("CHECK PHONE NUMBER");
		
		tf = new TextField("Enter Number:","",30,TextField.ANY);
		form.append(tf);

		check = new Command("CHECK",Command.OK,1);
		exit= new Command("EXIT",Command.EXIT,1);
		form.addCommand(check);
		form.addCommand(exit);
		form.setCommandListener(this);


	}
	public void startApp(){
		display.setCurrent(form);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c,Displayable d){
		if(c==exit){
			destroyApp(false);
		}
		else if (c==check){
			performMatching();
		}
	}
	public void performMatching(){
		String s = tf.getString();
		String areaCode="";
		int k=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)==' '){
				k=i;
				break;	
			} 
			else{
				areaCode+=s.charAt(i);
			}
		}
		boolean areaFlag=false,countFlag=false;
		if(areaCode=="111"||areaCode=="041"||areaCode=="050"||areaCode=="0400"||areaCode=="044"){
			areaFlag=true;
		}
		int count=0;
		for(int i=k+1;i<s.length();i++){
			count+=1;
		}
		if(count>=6 && count<=8){
			countFlag=true;
		}

			//alerts
		String alertMessage="";
		if(areaFlag==false){
			alertMessage="AreaCode Error, ";
		}
		if(countFlag==false){
			alertMessage+="Number Count Error, ";
		}
		if(countFlag&&areaFlag){
			alertMessage="Number is Correct";
		}
		Alert alert = new Alert("ALERT",alertMessage,null,null);
		display.setCurrent(alert);
	}
}