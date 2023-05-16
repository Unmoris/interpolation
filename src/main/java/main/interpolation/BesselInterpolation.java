package main.interpolation;

import main.Point;

public class BesselInterpolation implements IInterpolation {
    static double ucal(double u, int n) {
        if (n == 0)
            return 1;

        double temp = u;
        for (int i = 1; i <= n / 2; i++)
            temp = temp * (u - i);

        for (int i = 1; i < n / 2; i++)
            temp = temp * (u + i);

        return temp;
    }

    // calculating factorial of given number n
    static long fact(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    @Override
    public double interpolation(Point[] pointArr, double x) {
        // calculating u mentioned in the formula

        // Number of values given
        int n = pointArr.length;
        double arrX[] = new double[n];

        for (int i = 0; i < n; i++) {
            arrX[i] = pointArr[i].getX();
        }

        double[][] y = new double[n][n];

        for (int i = 0; i < n; i++) {
            y[i][0] = pointArr[i].getY();
        }


        // Calculating the central difference table
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n - i; j++)
                y[j][i] = y[j + 1][i - 1] - y[j][i - 1];


        // value to interpolate at
        double value = x;

        int zeroY = 0;

        int firstY = 0;
        for (int i = 0; i < arrX.length; i++) {
            if (x > arrX[i]) {
                zeroY = i;
                firstY = i + 1;
            }
        }
        // Initializing u and sum
        double sum = (y[zeroY][0] + y[firstY][0]) / 2;

        // k is origin thats is f(0)
        int k;
        if ((n % 2) > 0) // origin for odd
            k = n / 2;
        else
            k = n / 2 - 1; // origin for even

        double u = (value - arrX[k]) / (arrX[1] - arrX[0]);

        // Solving using bessel's formula
        for (int i = 1; i < n; i++) {
            double newSum;
            if ((i % 2) > 0)
                newSum = sum + ((u - 0.5) * ucal(u, i - 1) * y[k][i]) / fact(i);
            else {
                newSum = sum + (ucal(u, i) *
                        (y[k][i] + y[--k][i]) / (fact(i) * 2));
            }

//            if (Double.isInfinite(newSum) || Double.isNaN(newSum)){
//                throw new RuntimeException();
//            }

            sum = newSum;

        }
        return sum;
    }
}
