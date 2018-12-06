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
	//用户号码与状态 数组
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
	/**功能：记录用户操作日志并存于硬盘
	 *     循环向客户端写数据*/
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String str = null;

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
	
	/**根据客户端发送的请求作出回复
	 * 请求为：ask         回复：请输入号码：（11位）  +  操作类型：1.停机   2.复话 
	 * 请求为：号码+操作类型         回复：0成功/1失败
	 * 请求为：exit        回复：与服务器断开连接,并断开socket
	 * 其他请求			 回复：未知请求，重新输入*/
	public String judgeAsk(String Ask) {
			String reply = null;
			String phoneNum = null;
			String state = null;
			ServerWrite serverWrite = new ServerWrite();
			
			if(Ask.equals("ask")) {
				reply = "请输入号码：（11位）  +  操作类型：1.停机   2.复话 ";
			}else if(Ask.length()%12==0 && isNum(Ask)) {
				
				for(int j = 0 ; j < Ask.length()/12 ; j++ ) {
					//Ask的前11位（0-11）为号码，后一位（11）为操作类型
					phoneNum = Ask.substring(j*12, j*12+11);
					state = Ask.substring(j*12+11,j*12+12);

					//遍历ArrayList，存在用户提供的phoneNum则直接对其修改
					int count = 0;
					for(int i = 0; !userDatas.isEmpty() && i < userDatas.size();i++) {
						if(userDatas.get(i).getPhoneNum().equals(phoneNum)) {
							count++;
							if(state.equals("1")) {
								userDatas.get(i).setState(1);
								reply = "0（成功）";
							}else if(state.equals("2")){
								userDatas.get(i).setState(2);
								reply = "0（成功）";
							}else {
								reply = "1（失败），未知操作类型";
							}
						}
					}
					//如不存在用户提供的phoneNum，则新建并设置停复机状态
					if(count == 0) {
						if(state.equals("1")) {
							reply = serverWrite.setNumState(phoneNum,1);
						}else if(state.equals("2")){
							reply = serverWrite.setNumState(phoneNum,2);
						}else {
							reply = "1（失败），未知操作类型";
						}
					}
				}
				//用户更改后，服务器打印输出ArrayList当前所有数据
				System.out.println("当前ArrayList内容：");
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
				reply = "未知请求，重新输入"+Ask.length();
			}
			return reply;
	}
		
	/**接口方法实现：停机复话操作
		 * 参数：用户号码
		 * i：选择操作类型
		 * */
	public String setNumState(String phoneNum,int i) {
			String reply = null;
			if( i == 1 ) {
				userDatas.add(new UserData(phoneNum,1));//选择1.则将其状态改为停机
				reply = "0（成功）";
			}else if(i == 2){
				userDatas.add(new UserData(phoneNum,2));//选择2.则将其状态改为复话
				reply = "0（成功）";
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

	



	

