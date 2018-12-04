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
	//用户号码与状态
	private static ArrayList<UserData> userDatas = new ArrayList<UserData>();
	//操作日志
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
	/**默认构造器，主要是为了调方法而设*/
	public ServerWrite() {
	}
 
	@Override
	public void run() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String str = "";
			// 循环向客户端写数据
			while (true) {
				str = br.readLine();
				logger.info(str);
				str = str.replaceAll(" ", "");//当用户输入有空格时，去掉里面的空格
				bw.write(judgeAsk(str));
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {
 
		}
	}
	
	/**根据客户端发送的请求作出回复*/
	public String judgeAsk(String Ask) {
			String reply = null;
			String phoneNum = null;
			String state = null;
			ServerWrite serverWrite = new ServerWrite();
			if(Ask.equals("ask")) {
				reply = "请输入号码：（11位）  +  操作类型：1.停机   2.复话 ";
			}else if(Ask.length()==12 && isNum(Ask)) {
				//Ask的前11位为号码，后一位为要求状态
				phoneNum = Ask.substring(0, 10);
				state = Ask.substring(11);
				
				if(state.equals("1")) {
					reply = serverWrite.setNumState(phoneNum, 1);
				}else if(state.equals("2")){
					reply = serverWrite.setNumState(phoneNum,2);
				}
			}else if(Ask.equals("exit")){
				reply = "与服务器断开连接";
			}
			else {
				reply = "未知请求，重新输入" + Ask.length() ;
			}
			return reply;
	}
		
	/**接口方法实现：停机复话操作
		 * 参数：用户号码
		 * i：选择操作类型
		 * */
		public String setNumState(String phoneNum,int i) {
			String reply;
			if( i == 1 ) {
				userDatas.add(new UserData(phoneNum,"1"));//选择1.则将其状态改为停机
				reply = "0（成功）";
			}else if(i == 2){
				userDatas.add(new UserData(phoneNum,"2"));//选择2.则将其状态改为复话
				reply = "0（成功）";
			}else {
				reply = "1（失败）";
			}	
			return reply;
	}
			
	/**参数：从客户端读入的字符串
		 * 功能：判断字符串是否为数字
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

	



	

