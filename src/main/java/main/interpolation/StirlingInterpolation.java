package main.interpolation;

import main.Point;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

public class StirlingInterpolation implements IInterpolation {

    static double Stirling(double x[], double fx[],
                           double x1, int n) {
        double h, a, u, y1 = 0, N1 = 1, d = 1,
                N2 = 1, d2 = 1, temp1 = 1,
                temp2 = 1, k = 1, l = 1, delta[][];

        delta = new double[n][n];
        int i, j, s;
        h = x[1] - x[0];
        s = (int) floor(n / 2);
        a = x[s];
        u = (x1 - a) / h;

        // Preparing the forward difference
        // table
        for (i = 0; i < n - 1; ++i) {
            delta[i][0] = fx[i + 1] - fx[i];
        }
        for (i = 1; i < n - 1; ++i) {
            for (j = 0; j < n - i - 1; ++j) {
                delta[j][i] = delta[j + 1][i - 1]
                        - delta[j][i - 1];
            }
        }

        // Calculating f(x) using the Stirling
        // formula
        y1 = fx[s];

        for (i = 1; i <= n - 1; ++i) {
            if (i % 2 != 0) {
                if (k != 2) {
                    temp1 *= (pow(u, k) -
                            pow((k - 1), 2));
                } else {
                    temp1 *= (pow(u, 2) -
                            pow((k - 1), 2));
                }
                ++k;
                d *= i;
                s = (int) floor((n - i) / 2);
                try {
                    y1 += (temp1 / (2 * d)) * (delta[s][i - 1] + delta[ s == 0 ? 0 :s - 1][i - 1]);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println();
                }
            } else {
                temp2 *= (pow(u, 2) -
                        pow((l - 1), 2));
                ++l;
                d *= i;
                s = (int) floor((n - i) / 2);
                y1 += (temp2 / (d)) *
                        (delta[s][i - 1]);
            }
        }

        //System.out.print(+y1);
        return y1;
    }

    @Override
    public double interpolation(Point[] pointArr, double x) {
        int n = pointArr.length;
        double[] xArr = new double[n];
        double[] yArr = new double[n];

        for (int i = 0; i < n; i++) {
            xArr[i] = pointArr[i].getX();
            yArr[i] = pointArr[i].getY();

        }
        return Stirling(xArr, yArr,
                x,
                n );
    }
}
