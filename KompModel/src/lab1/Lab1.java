package lab1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Random;

public class Lab1 {

	static double psi=3;
	static double n=3;
	static double f;
	static double t;
	static double h;
	static double []T=new double[10000];
	static double []R=new double[T.length];
	
	Random r =new Random();
//	File writeT=new File("E://file.txt");
	
	public void generation_t(double psi,double n){
		t=0.001;
		h=0.002;
		for (int i = 0; i < T.length; i++) {
			f=0;
			R[i]=r.nextDouble();
			t=0;
			do{
			t+=0.002;
			f+=((t+psi)/(2*n*t*Math.sqrt(2*Math.PI*psi*t)))*Math.exp(-Math.pow((t-psi), 2)/(2*n*n*psi*t));
		}
			while(f*h<R[i]);		
			T[i]=t;
			System.out.println(T[i]);
		}
	}
	public void FileWriter() throws IOException{
     generation_t(psi,n);
		try {
			DataOutputStream out=new DataOutputStream(new FileOutputStream("E://file.txt"));
			for (int i = 0; i < T.length; i++) {
				out.writeUTF(String.valueOf(T[i]));
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Lab1 l=new Lab1();
    //    l.generation_t(psi, n);
       l.FileWriter();
	}
}
