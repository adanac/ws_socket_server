package socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 天气查询服务（socket实现）
 * 
 * @author adanac@sina.com
 * @date 2015-10-20下午8:56:27
 * @version v1.0
 */
public class WeatherServer {

	public static void main(String[] args) throws IOException {
		// 启动一个socket服务
		// 自定义端口号建议1万以上
		ServerSocket serverSocket = new ServerSocket(10086);
		System.out.println("启动socket服务。。。。。");

		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		while (true) {
			try {
				// 监听客户端连接
				// accept()方法是一个阻塞方法
				Socket socket = serverSocket.accept();

				// 准备接收数据的流
				dataInputStream = new DataInputStream(socket.getInputStream());

				// 准备发送数据的流
				dataOutputStream = new DataOutputStream(socket.getOutputStream());

				// 接收数据(城市名称)
				String cityName = dataInputStream.readUTF();
				System.out.println("from client .." + cityName);

				// 处理数据
				// 休闲1秒表示处理数据时间
				Thread.sleep(1000);
				String result = "多云";

				// 回应给客户端
				dataOutputStream.writeUTF(result);
				System.out.println("to client ...." + result);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放资源
				if (dataInputStream != null) {
					dataInputStream.close();
				}
				if (dataOutputStream != null) {
					dataOutputStream.close();
				}
				// socket连接在服务端一般不用主动关闭
			}

		}
	}
}
