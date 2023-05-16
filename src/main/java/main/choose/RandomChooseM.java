package main.choose;

import java.util.Random;

public class RandomChooseM implements IChooseM {
    @Override
    public double choose(double[] xArr) {
        int max = xArr.length;
        Random rand = new Random();

        int randomNum = rand.nextInt(max + 1);

        double rangeMin = xArr[randomNum];
        double rangeMax = xArr[randomNum + 1];

        Random r = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        return randomValue;
    }
}
