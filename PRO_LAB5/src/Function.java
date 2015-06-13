/**
 * Created by Vitaliy on 06.04.2015.
 */
public class Function {


    static int N=6;
    static int P=6;
    static int H=N/P;
    static int m=0;
    static int []Z=new int[N];
    static int [][]MO=new int[N][N];
    static int [][]ME=new int[N][N];
    static int [][]MK=new int[N][N];
    static int [][]MA=new int[N][N];
    static int Filler=1;


    public int searchMin(int [] V,int sid,int eid){
         int min=V[0];
        for (int i = 0; i <V.length ; i++) {
            if(V[i]<min){
                min=V[i];
            }
        }
        return min;
    }
    public int searchMax(int [] V,int sid,int eid){
        int max=V[0];
        for (int i = 0; i <V.length ; i++) {
            if(V[i]>max){
                max=V[i];
            }
        }
        return max;
    }
    public int [][] MatrMul(int [][] MM1,int [][] MM2,int c ,int sid,int eid){

        int [][] MM=new int [N][N];

        for (int i = sid; i <eid ; i++) {

            for (int j = 0; j <N ; j++) {
                for (int k = 0; k <N ; k++) {
                    MM[i][j]+=MM1[i][k]*MM2[k][j];
                }
                MM[i][j]*=c;
            }
        }
        return MM;
    }

    public int [][] MatrMulonConst(int [][] MM1,int c,int sid,int eid){
         int [][] MM=new int[N][N];
        for (int i = sid; i <eid ; i++) {
            for (int j = 0; j <N ; j++) {
                MM[i][j]=MM1[i][j]*c;
            }
        }
        return MM;
    }

    public void Func(int [][] ME1,int z4,int z5,int sid,int eid){

        for (int i = sid; i <eid ; i++) {
            for (int j = 0; j <N ; j++) {
                MA[i][j]=MatrMulonConst(MO,z4,sid,eid)[i][j]+MatrMul(MK,ME1,z5,sid,eid)[i][j];
            }
        }

    }

    public static int [][] getFillMatrix(int N){
        int [][]MM=new int[N][N];
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                MM[i][j]=Filler;
            }
        }
        return MM;
    }
    public static int [] getFillVector(int N){
        int [] V=new int[N];
        for (int i = 0; i <N ; i++) {
            V[i]=Filler;
        }
        return V;
    }

    public static void outputMatr(int [][] MM){
        System.out.println("MA=");
        for (int i = 0; i < MM.length; i++) {
            for (int j = 0; j <MM[i].length ; j++) {
                System.out.print(MM[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static int[][] getMO() {
        return MO;
    }

    public static void setMO(int[][] MO) {
        Function.MO = MO;
    }

    public static int[][] getMK() {
        return MK;
    }

    public static void setMK(int[][] MK) {
        Function.MK = MK;
    }

    public static int[][] getME() {
        return ME;
    }
    public static void setME(int[][] ME) {
        Function.ME = ME;
    }

    public static int getH() {
        return H;
    }

    public static void setH(int h) {
        H = h;
    }

    public static int[] getZ() {
        return Z;
    }

    public static void setZ(int[] z) {
        Z = z;
    }
}
