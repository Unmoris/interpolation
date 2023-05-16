package main.choose;

public class ChooseCenterM implements  IChooseM{
    @Override
    public double choose(double[] xArr) {
        int mid = (xArr.length)/2;
        return xArr[mid];
    }
}
