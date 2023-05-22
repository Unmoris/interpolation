package main.funs;

public class SinFunc implements  IFuncX{
    @Override
    public double func(double x) {
        return Math.sin(x);
    }

    @Override
    public String toString() {
        return "sin(x)";
    }
}
