package main.funs;

public class CubeXFunction implements IFuncX{
    @Override
    public double func(double x) {
        return Math.pow(x,2);
    }

    @Override
    public String toString() {
        return "x^2";
    }
}
