package lab1;

/**
 * Created by Vitaliy on 08.09.2015.
 */
public class Vertex {


    private int number;
    private double count;

    public Vertex(int number, double count) {
        this.number = number;
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "number=" + number +
                ", count=" + count +
                '}';
    }
}
