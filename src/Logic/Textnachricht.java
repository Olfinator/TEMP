package Logic;

import java.util.Date;

public class Textnachricht {

	private String Content;
	private Date Datum;
	private Boolean gesendet = false;
	
	public String getContent(){
		return Content;
	}
	public Date getDatum(){
		return Datum;
	}
	public Boolean getgesendet(){
		return gesendet;
	}
	public void setContent(String text){
		Content = text;
	}
	
	public void setGesendet(){
		gesendet = true;
	}
	
}
