package main.print;

import main.Calculate;

public class PrintConsole {

    public static void print(Calculate calculator, double uM){
        System.out.println("\n\n\n");
        System.out.println(calculator.getM());
        System.out.println(uM);
        System.out.println(calculator.getFuncX().func(calculator.getM()));
    }
}
