// Author: Eshin Griffith

public class NumPairFormatter {
    private int num1;
    private int num2;
    private int procNum;
    NumPairFormatter (int i){
        num1 = (int)(Math.random()*100);
        num2 = (int)(Math.random()*100);
        procNum = i;
    }

    @Override
    public String toString() {
        return procNum + "." + num1 + "," + num2;
    }
}
