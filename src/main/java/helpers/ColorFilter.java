package helpers;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.function.BiFunction;

import static helpers.Utils.*;
import static java.lang.Math.min;

public enum ColorFilter {
    GrayScale((pixel , ratio) -> {
        int a = getA(pixel);
        int r = getR(pixel);
        int g = getG(pixel);
        int b = getB(pixel);

        int avg = (r + g + b) / 3;
        avg = (int) (avg * ratio);
        if (ratio > 1) avg = min(avg , 255);

        return buildPixel(a , avg , avg , avg);
    }),

    Negative((pixel , ratio) -> {
        int a = getA(pixel);
        int r = getR(pixel);
        int g = getG(pixel);
        int b = getB(pixel);

        //calculate newRed, newGreen, newBlue
        int nR = (int) ((255 - r) * ratio);
        if (ratio > 1) nR = min(nR , 255);

        int nG = (int) ((255 - g) * ratio);
        if (ratio > 1) nG = min(nG , 255);

        int nB = (int) ((255 - b) * ratio);
        if (ratio > 1) nB = min(nB , 255);

        return buildPixel(a , nR , nG , nB);
    }),

    Red_FILTER((pixel , ratio) -> {
        int a = getA(pixel);
        int r = getR(pixel);

        r = (int) (r * ratio);
        if (ratio > 1) r = min(r , 255);

        return buildPixel(a , r , 0 , 0);
    }),

    Green_FILTER((pixel , ratio) -> {
        int a = getA(pixel);
        int g = getG(pixel);

        g = (int) (g * ratio);
        if (ratio > 1) g = min(g , 255);

        return buildPixel(a , 0 , g , 0);
    }),

    Blue_FILTER((pixel , ratio) -> {
        int a = getA(pixel);
        int b = getB(pixel);

        b = (int) (b * ratio);
        if (ratio > 1) b = min(b , 255);

        return buildPixel(a , 0 , 0 , b);
    }),

    Sepia((pixel , ratio) -> {
        int a = getA(pixel);
        int r = getR(pixel);
        int g = getG(pixel);
        int b = getB(pixel);

        //calculate newRed, newGreen, newBlue
        int nR = (int) (0.393 * r + 0.769 * g + 0.189 * b);
        nR = (int) (min(255 , nR) * ratio);
        if (ratio > 1) nR = min(nR , 255);

        int nG = (int) (0.349 * r + 0.686 * g + 0.168 * b);
        nG = (int) (min(255 , nG) * ratio);
        if (ratio > 1) nG = min(nG , 255);

        int nB = (int) (0.272 * r + 0.534 * g + 0.131 * b);
        nB = (int) (min(255 , nB) * ratio);
        if (ratio > 1) nB = min(nB , 255);


        return buildPixel(a , nR , nG , nB);
    }),

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


        return buildPixel(a , r , g , b);
    });


    private BiFunction<Integer, Float, Integer> operator;

    ColorFilter(BiFunction<Integer, Float, Integer> operator) {
        this.operator = operator;
    }

    public void apply(BufferedImage image , float ratio) {
        for (int y = 0; y < image.getHeight(); y++)
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x , y);
                int newPixel = operator.apply(pixel , ratio);
                image.setRGB(x , y , newPixel);
            }
    }
}
