package main.interpolation;

import main.Point;

public class NiotonInterpolation implements IInterpolation {
    // calculating u mentioned in the formula
    static double u_cal(double u, int n) {
        double temp = u;
        for (int i = 1; i < n; i++)
            temp = temp * (u - i);
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

    public static double calc(int n, double[] xArr, double[][] y, double value) {


        // Calculating the forward difference
        // table
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++)
                y[j][i] = y[j + 1][i - 1] - y[j][i - 1];
        }


        // initializing u and sum
        double sum = y[0][0];
        double u = (value - xArr[0]) / (xArr[1] - xArr[0]);

        for (int i = 1; i < n; i++) {
            double newSum = sum + (u_cal(u, i) * y[0][i]) /
                    fact(i);
            if (Double.isInfinite(newSum)) {
                System.out.println("----------------------");

                System.out.println(sum);
                System.out.println(u_cal(u, i) * y[0][i]);
                System.out.println(fact(i));
            }
            sum = newSum;
        }

        return sum;
    }

    @Override
    public double interpolation(Point[] pointArr, double x) {
        int n = pointArr.length;
        double[] xArr = new double[n];
        double[][] yArr = new double[n * n][n * n];

        double[] yArr2 = new double[n];

        for (int i = 0; i < n; i++) {
            xArr[i] = pointArr[i].getX();
            yArr[i][0] = pointArr[i].getY();
            yArr2[i] = pointArr[i].getY();
        }

        return Newton_inter_method( xArr, yArr2, x);
    }

    @Override
    public String toString() {
        return "nioton";
    }

    private static void copy_vector(double from[], double to[]) {
        int k = from.length;
        int k2 = to.length;
        if (k != k2) {
            System.out.println("the two vector's length is not equal!");
            System.exit(0);
        }
        for (int i = 0; i < k; i++) {
            to[i] = from[i];
        }
    }

    private static double Newton_inter_method(double[] X, double[] Y, double X0) {
        int m = X.length;
        double Y0 = 0d;
        double[] cp_Y = new double[m];
        double t = 0;
        int j = 0;
        copy_vector(Y, cp_Y);
        int kk = j;

        while (kk < m - 1) {
            kk = kk + 1;
            for (int i2 = kk; i2 < m; i2++) {
                cp_Y[i2] = (cp_Y[i2] - cp_Y[kk - 1]) / (X[i2] - X[kk - 1]);
            }
        }

        double temp = cp_Y[0];
        for (int i = 1; i <= m - 1; i++) {
            double u = 1;
            int jj = 0;
            while (jj < i) {
                u *= (X0 - X[jj]);
                jj++;
            }
            temp += cp_Y[i] * u;
        }
        Y0 = temp;

        return Y0;
    }
}

