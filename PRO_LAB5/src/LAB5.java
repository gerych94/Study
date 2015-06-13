

/**
 * Created by Vitaliy on 06.04.2015.
 */
public class LAB5 {


    public static void main(String[] args) {

        Monitor M =new Monitor();
        Function Func=new Function();

        Threads[] T=new Threads[Func.P];

        for (int i = 0; i <T.length ; i++) {
            T[i]=new Threads(Func,M,i);
        }
        for (int i = 0; i <T.length ; i++) {
            T[i].start();
        }
    }
}
