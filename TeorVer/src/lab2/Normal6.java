package lab2;

import java.util.Random;
import java.util.Scanner;

public class Normal6 {
	
	static double [] R=new double [5000];
	private static Scanner in;
	
	public static double Ran(){
         	double S=0;
		Random t=new Random();
		for (int i = 0; i < 40; i++) {
			 S+=t.nextDouble();
		}
		return S;	
	}
	public static double Yi(){
		return ((Ran()-20)/1.825); 	
	}
	
	public static double Ri(double a,double z){
		return (a+z*Yi());
	}
	
	public static double MatOchikyvana(double a,double z){
		double S=0;
		for (int i = 0; i < R.length; i++) {
			R[i]=Ri(a, z);
			S+=R[i];
		}
      return S/5000;				
	}
	
	public static double Zavdana(double a,double z){
		double K=0;
		for (int i = 0; i <R.length; i++) {
			R[i]=Ri(a,z);
			if(R[i]>0&R[i]<7)
				K=K+1;
		}
		return K/5000;
	}
	public static double Dispersion(double a,double z){
		double D = 0;
		double T=0;
	    double F=MatOchikyvana(a,z);	
		for (int i = 0; i < R.length; i++) {
		  T+=Math.pow((R[i]-F), 2);
			
		}
		D=T/5000;
		return D;
	}
	public static double KvadratVidhul(double a,double z){
		
		double K=Math.sqrt(Dispersion(a,z));
		return K;
	}
	public static void main(String args[]){
	
		in = new Scanner(System.in);
		System.out.println("введіть параметр m");
    	 double a=in.nextDouble();
	     System.out.println("введіть параметр сигма");
	     double z=in.nextDouble();
	     System.out.println("завдання "+ Zavdana(a,z));
	  System.out.println("Дисперсія " + Dispersion(a,z));
	 System.out.println("Математичне очікування " + MatOchikyvana(a,z));
	 System.out.println("Середннє квадратичне відхилення " + KvadratVidhul(a,z));
	}	
}