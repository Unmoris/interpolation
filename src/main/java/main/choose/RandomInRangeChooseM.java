package main.choose;

import java.util.Random;

public class RandomInRangeChooseM implements IChooseM {

    public int rangeMin;
    public int rangeMax;

    public RandomInRangeChooseM(int rangeMax, int rangeMin) {
        this.rangeMax = rangeMax;
        this.rangeMin = rangeMin;
    }

    @Override
    public double choose(double[] xArr) {
        double min = xArr[rangeMin];
        double max = xArr[rangeMax];

        Random r = new Random();

        return min + (max - min) * r.nextDouble();
    }
}
