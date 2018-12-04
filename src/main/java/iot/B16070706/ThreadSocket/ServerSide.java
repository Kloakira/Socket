package iot.B16070706.ThreadSocket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import iot.B16070706.ThreadSocket.UserData;
 
class ServerWrite extends Thread implements Service{
	private Socket socket;
	//�û�������״̬
	private static ArrayList<UserData> userDatas = new ArrayList<UserData>();
	//������־
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ServerWrite.class);
	
	public static ArrayList<UserData> getUserDatas() {
		return userDatas;
	}

	public static void setUserDatas(ArrayList<UserData> userDatas) {
		ServerWrite.userDatas = userDatas;
	}

	public ServerWrite(Socket socket) {
		this.socket = socket;
	}
	/**Ĭ�Ϲ���������Ҫ��Ϊ�˵���������*/
	public ServerWrite() {
	}
 
	@Override
	public void run() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String str = "";
			// ѭ����ͻ���д����
			while (true) {
				str = br.readLine();
				logger.info(str);
				str = str.replaceAll(" ", "");//���û������пո�ʱ��ȥ������Ŀո�
				bw.write(judgeAsk(str));
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
 
		}
	}
	
	/**���ݿͻ��˷��͵����������ظ�*/
	public String judgeAsk(String Ask) {
			String reply = null;
			String phoneNum = null;
			String state = null;
			ServerWrite serverWrite = new ServerWrite();
			if(Ask.equals("ask")) {
				reply = "��������룺��11λ��  +  �������ͣ�1.ͣ��   2.���� ";
			}else if(Ask.length()==12 && isNum(Ask)) {
				//Ask��ǰ11λΪ���룬��һλΪҪ��״̬
				phoneNum = Ask.substring(0, 10);
				state = Ask.substring(11);
				
				if(state.equals("1")) {
					reply = serverWrite.setNumState(phoneNum, 1);
				}else if(state.equals("2")){
					reply = serverWrite.setNumState(phoneNum,2);
				}
			}else if(Ask.equals("exit")){
				reply = "��������Ͽ�����";
			}
			else {
				reply = "δ֪������������" + Ask.length() ;
			}
			return reply;
	}
		
	/**�ӿڷ���ʵ�֣�ͣ����������
		 * �������û�����
		 * i��ѡ���������
		 * */
		public String setNumState(String phoneNum,int i) {
			String reply;
			if( i == 1 ) {
				userDatas.add(new UserData(phoneNum,"1"));//ѡ��1.����״̬��Ϊͣ��
				reply = "0���ɹ���";
			}else if(i == 2){
				userDatas.add(new UserData(phoneNum,"2"));//ѡ��2.����״̬��Ϊ����
				reply = "0���ɹ���";
			}else {
				reply = "1��ʧ�ܣ�";
			}	
			return reply;
	}
			
	/**�������ӿͻ��˶�����ַ���
		 * ���ܣ��ж��ַ����Ƿ�Ϊ����
		 **/
	public static boolean isNum(String str){
			  for (int i = 0 ; i < str.length() ; i++){   
			   if (!Character.isDigit(str.charAt(i))){
			    return false;
			   	}
			  }
			  return true;
	}
		
}

	



	

