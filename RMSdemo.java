import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

public class RMSdemo extends MIDlet implements CommandListener{
	private Display display;
	private TextField name,score;
	private Form form,res;
	private Command save,top10,exit,del_rec;
	private RecordStore rs=null;
	static final String REC_STORE = "RMSdemo";

	public RMSdemo(){
		super();
	}
	public void startApp(){
		display=Display.getDisplay(this);

		form=new Form("RMS to store player score");
		res=new Form("TOP10");
		name=new TextField("PlayerName : ","",30,TextField.ANY);
		score=new TextField("Score : ","",30,TextField.ANY);
		form.append(name);
		form.append(score);

		save=new Command("SAVE",Command.OK,1);
		top10=new Command("TOP10",Command.OK,2);
		del_rec=new Command("DELETE DATA",Command.OK,3);
		exit=new Command("EXIT",Command.EXIT,1);

		form.addCommand(save);
		form.addCommand(top10);
		form.addCommand(del_rec);
		form.addCommand(exit);
		form.setCommandListener(this);

		try{
			rs = RecordStore.openRecordStore(REC_STORE, true );
		}catch (Exception e){}


		display.setCurrent(form);
	}
	public void pauseApp(){

	}
	public void destroyApp(boolean unconditional){
		notifyDestroyed();
	}

	public void commandAction(Command c,Displayable d){
		if(c==save){
			String temp=name.getString()+";"+score.getString();
			saveRecord(temp);
		}
		else if(c==top10){
			displayTop10();
		}
		else if(c==del_rec){
			deleteRecordStore();
		}
		else if(c==exit){
			destroyApp(false);
		}
	}
	public void saveRecord(String r){
		byte[] record=r.getBytes();
		try{
			rs.addRecord(record,0,record.length);
		}catch(Exception e){

		}
	}
	public void displayTop10(){
		try{
			byte[] recData = new byte[5]; 
			int len;

			for(int i = 1; i <= rs.getNumRecords(); i++){
				if(rs.getRecordSize(i) > recData.length){
					recData = new byte[rs.getRecordSize(i)];
				}
				len = rs.getRecord(i, recData, 0);
				System.out.println("------------------------------");
				System.out.println("Record " + i + " : " +
					new String(recData, 0, len));
				System.out.println("------------------------------");  
			}
		}catch (Exception e){}
	}
	public void deleteRecordStore(){
		try{
			RecordStore.deleteRecordStore(REC_STORE);
		}catch (Exception e){}	
	}
}