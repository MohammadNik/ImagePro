package helpers;

@SuppressWarnings("PointlessBitwiseExpression")
class Utils {

    static int getA(int pixel) {
        return (pixel >> 24);
    }

    static int getR(int pixel) {
        return (pixel >> 16) & 0xFF;
    }

    static int getG(int pixel) {
        return (pixel >> 8) & 0xFF;
    }

    static int getB(int pixel) {
        return (pixel >> 0) & 0xFF;
    }

    static int buildPixel(int a , int r , int g , int b) {
        return (a << 24) | ( r << 16) | (g << 8) | (b << 0);
    }
}
