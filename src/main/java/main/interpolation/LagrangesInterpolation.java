package main.interpolation;

import main.Point;

public class LagrangesInterpolation implements IInterpolation {

    // To represent a data point
// corresponding to x and y = f(x)


    // function to interpolate the given
// data points using Lagrange's formula
// xi corresponds to the new data point
// whose value is to be obtained n
// represents the number of known data points
    static double interpolate(Point f[], double xi, int n) {
        double result = 0; // Initialize result

        for (int i = 0; i < n; i++) {
            // Compute individual terms of above formula
            double term = f[i].getY();
            for (int j = 0; j < n; j++) {
                if (j != i)
                    term = term * (xi - f[j].getX()) / (f[i].getX() - f[j].getX());
            }

            // Add current term to result
            result += term;
        }

        return result;
    }

    @Override
    public double interpolation(Point[] pointArr, double x) {
        return interpolate(pointArr, x, pointArr.length);
    }

    @Override
    public String toString() {
        return "lagrange";
    }
}
