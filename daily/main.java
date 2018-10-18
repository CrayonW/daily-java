import com.alibaba.fastjson.JSONObject;
import entity.PicInfo;
import util.PicUtil;

public class main {
    public static void main(String[] args) {
        PicInfo picInfo = PicUtil.readPicInfo("E:\\daily-java\\image\\53771575_p0.jpg");
        System.out.println(JSONObject.toJSONString(picInfo));
    }
}