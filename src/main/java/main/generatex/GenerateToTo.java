package main.generatex;

public class GenerateToTo implements IGenerateArrayX {

    public double startX;

    public double finishX;

    public int count;

    public double h;

    public GenerateToTo(double startX, double finishX, int count) {
        this.startX = startX;
        this.finishX = finishX;
        this.count = count;
    }

    @Override
    public double[] generate() {
        double[] arr = new double[count];
        h = (finishX - startX) / count;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = startX + h * i;
        }
        return arr;
    }
}
