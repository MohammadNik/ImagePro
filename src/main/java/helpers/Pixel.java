package helpers;

import lombok.Getter;

public class Pixel {
    @Getter
    private int raw;

    public Pixel(int raw) {
        this.raw = raw;
    }

    public int getA() {
        return (raw >> 24);
    }

    public int getR() {
        return (raw >> 16) & 0xFF;
    }

    public int getG() {
        return (raw >> 8) & 0xFF;
    }

    public int getB() {
        return (raw >> 0) & 0xFF;
    }

    public static Pixel build(int a , int r , int g , int b) {
        int rawVal = (a << 24) | (r << 16) | (g << 8) | (b << 0);
        return new Pixel(rawVal);
    }
}
