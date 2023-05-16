package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import main.charecters.Character;
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
import main.print.PrintCSV;

public class Main {
    public static void main(String[] args) {

        List<IInterpolation> interpolationList = Arrays.asList(
                new LagrangesInterpolation(),
                new NiotonInterpolation(),
                new BesselInterpolation(),
                new StirlingInterpolation()
        );

        IFuncX funcX = new CubeXFunction();

        calculateCharacters(getCalculates(funcX, new LagrangesInterpolation()), "lagr");
        calculateCharacters(getCalculates(funcX, new NiotonInterpolation()), "niot");
        calculateCharacters(getCalculates(funcX, new BesselInterpolation()), "bess");
        calculateCharacters(getCalculates(funcX, new StirlingInterpolation()), "stir");


    }

    private static List<Calculate> getCalculates(IFuncX iFuncX, IInterpolation interpol) {
        List<Calculate> calculateList = new LinkedList<>();

        for (int startIndex = -50; startIndex < 0; startIndex++) {
            for (int countIndex = 6; countIndex < 50; countIndex++) {

                IGenerateArrayX generateArrayX = new GenerateToTo(startIndex, Math.abs(startIndex), countIndex);

                for (int i = 0; i < countIndex - 1; i++) {
                    IChooseM chooseM = new RandomInRangeChooseM(i, i + 1);
                    calculateList.add(new Calculate(chooseM, iFuncX, generateArrayX, interpol));
                }
            }
        }
        return calculateList;
    }

    private static void calculateCharacters(List<Calculate> calculateList, String nameFile) {
        List<Character> characterList = new ArrayList<>();

        for (Calculate calculator : calculateList) {
            double val = calculator.calculate();

            Character character = new Character();

            character.interpolationX = calculator.getM();
            character.getInterpolationValue = val;
            character.func = calculator.getFuncX().toString();
            character.time = calculator.getDuration();
            character.fallibility = Math.abs(val - calculator.getfX());
            character.valueFx = calculator.getfX();
            character.relativeError = Math.abs(val - calculator.getfX()) / val / 100;
            characterList.add(character);
        }

        PrintCSV.print(characterList, nameFile);
    }
}
