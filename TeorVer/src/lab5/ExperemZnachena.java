package lab5;

import java.util.Random;

public class ExperemZnachena {
	double a[][];
	public ExperemZnachena(double a[][]){
		this.a=a;
	}
	double P[]=new double[4];
	Random R=new Random();
	int k=0;
	public void pass(){
		for (int j = 0; j < 10000; j++) {
		double m ;
		double r = R.nextDouble();
		double min = -1/a[k][0]*Math.log(r);
		int min_ind = 0;		
		for (int i = 1; i < a[k].length; i++) {
			r = R.nextDouble();
			m = -1/a[k][i]*Math.log(r);
			if (m < min) {
				min = m;
				min_ind = i;
			} 	
		}
		P[k]+=min;
		k = min_ind;
		}
		System.out.println("Ймовірності отриманні шляхом моделювання");
		System.out.println("p1 = "+ P[0]/(P[1]+P[2]+P[0]+P[3]));
		System.out.println("p2 = "+ P[1]/(P[1]+P[2]+P[0]+P[3]));
		System.out.println("p3 = "+ P[2]/(P[1]+P[2]+P[0]+P[3]));
		System.out.println("p4 = "+ P[3]/(P[1]+P[2]+P[0]+P[3]));
	}
}