package lab5;

import java.util.Random;

public class Lab5 {
 //  static int n=100;
	static double [][] P1={{0.1,0.2,0.3,0.6},
		                   {0.3,0,0.3,0.4},
		                   {0.1,0.8,0,0.1},
		                   {0.2,0.2,0.4,0.2}};
	
	static double [][] P2={{0.5,0,0.4,0.1},
                           {0,0.4,0.3,0.3},
                           {0.4,0,0.4,0.2},
                           {0.3,0.1,0.1,0.5}};
	static int [][] A={{1,1,2,3},
                       {2,1,3,5},
                       {1,0,2,4},
                       {2,2,4,8}};
	/*
	static double [][] P1=new double[n][n];
	static double [][] P2=new double[n][n];
	
	static int [][] A=new int[n][n];
	*/
	public void method(){
	    int k=0;
		float [] graph1=new float [P1.length];
		float []graph2=new float [P2.length];
		float S1=0;
		float S2=0;
		double S=0;
/*	 
	Random r =new Random();
		for (int i = 0; i < P1.length; i++) {
			for (int j = 0; j < P1[i].length; j++) {
				P1[i][j]=r.nextDouble();
				P2[i][j]=r.nextDouble();
				A[i][j]=r.nextInt(10);	
			}
		}
	*/	
		for (int i = 0; i < P1.length; i++) {
			S1=0;
			S2=0;
			k=100;
			while(k>0){
			for (int j = 0; j <P1[i].length; j++) {
				S1+=A[i][j]*P1[i][j];
				S2+=A[i][j]*P2[i][j];
			}
		k--;}
			graph1[i]=S1;
			graph2[i]=S2;
			
		}
		
		
		System.out.println("Pyx по першому графу");

		for (int i = 0; i < graph1.length; i++) {
			System.out.print(" з "+(i+1)+" стану="+graph1[i]);
		}
		
		System.out.println();
		System.out.println("Рух по другому графу");
		for (int i = 0; i < graph2.length; i++) {
			System.out.print(" з "+(i+1)+" стану="+graph2[i]);
		}
		
		
		for (int i = 0; i < graph2.length; i++) {
			if(graph1[i]>graph2[i]){
				S+=graph1[i];
			}else{
				S+=graph2[i];
			}
		}
		System.out.println();
		System.out.print("Максимальна Корисність="+S);
	}
	
	
	public static void main(String[] args) {
		Lab5 l=new Lab5();
		l.method();

	}

}
