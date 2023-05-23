package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import main.charecters.Character;
import main.choose.IChooseM;
import main.choose.RandomInRangeChooseM;
import main.funs.CosFunc;
import main.funs.CubeXFunction;
import main.funs.ExpFunc;
import main.funs.IFuncX;
import main.funs.SinFunc;
import main.generatex.GenerateToTo;
import main.generatex.IGenerateArrayX;
import main.interpolation.IInterpolation;
import main.interpolation.LagrangesInterpolation;
import main.interpolation.NiotonInterpolation;
import main.print.PrintCSV;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class Main {
    public static void main(String[] args) {

        List<IInterpolation> interpolationList = Arrays.asList(
//                new LagrangesInterpolation()
                new NiotonInterpolation()
//                new BesselInterpolation(),
//                new StirlingInterpolation()
        );

        List<IFuncX> listFunc = Arrays.asList(
                new CubeXFunction(),
                new CosFunc(),
                new SinFunc(),
                new ExpFunc());


        for (IFuncX func : listFunc) {
            for (IInterpolation interpolation: interpolationList) {
                calculateCharacters(getCalculates(func, interpolation), func.toString()+"_" + interpolation.toString());
            }
        }
    }

    private static List<Calculate> getCalculates(IFuncX iFuncX, IInterpolation interpol) {
        List<Calculate> calculateList = new LinkedList<>();

        for (int startIndex = -30; startIndex < 0; startIndex++) {
            for (int countIndex = 6; countIndex < 30; countIndex++) {

                IGenerateArrayX generateArrayX = new GenerateToTo(startIndex, Math.abs(startIndex), countIndex);

                for (int i = 0; i < countIndex - 1; i++) {
                    IChooseM chooseM = new RandomInRangeChooseM(i, i + 1);
                    calculateList.add(new Calculate(chooseM, iFuncX, generateArrayX, interpol));
                }
            }
        }

        return calculateList;
    }

    private static void corel(List<Character> characters){
        double[] xArr = new double[characters.size()];
        double[] yArr = new double[characters.size()];

        for (int i = 0; i < characters.size(); i++) {
            xArr[i] = characters.get(i).relativeError;
            yArr[i] = characters.get(i).deltaCenter;
        }
        PearsonsCorrelation pearsonsCorrelation = new PearsonsCorrelation();

        System.out.println(pearsonsCorrelation.correlation(xArr, yArr));

    }

    private static void calculateCharacters(List<Calculate> calculateList, String nameFile) {
        List<Character> characterList = new ArrayList<>();

        for (Calculate calculator : calculateList) {
            double val = calculator.calculate();

            Character character = new Character();

            character.interpolationX = calculator.getM();
            character.getInterpolationValue = val;
            character.funcName = calculator.getFuncX().toString();
            character.time = calculator.getDuration();
            character.fallibility = Math.abs(val - calculator.getfX());
            character.valueFx = calculator.getfX();
            character.relativeError = Math.abs(val - calculator.getfX() / val) / 100;
            character.countPoints = calculator.getPoints().length;

            double startPoint = calculator.getPoints()[0].getX();
            double finishPoint = calculator.getPoints()[calculator.getPoints().length - 1].getX();

            character.deltaCenter = Math.abs(calculator.getM() - (startPoint + finishPoint) / 2);
            character.lengthX = Math.abs(finishPoint - startPoint);

            characterList.add(character);
        }
        corel(characterList);
        PrintCSV.print(characterList, nameFile);
    }
}
