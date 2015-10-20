package socket.server.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 天气查询服务端
 * 
 * @author adanac@sina.com
 * @date 2015-10-20下午8:55:42
 * @version v1.0
 */
public class WeatherServerThread {

	public static void main(String[] args) throws IOException {
		// 创建socket服务端对象
		ServerSocket serverSocket = new ServerSocket(10086);
		System.out.println("服务端已启动。。。。");
		while (true) {
			// 监听客户端连接，accept方法为阻塞方法
			Socket socket = serverSocket.accept();
			new Thread(new WeatherRunnable(socket)).start();
		}
	}
}
