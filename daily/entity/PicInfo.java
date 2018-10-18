package entity;

import java.util.Map;

public class PicInfo {
    private Integer width;

    private Integer height;

    private Map<Location, RGB> info;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Map<Location, RGB> getInfo() {
        return info;
    }

    public void setInfo(Map<Location, RGB> info) {
        this.info = info;
    }
}
