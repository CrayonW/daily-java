package MyTomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * 通过输入流，解析HTTP请求头的方法和URL
 */
public class MyRequest {
    private String url;
    private String method;

    public String getMethod() {
        return method;
    }

    public String getUrl() { return url; }

    public MyRequest(InputStream inputStream) throws IOException {
        StringBuilder httpRequest = new StringBuilder();
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest.append(new String(httpRequestBytes, 0, length));
        }
        String httpHead = httpRequest.toString().split("\n")[0];
        if (httpHead.split("\\s").length >= 2) {
            url = httpHead.split("\\s")[1];
            method = httpHead.split("\\s")[0];
        }
    }
}
