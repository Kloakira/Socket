package iot.B16070706.ThreadSocket;

import java.net.ServerSocket;
import java.net.Socket;
import iot.B16070706.ThreadSocket.ServerWrite;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(9988);
		System.out.println("服务器已建立，等待客户端连接");
		Socket socket = server.accept();
		System.out.println("有客户端连接了");
		// 启动线程，读客户端内容并执行操作
		new ServerWrite(socket).start();
	}

}
