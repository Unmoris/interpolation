package main.choose;

public class ChooseConcM implements IChooseM{

    double m;

    public ChooseConcM(double m) {
        this.m = m;
    }

    @Override
    public double choose(double[] xArr) {
        return m;
    }
}
