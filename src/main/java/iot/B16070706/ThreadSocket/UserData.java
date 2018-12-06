package iot.B16070706.ThreadSocket;

public class UserData {
	private  String phoneNum;
	private  int state = 1;//state  1£ºÍ£»ú×´Ì¬      2£º¼¤»î×´Ì¬	
	
	
	public UserData() {
	}
	public UserData(String number ,int state) {
		this.phoneNum = number;
		this.state = state;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;

	}
	public String getNumAndState() {
		String And = "phoneNumber: "+ this.phoneNum + " state: " + this.state;
		return And;
	}
	
}
