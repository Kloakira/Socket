package iot.B16070706.ThreadSocket;

import java.util.ArrayList;

public class DatasTest implements Service {
	private static ArrayList<UserData> userDatas = new ArrayList<UserData>();
	UserData userData = new UserData();
	public static void main(String[] args) {
		String string = "15295529296";
		String string2 = null;
		DatasTest datasTest = new DatasTest();
		string2 = datasTest.setNumState(string,1);
		System.out.println(string2);
	}
	
	/**接口方法实现：停机复话操作
	 * 参数：用户号码
	 * i：选择操作类型
	 * */
	public String setNumState(String phoneNum,int i) {
		String reply = null;
		if( i == 1 ) {
			userDatas.add(new UserData(phoneNum,"1"));//选择1.则将其状态改为停机
			String s = userDatas.get(0).getPhoneNum();
			reply = s;
		}else if(i == 2){
			userDatas.add(new UserData(phoneNum,"2"));//选择2.则将其状态改为复话
			reply = "0（成功）";
		}else {
			reply = "1（失败）";
		}	
		return reply;
}
}
