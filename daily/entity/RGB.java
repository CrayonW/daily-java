package entity;

public class RGB {
    private Integer red;

    private Integer green;

    private Integer black;

    public RGB() {
    }

    public RGB(Integer red, Integer green, Integer black) {
        this.red = red;
        this.green = green;
        this.black = black;
    }

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlack() {
        return black;
    }

    public void setBlack(Integer black) {
        this.black = black;
    }
}
