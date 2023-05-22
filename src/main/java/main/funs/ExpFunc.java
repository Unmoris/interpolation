package main.funs;

public class ExpFunc implements  IFuncX{
    @Override
    public double func(double x) {
        return Math.exp(x);
    }

    @Override
    public String toString() {
        return "e ^ x";
    }
}
