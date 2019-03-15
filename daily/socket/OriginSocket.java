package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OriginSocket {
    public final Integer portNumber = 8080;

    //单线程阻塞处理socket请求
    public void main (String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);//创建socket服务端
            Socket clientSocket = serverSocket.accept();//accept调用阻塞
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true
            );
            String request, response;
            while ((request = in.readLine()) != null) {
                if ("Done".equals(request)) {
                    break;
                }
            }
            response = processRequest(request);
            out.print(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {

        return null;
    }
}
