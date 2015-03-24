package lab4;

import java.util.Random;
import java.util.Scanner;

public class ThreeStageGenerator {
	Scanner scan=new Scanner(System.in);
	double a,b,c,d,h1,h2,h3;
	public double [] generator(){
		a=3;
		b=5;
		c=6;
		d=7;
		h1=0.1;
		h2=0.3;
		h3=0.5;
		double S1 = (b-a)*h1;
		double S2 = (c-b)*h2;
		double S3 = (d-c)*h3;
		double S = S1 + S2 + S3;
		Random Ran = new Random();
		double R[] = new double[1000];
		double r[] = new double[1000];
		for (int i = 0; i < 1000; i++) {
	   	    r[i] = Ran.nextDouble();
			//r[i] = 0.5;
	   	    if( r[i]*S <= S1){
	   	    	R[i] =  a + r[i]*S/h1;
	   	    	} else { 
	   	    		if(r[i]*S <= S1+S2){
	   	    			R[i] = b + (r[i]*S - S1)/h2;
	   	    		} else {
	   	    			R[i] = c +(r[i]*S - S1 - S2)/h3;
			}	
		}
	   	   // System.out.println(R[i]);
		}
		
		return R;
	}
}
