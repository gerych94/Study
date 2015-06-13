/**
 * Created by Vitaliy on 06.04.2015.
 */
public class Monitor {



    private static int z=100;
    private   static int z2=100;
    private int m=100;
    private   static int [][]ME=new int[Function.N][Function.N];

    private int SIn=0;
    private int SOut=0;
    private   int SCM=0;


    synchronized int copyM(){
        return m;
    }
    synchronized void Input_ME(int [][] MM){
        Monitor.ME=MM;
    }
    synchronized int copyZ4(){
        return z;
    };

    synchronized int copyZ5(){
        return z2;
    }
    synchronized int [][] copyME(){
        return ME;
    }

    synchronized void CalcZ(int x){
        if(x<z){
            z=x;
        }
    }
    synchronized void CalcZ2(int x){
        if(x<z2){
            z2=x;
        }
    }
    synchronized void signIn(){
        SIn++;
        if(SIn==4){
            notifyAll();
        }
    }
    synchronized void SignOut(){
        SOut++;
        if(SOut==Function.P-1){
            notify();
        }
    }
    synchronized void SignCalcZ(){
        SCM++;
        if(SCM==Function.P-1){
            notifyAll();
        }
    }
    synchronized void WaitIn(){
        try{
            if(SIn==4){
            }else{
                wait();
            }
        }catch (Exception exeption){
            System.out.println(exeption.getLocalizedMessage());
        }
    }

    synchronized void WaitOut(){
        try{
            if(SOut==Function.P-1){

            }else{
                wait();
            }
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    synchronized void WaitCalcZ(){
        try{
            if(SCM<Function.P){
                wait();
            }
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

    }

}



