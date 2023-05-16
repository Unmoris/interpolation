package main;

import main.choose.ChooseCenterM;
import main.choose.IChooseM;
import main.choose.RandomInRangeChooseM;
import main.funs.CubeXFunction;
import main.funs.IFuncX;
import main.generatex.GenerateToTo;
import main.generatex.IGenerateArrayX;
import main.interpolation.BesselInterpolation;
import main.interpolation.IInterpolation;
import main.interpolation.LagrangesInterpolation;
import main.interpolation.NiotonInterpolation;
import main.interpolation.StirlingInterpolation;
import main.print.PrintConsole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<IInterpolation> interpolationList = Arrays.asList(
                new LagrangesInterpolation(),
                new NiotonInterpolation(),
                new BesselInterpolation(),
                new StirlingInterpolation()
        );

        List<IFuncX> iFuncXList = Arrays.asList(new CubeXFunction());

        int count = 5;
        IGenerateArrayX generateArrayX = new GenerateToTo(-5, 5, count);

        List<Calculate> calculateList = new LinkedList<>();
        for (int i = 0; i < count - 1; i++) {
            IChooseM chooseM = new RandomInRangeChooseM(i, i + 1);

            for (IFuncX func : iFuncXList) {
                for (IInterpolation interpol : interpolationList) {
                    calculateList.add(new Calculate(chooseM,func,generateArrayX,interpol ));
                }
            }
        }

        for (Calculate calculator:calculateList) {
            double val = calculator.calculate();

            PrintConsole.print(calculator, val);

        }

    }
}
