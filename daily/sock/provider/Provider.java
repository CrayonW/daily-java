package sock.provider;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务提供者
 */
public class Provider {

    public static void main(String[] args)  {
        Provider provider = new Provider();
        provider.init(12345);
    }

    /**
     * 初始化服务
     *
     * @param port
     */
    public void init(Integer port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                new HandlerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程处理socket数据
     */
    private class HandlerThread implements Runnable {
        private Socket socket;

        /**
         * 初始化启动线程
         *
         * @param client
         */
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                //对应客户端的输出流
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String clientInputStr = input.readUTF();
                // 处理客户端数据
                System.out.println("客户端发过来的内容:" + clientInputStr);

                // 向客户端回复信息
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.print("请输入:\t");
                // 发送键盘输入的一行
                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(s);

                out.close();
                input.close();
            } catch (IOException e) {
                System.out.println("输入流异常");
            } catch (Exception e) {
                System.out.println("服务运行异常:" + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }

}
