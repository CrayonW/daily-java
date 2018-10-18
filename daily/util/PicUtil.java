package util;

import entity.Location;
import entity.PicInfo;
import entity.RGB;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PicUtil {

    public static PicInfo readPicInfo(String filePath) {
        PicInfo picInfo = new PicInfo();
        //todo 文件格式校验
        File file = new File(filePath);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        Map<Location, RGB> map = new HashMap<>();
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                int rgb0 = (pixel & 0xff0000) >> 16;
                int rgb1 = (pixel & 0xff00) >> 8;
                int rgb2 = (pixel & 0xff);
                Location location = new Location(i, j);
                RGB rgb = new RGB(rgb0, rgb1, rgb2);
                map.put(location, rgb);
                //todo 修改像素值
//                bi.setRGB(123, 123, 123);
            }
        }
        picInfo.setWidth(width);
        picInfo.setHeight(height);
        picInfo.setInfo(map);
        return picInfo;
    }
}
