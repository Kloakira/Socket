package iot.B16070706.ThreadSocket;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			Socket client = new Socket("127.0.0.1", 9988);
			System.out.println("客户端建立完成");
			
			// 1.启动线程读服务器内容
			new ClientRead(client).start();
			// 2.启动线程用于向服务器写内容
			new ClientWrite(client).start();
	
		}
	}
