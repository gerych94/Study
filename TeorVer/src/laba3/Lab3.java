package laba3;

import java.util.Random;

public class Lab3 {
	
	static Random t=new Random();
	
	public static double function(double x){
		return 1.5-1.5*Math.pow(x, 2);
	}
	
	public  double integral(){
		double a=0;
		double b=1;
		int n=10;
		double h=(b-a)/n;
		double S=0,x=0;
         
		for (int i = 1; i < n; i++) {
			x=a+h*i;
			S+=function(x);
		}
	double	integ=h*((function(a)+function(b))/2+S);
	return  integ;
	}
	public static double RanX(){
		double a=0;
		double b=1;
		int n=10;
		double h=(b-a)/n;
		double S=0,x=0;
		
	   	double	r=t.nextDouble();
			for(x=0;x<1;x=x+h){
				S+=h*function(x);
				if(S>r)
					return x;
		}
		return x;
	}
	public static double RanY(double x){
	double r = t.nextDouble();
		double h=3*(1-x);
		double y = 0.7;
		double S1=3*x*(1-x);
		double S2=1.5*(1-x)*x;
		double S = S1 + S2;
		if(S*r<=S1){
			y=S*r/h;
		}
		else{
			y=(1-x)+(S*r-S1)/(2*h);
		}
		return y;
	}
	public static double MatOchikyvana(double []X){
		double S=0;
		for (int i = 0; i <1000; i++) {
			S+=X[i];
		}
		return S/1000;
	}
	public static double Dispersion(double []X,double k){

		double T=0;
		for (int i = 0; i < 1000; i++) {
			T+=Math.pow((X[i]- k),2);
		}
		return T/1000;
	}
public static double Covariacia(double []X,double []Y,double MatX,double MatY){
	double cov=0;
	 
	for (int i = 0; i < 1000; i++) {
		cov+=(X[i]-MatX)*(Y[i]-MatY);
	}
	return cov/1000;
}	
	public static void main(String[] args) {
		double X[]=new double[1000];
		 double Y[]=new double[1000];
		 for (int i = 0; i < 1000; i++) {
			X[i]=RanX();
			Y[i]=RanY(X[i]);
		}
		 double MatX=MatOchikyvana(X);
	     double MatY=MatOchikyvana(Y);
	     
	     double DisX=Dispersion(X,MatX);
	     double DisY=Dispersion(Y,MatY);
         double SigX=Math.sqrt(DisX);
         double SigY=Math.sqrt(DisY);
         
         double Ro=Covariacia(X,Y,MatX,MatY)/(SigX*SigY);
         
         System.out.println("MatX "+MatX); 
         System.out.println("MatY "+MatY);
    //   System.out.println("DisX "+DisX);
    //   System.out.println("DisY "+DisY);
         System.out.println("SigX "+SigX);
         System.out.println("SigY "+SigY);
         System.out.println("êîåô³ö³ºíò êîðåëÿö³¿ "+Ro);
	}
}
