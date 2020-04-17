package helpers;

import lombok.Getter;

import java.awt.image.BufferedImage;

public class Image {
    @Getter
    private final BufferedImage bufferedImage;

    public Image(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }


    public Pixel getPixel(int x , int y) {
        int raw = bufferedImage.getRGB(x , y);
        return new Pixel(raw);
    }

    public void setPixel(Pixel pixel , int x , int y) {
        bufferedImage.setRGB(x , y , pixel.getRaw());
    }


}
