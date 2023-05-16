package main;

import main.choose.IChooseM;
import main.funs.IFuncX;
import main.generatex.IGenerateArrayX;
import main.interpolation.IInterpolation;

public class Calculate {
    private IFuncX funcX;

    private IInterpolation interpolation;

    private double m;
    private double[] arrX;

    private double fX;
    private Point[] points;

    private long duration;

    public Calculate(IChooseM chooseM, IFuncX funcX, IGenerateArrayX generateArrayX, IInterpolation interpolation) {
        this.funcX = funcX;
        this.arrX = generateArrayX.generate();
        this.m = chooseM.choose(arrX);
        this.interpolation = interpolation;

        points = new Point[arrX.length];

        for (int i = 0; i < points.length; i++) {
            double x = arrX[i];
            points[i] = new Point(x, funcX.func(x));
        }

        fX = funcX.func(m);
    }

    public double calculate() {
        long startTime = System.nanoTime();
        double Um = interpolation.interpolation(points, m);
        long endTime = System.nanoTime();

        duration = (endTime - startTime);

        return Um;
    }

    public IFuncX getFuncX() {
        return funcX;
    }

    public double getfX() {
        return fX;
    }

    public Point[] getPoints() {
        return points;
    }

    public double getM() {
        return m;
    }

    public long getDuration() {
        return duration;
    }

}
