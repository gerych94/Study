package lab1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Lab1_restorer {

	
	static double []T=new double[10000];
	static double []R=new double[T.length];
	static double n;
	static double psi;
	Random r =new Random();
	public void FileReader()throws IOException{
	 	try{
	 		DataInputStream input=new DataInputStream(new FileInputStream("J://filename.txt"));
	 		for (int i = 0; i < T.length; i++) {
				T[i]=Double.parseDouble(input.readLine());
			}
	 		input.close();
	 	}
	 	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void restorer_psi_n() throws IOException{
		FileReader();
		double t=0;
		double D=0;
		double S=0;
		double Sum=0;
		double f=0;
	
		for (int i = 0; i < T.length; i++) {
			S+=T[i];
		}
		t=S/T.length;
		for (int i = 0; i < T.length; i++) {
			Sum+=Math.pow((t-T[i]), 2);
		}
		D=Sum/(T.length-1);
	//	psi=t/(1+Math.pow(n, 2)/2);
		while (Math.abs(D - t * t * n * n * (1 + 5 * n * n / 4) / Math.pow((1 + n * n / 2), 2)) > 0.02) {
			n += 0.001;
			}
		
		System.out.println("Середній час t= "+t);
		System.out.println("Дисперсія= "+D);
		System.out.println("n= "+n);
		psi=t/(1+Math.pow(n, 2)/2);
		System.out.println("psi "+psi);
	double t1=0;
	double h1=0;
		t1=0.001;
		h1=0.002;
		for (int i = 0; i < R.length; i++) {
			f=0;
			R[i]=r.nextDouble();
			t1=0;
			do{
			t1+=0.002;
			f+=((t1+psi)/(2*n*t1*Math.sqrt(2*Math.PI*psi*t1)))*Math.exp(-Math.pow((t1-psi), 2)/(2*n*n*psi*t1));
		}
			while(f*h1<R[i]);		
			R[i]=t1;
	//		System.out.println(R[i]);
		}
		double count=0;
		double count2=0;
		double count3=0;
		double count4=0;
		double count5=0;	
		for (int i = 0; i < T.length; i++) {
			if(T[i]>0&&T[i]<0.2){
				count++;
			}if(T[i]>0.2&&T[i]<0.5){
				count2++;
			}if(T[i]>0.5&&T[i]<0.8){
				count3++;
			}if(T[i]>0.8&&T[i]<1){
				count4++;
			}if(T[i]>1){
				count5++;
			}
		}
		double k=0;
		double k2=0;
		double k3=0;
		double k4=0;
		double k5=0;
		for (int i = 0; i < R.length; i++) {
			if(R[i]>0&&R[i]<=0.2){
				k++;
			}if(R[i]>0.2&&R[i]<=0.5){
				k2++;
			}if(R[i]>0.5&&R[i]<=0.8){
				k3++;
			}if(R[i]>0.8&&R[i]<=1){
				k4++;
			}if(R[i]>1){
				k5++;
			}
		}
	double xi=0;
double	p1=k/count;
double p2=k2/count2;
double p3=k3/count3;
double p4=k4/count4;
double p5=k5/count5;
        


//	xi=k/count+k2/count2+k3/count3+k4/count4+k5/count5;
//	xi=Math.pow((k-R.length*p1), 2)/(R.length*p1)+Math.pow((k2-R.length*p2), 2)/(R.length*p2)+Math.pow((k3-R.length*p3), 2)/(R.length*p3)+Math.pow((k4-R.length*p4), 2)/(R.length*p4)+Math.pow((k5-R.length*p5), 2)/(R.length*p5);
	xi=(p1+p2+p3+p4+p5)/5;
    
	System.out.println("xi " +xi);
	}
	
	public static void main(String[] args) throws IOException {
		Lab1_restorer l=new Lab1_restorer();
		l.restorer_psi_n();

	}

}
