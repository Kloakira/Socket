package iot.B16070706.ThreadSocket;

public class UserData {
	private  String phoneNum;
	private  String state = "1";//state  1£ºÍ£»ú×´Ì¬      2£º¼¤»î×´Ì¬	
	
	
	public UserData() {
	}
	public UserData(String number ,String state) {
		this.phoneNum = number;
		this.state = state;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;

	}

	
}
