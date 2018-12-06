package iot.B16070706.ThreadSocket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import iot.B16070706.ThreadSocket.UserData;
 
class ServerWrite extends Thread implements Service{
	private Socket socket;
	//�û�������״̬ ����
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
	/**���ܣ���¼�û�������־������Ӳ��
	 *     ѭ����ͻ���д����*/
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String str = null;

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
	
	/**���ݿͻ��˷��͵����������ظ�
	 * ����Ϊ��ask         �ظ�����������룺��11λ��  +  �������ͣ�1.ͣ��   2.���� 
	 * ����Ϊ������+��������         �ظ���0�ɹ�/1ʧ��
	 * ����Ϊ��exit        �ظ�����������Ͽ�����,���Ͽ�socket
	 * ��������			 �ظ���δ֪������������*/
	public String judgeAsk(String Ask) {
			String reply = null;
			String phoneNum = null;
			String state = null;
			ServerWrite serverWrite = new ServerWrite();
			
			if(Ask.equals("ask")) {
				reply = "��������룺��11λ��  +  �������ͣ�1.ͣ��   2.���� ";
			}else if(Ask.length()%12==0 && isNum(Ask)) {
				
				for(int j = 0 ; j < Ask.length()/12 ; j++ ) {
					//Ask��ǰ11λ��0-11��Ϊ���룬��һλ��11��Ϊ��������
					phoneNum = Ask.substring(j*12, j*12+11);
					state = Ask.substring(j*12+11,j*12+12);

					//����ArrayList�������û��ṩ��phoneNum��ֱ�Ӷ����޸�
					int count = 0;
					for(int i = 0; !userDatas.isEmpty() && i < userDatas.size();i++) {
						if(userDatas.get(i).getPhoneNum().equals(phoneNum)) {
							count++;
							if(state.equals("1")) {
								userDatas.get(i).setState(1);
								reply = "0���ɹ���";
							}else if(state.equals("2")){
								userDatas.get(i).setState(2);
								reply = "0���ɹ���";
							}else {
								reply = "1��ʧ�ܣ���δ֪��������";
							}
						}
					}
					//�粻�����û��ṩ��phoneNum�����½�������ͣ����״̬
					if(count == 0) {
						if(state.equals("1")) {
							reply = serverWrite.setNumState(phoneNum,1);
						}else if(state.equals("2")){
							reply = serverWrite.setNumState(phoneNum,2);
						}else {
							reply = "1��ʧ�ܣ���δ֪��������";
						}
					}
				}
				//�û����ĺ󣬷�������ӡ���ArrayList��ǰ��������
				System.out.println("��ǰArrayList���ݣ�");
				for(UserData temp : userDatas){
					System.out.println(temp.getNumAndState());
				}
			}else if(Ask.equals("exit")){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				reply = "δ֪������������"+Ask.length();
			}
			return reply;
	}
		
	/**�ӿڷ���ʵ�֣�ͣ����������
		 * �������û�����
		 * i��ѡ���������
		 * */
	public String setNumState(String phoneNum,int i) {
			String reply = null;
			if( i == 1 ) {
				userDatas.add(new UserData(phoneNum,1));//ѡ��1.����״̬��Ϊͣ��
				reply = "0���ɹ���";
			}else if(i == 2){
				userDatas.add(new UserData(phoneNum,2));//ѡ��2.����״̬��Ϊ����
				reply = "0���ɹ���";
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

	



	

