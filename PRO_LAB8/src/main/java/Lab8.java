/**
 * Created by Vitaliy on 23.05.2015.
 */

import mpi.*;
public class Lab8 {


    static int N=9;
    static int P=9;
    static int H=N/P;
    static int LastH=N-(P-1)*H;
     static Data data=new Data(N,P);
    static int Filler=1;

    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        System.out.println("Task" + (rank + 1) + " started");
        switch (rank) {
            case 0:
                T1(rank);
                break;
            case 1:
                T2(rank);
                break;
            case 2:
                T3(rank);
                break;
            case 3:
                T4(rank);
                break;
            case 4:
                T5(rank);
                break;
            case 5:
                T6(rank);
                break;
            case 6:
                T7(rank);
                break;
            case 7:
                T8(rank);
                break;
            case 8:
                T9(rank);
                break;
        }
        System.out.println("Task" + (rank + 1) + " finished");
        MPI.Finalize();
    }

    public static void T1(int rank){
        Object object=new Object();
        int [][] MB=new int[N][N];
        int [][]MC=new int[N][N];
        int [][]MK=new int[N][N];
        int [][]MA=new int[N][N];
        int alpha=Filler;
        data.FillMatrix(MB,1);
        data.FillMatrix(MC,1);
        data.FillMatrix(MK,1);

        //data for T2
        int[][] MB_2=getMatrixPart(MB,0,H);
        int [][]MK_2=getMatrixPart(MK,0,H);
        object=MB_2;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T2,1);
        object=MK_2;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T2,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T2,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.INT,T2,1);
        //data for T3
        int [][]MB_3=getMatrixPart(MB,H,4*H);
        int [][]MK_3=getMatrixPart(MK,H,4*H);
        object=MB_3;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T3,1);
        object=MK_3;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T3,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T3,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.OBJECT,T3,1);
        //data for T6;
        int [][]MB_6=getMatrixPart(MB,4*H,6*H);
        int [][]MK_6=getMatrixPart(MK,4*H,6*H);
        object=MB_6;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T6,1);
        object=MK_6;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T6,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T6,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.OBJECT,T6,1);
        //data for T8;
        int [][] MB_8=getMatrixPart(MB,6*H,8*H);
        int [][] MK_8=getMatrixPart(MC,6*H,8*H);
        object=MB_8;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T8,1);
        object=MK_8;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T8,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T8,1);
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T8,1);
        //calc
        int [][]MA_H1=new int[N-8*H][N];
        int [][] MB_H=getMatrixPart(MB,8*H,N);
        int [][]MK_H=getMatrixPart(MK,8*H,N);
         calc(MA_H1,alpha,MB_H,MK_H,MC);
        int [][] MA_2=new int[H][N];
        int [][]MA_3=new int[H*3][N];
        int [][]MA_6=new int[H*2][N];
        int [][]MA_8=new int[H*2][N];

        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T2,1);
        MA_2= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MA_3= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T6,1);
        MA_6= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T8,1);
        MA_8= (int[][]) object;

        MA= con( con(con(con(MA_8,MA_6),MA_3) ,MA_2),MA_H1);
        data.MatrixOutput(MA);
    }

    public static void T2(int rank){
        Object object=new Object();

       int [][] MB_2=new int[H][N];
        int [][] MK_2=new int[H][N];
        int [][] MC=new int[N][N];
        int alpha=0;

        //get Data from T1;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MB_2= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MK_2= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T1,1);
        int [][] MA_1=new int[H][N];
        calc(MA_1,alpha,MB_2,MK_2,MC);
        object=MA_1;
        MPI.COMM_WORLD.Send(object, 0, 1, MPI.OBJECT, T1, 1);

    }
    public static void T3(int rank){
         Object object=new Object();
        int [][]MA=new int[3*H][N];
         int [][] MB_3H=new int[3*H][N];
        int [][] MK_3H=new int[3*H][N];
        int [][]MC=new int[N][N];
        int alpha=0;
        //get data from T1
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MB_3H= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MK_3H= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T1,1);
        //send to T4;

        int [][]MB_4_H=getMatrixPart(MB_3H,H,H*2);
        int [][]MK_4_H=getMatrixPart(MK_3H,H,H*2);
        object=MB_4_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T4,1);
        object=MK_4_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T4,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T4,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.INT,T4,1);
        //send to T5;

        int [][]MB_5_H=getMatrixPart(MB_3H,H*2,H*3);
        int [][]MK_5_H=getMatrixPart(MK_3H,H*2,H*3);
        object=MB_5_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T5,1);
        object=MK_5_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T5,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T5,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.INT,T5,1);

        //calc
        int [][]MA_H=new int[H][N];
        int [][]MB_3_H=getMatrixPart(MB_3H,0,H);
        int [][]MK_3_H=getMatrixPart(MK_3H,0,H);
        calc(MA_H,alpha,MB_3_H,MK_3_H,MC);

        //get MA from T4
        int [][]MA_4=new int[H][N];
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T4,1);
        MA_4= (int[][]) object;
        //get MA from T5
        int [][]MA_5=new int[H][N];
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T5,1);
        MA_5= (int[][]) object;

        MA=con(con(MA_5,MA_4),MA_H);
        object=MA;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);

    }

    public static void T4(int rank){
        Object object=new Object();

        int [][] MB_4=new int[H][N];
        int [][] MK_4=new int[H][N];
        int [][] MC=new int[N][N];
        int alpha=0;

        //get Data from T3;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MB_4= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MK_4= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T3,1);
        int [][] MA_1=new int[H][N];
        calc(MA_1,alpha,MB_4,MK_4,MC);
        object=MA_1;
        MPI.COMM_WORLD.Send(object, 0, 1, MPI.OBJECT, T3, 1);


    }
    public static void T5(int rank){
        Object object=new Object();

        int [][] MB_5=new int[H][N];
        int [][] MK_5=new int[H][N];
        int [][] MC=new int[N][N];
        int alpha=0;

        //get Data from T3;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MB_5= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MK_5= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T3,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T3,1);
        int [][] MA_1=new int[H][N];
        calc(MA_1,alpha,MB_5,MK_5,MC);
        object=MA_1;
        MPI.COMM_WORLD.Send(object, 0, 1, MPI.OBJECT, T3, 1);
    }
    public static void T6(int rank){
        Object object=new Object();
        int [][]MB_6=new int[H*2][N];
        int [][]MK_6=new int[H*2][N];
        int [][]MC=new int[N][N];
        int [][]MA=new int[H*2][N];
        int alpha=0;

        //get Data from T1;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MB_6= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MK_6= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T1,1);

        //send Data to T7
        int [][]MB_7_H=getMatrixPart(MB_6,H,H*2);
        int [][]MK_7_H=getMatrixPart(MK_6,H,H*2);
        object=MB_7_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T7,1);
        object=MK_7_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T7,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T7,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.INT,T7,1);
        //calc
        int [][]MB_H=getMatrixPart(MB_6,0,H);
        int [][]MK_H=getMatrixPart(MK_6,0,H);
        int [][]MA_H=new int[H][N];

        calc(MA_H,alpha,MB_H,MK_H,MC);

        //get MA from T7;
        int [][]MA_7=new int[H][N];
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T7,1);
        MA_7= (int[][]) object;
        MA=con(MA_7,MA_H);

        object=MA;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T1,1);

    }

    public static void T7(int rank){
        Object object=new Object();
        int [][]MA=new int[H][N];
        int [][]MB_7=new int[H][N];
        int [][]MK_7=new int[H][N];
        int [][]MC=new int[N][N];
        int alpha=0;

        //get Data from T6;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T6,1);
        MB_7= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T6,1);
        MK_7= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T6,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T6,1);

        calc(MA, alpha, MB_7, MK_7, MC);

        object=MA;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T6,1);

    }
    public static void T8(int rank){
        Object object=new Object();
        int [][]MB_8=new int[H*2][N];
        int [][]MK_8=new int[H*2][N];
        int [][]MC=new int[N][N];
        int [][]MA=new int[H*2][N];
        int alpha=0;

        //get Data from T1;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MB_8= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MK_8= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T1,1);
        MC=(int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T1,1);

        //send Data to T9
        int [][]MB_9_H=getMatrixPart(MB_8,H,H*2);
        int [][]MK_9_H=getMatrixPart(MK_8,H,H*2);
        object=MB_9_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T9,1);
        object=MK_9_H;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T9,1);
        object=MC;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T9,1);
        MPI.COMM_WORLD.Send(alpha,0,1,MPI.INT,T9,1);
        //calc
        int [][]MB_H=getMatrixPart(MB_8,0,H);
        int [][]MK_H=getMatrixPart(MK_8,0,H);
        int [][]MA_H=new int[H][N];

        calc(MA_H,alpha,MB_H,MK_H,MC);

        //get MA from T9;
        int [][]MA_9=new int[H][N];
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T9,1);
        MA_9= (int[][]) object;
        MA=con(MA_9,MA_H);

        object=MA;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T1,1);

    }

    public static void T9(int rank){
        Object object=new Object();
        int [][]MA=new int[H][N];
        int [][]MB_9=new int[H][N];
        int [][]MK_9=new int[H][N];
        int [][]MC=new int[N][N];
        int alpha=0;

        //get Data from T8;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T8,1);
        MB_9= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T8,1);
        MK_9= (int[][]) object;
        MPI.COMM_WORLD.Recv(object,0,1,MPI.OBJECT,T8,1);
        MC= (int[][]) object;
        MPI.COMM_WORLD.Recv(alpha,0,1,MPI.INT,T8,1);

        calc(MA,alpha,MB_9,MK_9,MC);

        object=MA;
        MPI.COMM_WORLD.Send(object,0,1,MPI.OBJECT,T8,1);

    }





    public static void check(int a, int b, int[][] MC, int[][] MB, int[][] MR) {
        System.out.println("a = " + a);
        System.out.println("a = " + b);
        System.out.print("MC = ");
        data.MatrixOutput(MC);
        System.out.println("MB = ");
        data.MatrixOutput(MB);
        System.out.println("MK = ");
        data.MatrixOutput(MR);
    }

    public static int[][] getMatrixPart(int[][] M, int start, int end) {
        int MR[][] = new int[end-start][N];
        for (int i = 0; i < end-start; i++) {
            for (int j = 0; j < N; j++) {
                MR[i][j] = M[i+start][j];
            }
        }
        return MR;
    }

    public static int [][] con(int [][]M1,int [][]M2) {
        int [][] res = new int [M1.length+M2.length][N];
        for (int i = 0; i < res.length; i++) {
            if (i<M1.length) {
                for (int j = 0; j < N; j++) {
                    res[i][j] = M1[i][j];
                }
            } else {
                for (int j = 0; j < N; j++) {
                    res[i][j] = M2[i-M1.length][j];
                }
            }
        }
        return res;
    }
    public static void calc(int [][] MA, int a,  int [][]MB,int [][]MK, int [][]MC){
        int t = 0;
        for (int i = 0; i < MA.length; i++) {
            for (int j = 0; j < MC.length; j++) {
                t = 0;
                for (int k = 0; k < MC.length; k++) {
                    t += MK[i][k]*MC[k][j];
                }
                MA[i][j] = a*MB[i][j] + t;
            }
        }
    }

    public static final int T1 = 0;
    public static final int T2 = 1;
    public static final int T3 = 2;
    public static final int T4 = 3;
    public static final int T5 = 4;
    public static final int T6 = 5;
    public static final int T7 = 6;
    public static final int T8 = 7;
    public static final int T9 = 8;

}
