package lab2;

import java.util.Random;

public class Lab2 {

	static double l=0.3;
	static double n=0.6;
	
	static double []TimeCome=new double [5000];
	static double []TimeJob=new double[5000];
	
	static Random r =new Random();
	
	public static double[] Time_of_Come(){
		
		for (int i = 0; i < TimeCome.length; i++) {
			TimeCome[i]=-1/l*Math.log(r.nextDouble());
		}
		
		return TimeCome;
	}
	
	public static double [] Time_of_Job(){
	//	double []R=new double[TimeJob.length];
		double sigma=1/n;
		double S=0;
		for (int i = 0; i < TimeJob.length; i++) {
			TimeJob[i]=sigma*r.nextGaussian()+n;
		}
		return TimeJob;
	}
	
	public static void FIFO(){
		double [] Come=Time_of_Come();
		double [] Job=Time_of_Job();
		double [] Cherga=new double[Come.length];
		double [] Time=new double[Come.length];
		double [] Time_of_Znah=new double[Come.length];
		double S=0;
		double troz=0;
		double t=0;
		double x1=0;
		double x2=0;
		double x3=0;
		double x4=0;
		double x5=0;
		for (int i = 0; i < Job.length; i++) {
			if(Job[i]<0){
				Job[i]=0;
			}
		}	
		
		for (int i = 0; i < Time.length; i++) {
			S+=Come[i];
			Time[i]=S;
		}
		troz=Come[0]+Job[0];
			for (int i = 1; i < Come.length-1; i++) {
		  //      troz=Come[i]+Job[i];
		        
		        if(troz<Time[i]){
		        	Time_of_Znah[i]=troz-Time[i];
		        	
		        }else{
		        	Cherga[i]=Time[i];
		        	
		        }
		        troz+=Job[i];
		        Time_of_Znah[i]=troz-Cherga[i];
		       
		        
		        
		      System.out.println(Time_of_Znah[i]);
			}
	}
	
	public void visual(double [] a){
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
		Lab2 l=new Lab2();
	    Time_of_Come();
	    Time_of_Job();
	   l.FIFO();
	//	l.visual(Time);

	}

}
