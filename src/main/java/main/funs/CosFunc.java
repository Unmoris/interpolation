package main.funs;

public class CosFunc implements  IFuncX{
    @Override
    public double func(double x) {
        return Math.cos(x);
    }

    @Override
    public String toString() {
        return "cos (x)";
    }
}
