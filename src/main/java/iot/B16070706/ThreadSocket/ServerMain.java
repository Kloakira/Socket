package iot.B16070706.ThreadSocket;

import java.net.ServerSocket;
import java.net.Socket;
import iot.B16070706.ThreadSocket.ServerWrite;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(9988);
		System.out.println("�������ѽ������ȴ��ͻ�������");
		Socket socket = server.accept();
		System.out.println("�пͻ���������");
		// �����̣߳����ͻ������ݲ�ִ�в���
		new ServerWrite(socket).start();
	}

}
