import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class main {
    public static void main(String[] args) throws Exception {
//        PicInfo picInfo = PicUtil.readPicInfo("E:\\daily-java\\image\\53771575_p0.jpg");
//        System.out.println(JSONObject.toJSONString(picInfo));
        try {
            BufferedReader in =
                    new BufferedReader(
                            new FileReader("E:\\test\\1.txt"));
            BufferedOutputStream out =
                    new BufferedOutputStream(
                            new GZIPOutputStream(
                                    new FileOutputStream("E:\\test\\test.gz")));
            System.out.println("Writing file");
            int c;
            while((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.close();
            System.out.println("Reading file");
            BufferedReader in2 =
                    new BufferedReader(
                            new InputStreamReader(
                                    new GZIPInputStream(
                                            new FileInputStream( "E" ))));
            String s;
            while((s = in2.readLine()) != null)
                System.out.println(s);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}