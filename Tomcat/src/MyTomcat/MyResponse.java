package MyTomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 将返回的内容按格式写入到输出流中
 */
public class MyResponse {
    private OutputStream outputStream;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        //按照浏览器要求的格式进行输出
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
