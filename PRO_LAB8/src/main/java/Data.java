/**
 * Created by Vitaliy on 23.05.2015.
 */
public class Data {
    protected int N;
    protected int H;
    protected int P;

    public Data(int N, int P) {
        this.N = N;
        this.P = P;
        this.H = N / P;
    }

    // ��������� �������
    public void VectorOutput(int[] V) {
        for (int i = 0; i <V.length; i++) {
            System.out.print(V[i] + " ");
        }
        System.out.println();
    }

    // ����������������
    public void MatrixOutput(int[][] M) {
        for (int i = 0; i <M.length; i++) {
            int j = 0;
            for (j = 0; j < M[i].length - 1; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println(M[i][j]);
        }
    }

    // ������������������������
    public int[][] MultiplicationMatrixOnMatrix(int[][] MA, int[][] MB) {
        int[][] MR = new int[N][MB[0].length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < MB[0].length; j++) {
                MR[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    MR[i][j] += (MA[i][k] * MB[k][j]);
                }
            }
        }
        return MR;
    }

    // �������� ������� �� �������
    public int[] MultiplicationVectorOnMatrix(int[] V, int[][] MA) {
        int[] R = new int[MA[0].length];
        for (int i = 0; i < MA[0].length; i++) {
            R[i] = 0;
            for (int k = 0; k < N; k++) {
                R[i] += (V[k] * MA[k][i]);
            }
        }
        return R;
    }

    // �������� ������� �� ������
    public int[][] MultiplicationScalarOnMatrix(int s, int[][] M) {
        int[][] R = new int[N][M[0].length];
        for(int i =0;i<N;i++)
        {
            for (int j = 0; j <M[0].length; j++) {
                R[i][j] = M[i][j] * s;
            }
        }
        return R;
    }


    // �������� ������� �� ������
    public int[] MultiplicationScalarOnVector(int s, int[] M) {
        int[] R = new int[M.length];
        for(int i =0;i<M.length;i++)
        {
            R[i] = M[i] * s;
        }
        return R;
    }

    // ����������������
    public void AddVector(int[] A, int[] B, int[] R) {
        for (int i = 0; i <B.length; i++) {
            R[i] = A[i] + B[i];
        }
    }

    // �������� �������
    public void MultVector(int[] A, int[] B, int[] R) {
        for (int i = 0; i <A.length; i++) {
            for (int j = 0; j <B.length; j++) {
                R[j] += A[i] * B[j];
            }
        }
    }

    // г����� �������
    public int[][] RizMatrix(int[][] M1,int[][] M2) {
        int[][] ret = new int[N][M2[0].length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M2[0].length; j++) {
                ret[i][j] = M1[i][j]-M2[i][j];
            }
        }
        return ret;
    }

    // Sum �������
    public int[][] AddMatrix(int[][] M1,int[][] M2) {
        int[][] ret = new int[N][M2[0].length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M2[0].length; j++) {
                ret[i][j] = M1[i][j]+M2[i][j];
            }
        }
        return ret;
    }

    // ���������������� ����� ������
    public void FillMatrix(int[][] M, int n) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = n;
            }
        }
    }

    // ��������� ������ ����� ������
    public void FillVector(int[] V, int n) {
        for (int i = 0; i <V.length; i++) {
            V[i] = n;
        }
    }

    public int getH() {
        return this.H;
    }

    public int getP() {
        return this.P;
    }

    public int getN() {
        return this.N;
    }
}
