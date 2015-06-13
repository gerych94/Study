/**
 * Created by Vitaliy on 06.04.2015.
 */
public class Threads extends Thread {

    Monitor M;
    Function F;
    int id;

    public Threads(){
    }

    public Threads(Function F,Monitor M,int id){
        this.F=F;
        this.M=M;
        this.id=id;
    }

    public void run(){
         int startid=id*Function.getH();

        int endid;

        if(id!=Function.P-1){
            endid=(id+1)*Function.getH();
        }else {
            endid=Function.N;
        }

        int z4=0;
        int z5=0;

        int[][] ME1=new int[Function.N][Function.N];

        System.out.println("Thread "+(id+1)+"started");
        switch (id){
            case(0):
             Function.setZ(Function.getFillVector(Function.N));

                M.signIn();
                break;
            case(3):
                Function.setMO(Function.getFillMatrix(Function.N));
                M.signIn();
                break;
            case(4):
                ME1=Function.getFillMatrix(Function.N);
                M.Input_ME(ME1);
                M.signIn();
                break;
            case(5):
                Function.setMK(Function.getFillMatrix(Function.N));
                M.signIn();
                break;
        }
        M.WaitIn();



        z4=F.searchMax(Function.getZ(), startid, endid);
        z5=F.searchMin(Function.getZ(),startid,endid);

       // M.CalcZ(z4);
       // M.CalcZ(z5);
      //    M.SignCalcZ();
      //  M.WaitCalcZ();



        M.CalcZ(z4);
        M.CalcZ2(z5);
        z4=M.copyZ4();
        z5=M.copyZ5();
        ME1=M.copyME();

        F.Func(ME1,z4,z5,startid,endid);


        if(id==0){
            M.WaitOut();
            if(Function.N<13){
                Function.outputMatr(Function.MA);
            }
            System.out.println("Thread "+(id+1)+" end");
        }else{
            M.SignOut();
            System.out.println("Thread "+(id+1)+" end");
        }

    }


}
