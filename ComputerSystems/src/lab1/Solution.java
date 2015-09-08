package lab1;

import java.util.Random;

/**
 * Created by Vitaliy on 08.09.2015.
 */
public class Solution {

    private int count;
    private int [][]matrix;
    private double Q;
    private double N;
    private  double Qh;
    private Vertex [] vertex=new Vertex[10];

    public Solution(int count, int[][] matrix, double q, double n, double qh) {
        this.count = count;
        this.matrix = matrix;
        Q = q;
        N = n;
        Qh = qh;
    }

    public void solve(){
        double S=0;
        int[] array=new int[vertex.length];
        for (int i = 0; i <vertex.length ; i++) {
            vertex[i]=new Vertex(i+1,0);
        }
        for (int i = 0; i <count ; i++) {
            array[0]++;
            array[1]++;
            Random r=new Random();
            while (!(r.nextInt(10)==9)){
                Random r3=new Random();
                int j=r3.nextInt(9)-5;
                if(j>=0){
                    array[2]++;
                }else {
                    array[3]++;
                }
                array[4]++;
            }
                array[5]++;
            Random r2=new Random();
            int x=r2.nextInt(10)-4;
            if(x>0){
                array[6]++;
                array[8]++;
            }else {
                array[7]++;
            }
            array[9]++;
        }
        for (int i = 0; i <vertex.length ; i++) {
            vertex[i].setCount((double)array[i]/count);
            S+=vertex[i].getCount();
        }
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                if(i==0&&matrix[i][j]!=0){
                    N+=vertex[j].getCount();
                }else if(i==1){
                    Q+=vertex[j].getCount()*matrix[i][j];
                }else if(matrix[i][j]!=0){

                    Qh+=vertex[j].getCount()*matrix[i][j];
                }
            }
        }

    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public double getQ() {
        return Q;
    }

    public void setQ(double q) {
        Q = q;
    }

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public double getQh() {
        return Qh;
    }

    public void setQh(double qh) {
        Qh = qh;
    }

    public Vertex[] getVertex() {
        return vertex;
    }

    public void setVertex(Vertex[] vertex) {
        this.vertex = vertex;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "Середнє число операцій при 1 прогоні: " + Q +
                ",Середнє число звернень до файлу:  " + N +
                ", Cередня кількість інформації яка передається: " + Qh/N +
                ", Середня трудоємкість етапу обрахунку: "+Q/N+
                '}';
    }
}
