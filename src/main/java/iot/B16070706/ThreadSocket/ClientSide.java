package iot.B16070706.ThreadSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
 
/**
 * 读取服务器的信息
 * 
 * @author Mr.Gao
 * 
 */
class ClientRead extends Thread {
	private Socket client;
 
	public ClientRead(Socket client) {
		this.client = client;
	}
 
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			String line = "";
			while (true) {
				line = br.readLine();
				if (!"".equals(line) && line != null) {
					System.out.println("服务器：" + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
 
/**
 * 读键盘，写到服务器
 * @author Administrator
 */
class ClientWrite extends Thread {
	private Socket client;
 
	public ClientWrite(Socket client) {
		this.client = client;
	}
 
	@Override
	public void run() {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			String str = "";
			while (true) {
				str = input.readLine();
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
 

