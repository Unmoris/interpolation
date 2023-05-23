package main.interpolation;

import java.text.*;

public class BClass {
    // calculating u mentioned in the formula
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

    public static double calculete(int n, double[][] y,double[] x){
        // Calculating the central difference table
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n - i; j++)
                y[j][i] = y[j + 1][i - 1] - y[j][i - 1];

        // Displaying the central difference table
        DecimalFormat df = new DecimalFormat("#.########");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++)
                System.out.print(y[i][j] + "\t");
            System.out.println("");
        }

        // value to interpolate at
        double value = 27.4;

        // Initializing u and sum
        double sum = (y[2][0] + y[3][0]) / 2;

        // k is origin thats is f(0)
        int k;
        if ((n % 2) > 0) // origin for odd
            k = n / 2;
        else
            k = n / 2 - 1; // origin for even

        double u = (value - x[k]) / (x[1] - x[0]);

        // Solving using bessel's formula
        for (int i = 1; i < n; i++) {
            if ((i % 2) > 0)
                sum = sum + ((u - 0.5) *
                        ucal(u, i - 1) * y[k][i]) / fact(i);
            else
                sum = sum + (ucal(u, i) *
                        (y[k][i] + y[--k][i]) / (fact(i) * 2));
        }

        return sum;
    }

    public static void main(String[] args) {
        // Number of values given
        int n = 6;
        double x[] = {25, 26, 27, 28, 29, 30};

        // y[][] is used for difference table
        // with y[][0] used for input
        double[][] y = new double[n][n];
        y[0][0] = 4.000;
        y[1][0] = 3.846;
        y[2][0] = 3.704;
        y[3][0] = 3.571;
        y[4][0] = 3.448;
        y[5][0] = 3.333;

    }
}
