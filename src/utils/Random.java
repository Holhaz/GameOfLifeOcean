package utils;

public class Random {
    public int nextIntBetween(int low, int high) {
        float t;
        int c;
        t = (float) ((Math.random() * (high - low)) + low);
        c = (int) t;
        return (c);
    }

}