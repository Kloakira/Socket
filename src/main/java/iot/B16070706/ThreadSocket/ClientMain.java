package iot.B16070706.ThreadSocket;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			Socket client = new Socket("127.0.0.1", 9988);
			System.out.println("�ͻ��˽������");
			
			// 1.�����̶߳�����������
			new ClientRead(client).start();
			// 2.�����߳������������д����
			new ClientWrite(client).start();
	
		}
	}
