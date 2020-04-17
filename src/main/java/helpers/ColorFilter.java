package helpers;

import java.util.Random;
import java.util.function.BiFunction;

import static java.lang.Math.min;

public enum ColorFilter {
    GrayScale((pixel , ratio) -> {

        int avg = (pixel.getR() + pixel.getG() + pixel.getB()) / 3;
        avg = (int) (avg * ratio);
        if (ratio > 1) avg = min(avg , 255);

        return Pixel.build(pixel.getA() , avg , avg , avg);
    }),

    Negative((pixel , ratio) -> {

        //calculate newRed, newGreen, newBlue
        int nR = (int) ((255 - pixel.getR()) * ratio);
        if (ratio > 1) nR = min(nR , 255);

        int nG = (int) ((255 - pixel.getG()) * ratio);
        if (ratio > 1) nG = min(nG , 255);

        int nB = (int) ((255 - pixel.getB()) * ratio);
        if (ratio > 1) nB = min(nB , 255);

        return Pixel.build(pixel.getA() , nR , nG , nB);
    }),

    Red_FILTER((pixel , ratio) -> {
        int r = (int) (pixel.getR() * ratio);
        if (ratio > 1) r = min(r , 255);

        return Pixel.build(pixel.getA() , r , 0 , 0);
    }),

    Green_FILTER((pixel , ratio) -> {
        int g = (int) (pixel.getG() * ratio);
        if (ratio > 1) g = min(g , 255);

        return Pixel.build(pixel.getA() , 0 , g , 0);
    }),

    Blue_FILTER((pixel , ratio) -> {
        
        int b = (int) (pixel.getB() * ratio);
        if (ratio > 1) b = min(b , 255);

        return Pixel.build(pixel.getA() , 0 , 0 , b);
    }),

    Sepia((pixel , ratio) -> {

        //calculate newRed, newGreen, newBlue
        int nR = (int) (0.393 * pixel.getR() + 0.769 * pixel.getG() + 0.189 * pixel.getB());
        nR = (int) (min(255 , nR) * ratio);
        if (ratio > 1) nR = min(nR , 255);

        int nG = (int) (0.349 * pixel.getR() + 0.686 * pixel.getG() + 0.168 * pixel.getB());
        nG = (int) (min(255 , nG) * ratio);
        if (ratio > 1) nG = min(nG , 255);

        int nB = (int) (0.272 * pixel.getR() + 0.534 * pixel.getG() + 0.131 * pixel.getB());
        nB = (int) (min(255 , nB) * ratio);
        if (ratio > 1) nB = min(nB , 255);


        return Pixel.build(pixel.getA() , nR , nG , nB);
    }),

    /**
     * function that generate a random pixel
     */
    Random((pixel , ratio) -> {
        java.util.Random rd = new Random();
        int a = rd.nextInt(256);
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);

        r = (int) (r * ratio);
        if (ratio > 1) r = min(r , 255);

        g = (int) (g * ratio);
        if (ratio > 1) g = min(g , 255);

        b = (int) (b * ratio);
        if (ratio > 1) b = min(b , 255);


        return Pixel.build(a , r , g , b);
    });


    private BiFunction<Pixel, Float, Pixel> operator;

    ColorFilter(BiFunction<Pixel, Float, Pixel> operator) {
        this.operator = operator;
    }

    public void apply(Image image , float ratio) {
        
        for (int y = 0; y < image.getHeight(); y++)
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x , y);
                Pixel newPixel = operator.apply(pixel , ratio);
                image.setPixel(newPixel, x , y);
            }
    }
}
