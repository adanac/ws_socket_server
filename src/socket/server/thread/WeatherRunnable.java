package socket.server.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 定义天气查询服务端的Runnable
 * 
 * @author adanac@sina.com
 * @date 2015-10-20下午8:55:54
 * @version v1.0
 */
public class WeatherRunnable implements Runnable {

	private Socket socket;

	public WeatherRunnable(Socket socket) {
		this.socket = socket;
	}

	DataInputStream dataInputStream = null;

	DataOutputStream dataOutputStream = null;

	@Override
	public void run() {
		// 接收客户端请求
		try {

			// 准备接收数据的流
			dataInputStream = new DataInputStream(socket.getInputStream());

			// 准备发送数据的流
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			// 接收数据(城市名称)
			String cityName = dataInputStream.readUTF();
			System.out.println("from client..." + cityName);
			// 处理数据
			// 休眠1秒表示处理数据时间
			Thread.sleep(1000);
			String result = "多云";

			// 回应给客户端
			dataOutputStream.writeUTF(result);
			System.out.println("to client...." + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dataOutputStream != null) {
				try {
					dataOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// socket连接在服务端一般不用主动关闭

		}
		// 响应数据

	}

}
