package lab1;

import Jama.Matrix;

import java.util.Random;

/**
 * Created by Vitaliy on 08.09.2015.
 */
public class Lab1 {

    static int count =10000;
    static int[] array=new int[10];
    static int [][]matrix={{1,0,0,0,1,2,0,0,2,3},
                             {0,50,75,100,0,0,125,150,0,0},
                              {100,0,0,0,200,300,0,0,400,500}};

    public static void main(String[] args) {
        Solution solution=new Solution(count,matrix,0,0,0);
        solution.solve();
        Vertex[] vertex=solution.getVertex();
        for (int i = 0; i <vertex.length ; i++) {
            System.out.println(vertex[i].toString());
        }
        System.out.println(solution.toString());


         /*Lab1 lab1=new Lab1();
        lab1.solve(count,array);
        double Q=0;
        double N=0;
        double Qh=0;
        double S=0;
        double [] result=new double[array.length];
        for (int i = 0; i <array.length ; i++) {
            System.out.println("V"+(i+1)+"= "+(double)array[i]/count);
            result[i]=(double)array[i]/count;
            S+=result[i];
        }
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                if(i==0&&matrix[i][j]!=0){
                    N+=result[j];
                }else if(i==1){
                    Q+=result[j]*matrix[i][j];
                }else if(matrix[i][j]!=0){

                    Qh+=result[j]*matrix[i][j];
                }
            }
        }
        System.out.println("Середнє число операцій при 1 прогоні: "+Q);
        System.out.println("Середнє число звернень до файлу:  "+N);
        System.out.println("Cередня кількість інформації яка передається: "+Qh/N);
        System.out.println("Середня трудоємкість етапу обрахунку: "+Q/N);*/
/*
    public  void solve(int count,int []array){
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
    }*/

    }

}
