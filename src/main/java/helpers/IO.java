package helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IO {

    /**
     * @param address image address
     * @return Buffered Type of image file
     */
    public static Image readImg(String address) {
        File file = new File(address);
        if (!file.exists()) throw new IllegalArgumentException("No Image Found");

        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("Problem loading image from File to BufferedImage");
            e.printStackTrace();
        }

        return new Image(img);
    }

    public static Image createImg(int width , int height) {
        BufferedImage bufferedImage = new BufferedImage(width , height , BufferedImage.TYPE_INT_ARGB);
        return new Image(bufferedImage);
    }

    /**
     * @param image image that is going to write in a file
     * @param format format of image e.g. jpg - png - ..
     * @param address address of where image is going to saved
     */
    public static void writeImg(Image image , String format, String address) {
        try {
            ImageIO.write(image.getBufferedImage() , format , new File(address));
        } catch (IOException e) {
            System.err.printf("There was a problem in save Image with %s format at Address \'%s\'",format,address);
            e.printStackTrace();
        }
    }
}
