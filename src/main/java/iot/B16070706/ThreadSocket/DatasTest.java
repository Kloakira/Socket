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
	
	/**�ӿڷ���ʵ�֣�ͣ����������
	 * �������û�����
	 * i��ѡ���������
	 * */
	public String setNumState(String phoneNum,int i) {
		String reply = null;
		if( i == 1 ) {
			userDatas.add(new UserData(phoneNum,"1"));//ѡ��1.����״̬��Ϊͣ��
			String s = userDatas.get(0).getPhoneNum();
			reply = s;
		}else if(i == 2){
			userDatas.add(new UserData(phoneNum,"2"));//ѡ��2.����״̬��Ϊ����
			reply = "0���ɹ���";
		}else {
			reply = "1��ʧ�ܣ�";
		}	
		return reply;
}
}
