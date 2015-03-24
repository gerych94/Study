package laba1;

// import java.util.Random;

public class Program {

	
	public static void main(String[] args) {
	      int [] b=new int[10000];
		  int [] mas  = new int[256];
		   int h=1;
		   int p=1;
	//    Random r = new Random();
		for (int i = 0; i <mas.length; i++) {
			if(i%h==0){
				p=(p+1)%2;
				mas[i]=p;
			}
				
		//	mas[i]=r.nextInt(2);
		}
		LSFR l []=new LSFR[8];
		int [] polinom1 = {1,0,0,0,0,1};
     l[0]= new LSFR(polinom1);
    int[] polinom2 = {1,0,0,0,1,0,0};
    l[1]=new LSFR(polinom2);
    int[] polinom3={1,0,0,0,1,1,1,0};
    l[2]=new LSFR(polinom3);
    int[] polinom4={1,0,0,0,0,0,0,1,0,0};
    l[3]=new LSFR(polinom4);
    int[] polinom5={1,0,0,0,0,0,0,0,0,1,0};
    l[4]=new LSFR(polinom5);
    int[] polinom6={1,0,0,0,0,0,1,0,1,0,0,1};
    l[5]=new LSFR(polinom6);
    int[] polinom7={1,0,0,0,0,0,0,0,0,1,1,0,1};
    l[6]=new LSFR(polinom7);
    int[] polinom8={1,0,0,0,1,0,0,0,1,0,0,0,0,1};
    l[7]=new LSFR(polinom8);

    for (int i = 0; i <b.length; i++) {
    	int k = 0;
    	 h = 1;
    	for (int j = 0; j < l.length; j++) {
			k+=l[j].Next()*h;
			h=h<<1;
		}
    	b[i]=mas[k];
    	
    	//System.out.println(mas[k]);
	}    
    Test t = new Test(b);
    System.out.println(t.Chast()+" Частотний тест");
    System.out.println(t.ChastDif()+" Частотно диференційний тест");
    int e[] = t.Rang();
    System.out.println("Ранговий тест");
    for (int i = 0; i < e.length; i++) {	
    System.out.println(e[i]);
    }
    System.out.println("Лінійна складність");
    System.out.println(t.BerlekampTest());
	}
}
