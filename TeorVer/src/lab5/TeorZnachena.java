package lab5;

public class TeorZnachena {
	double a[][];
	public TeorZnachena(double a[][] ){
		this.a=a;
	}
	public double getDet(double a[][]){
		if (a.length==0) {
		return 1;
		}
		double buf[][] = new double[a.length-1][a.length];
		double sum = 0;
		int k =0;
		for (int i = 0; i < a.length; i++) {
		k = 0;
		for (int j = 0; j < buf.length; j++) {
		for (int l = 0; l < buf.length; l++) {
		if (l==i) k++;
		buf[j][l]=a[j+1][l+k];
		}
		k =0;
		}
		if (i%2 == 0) k = 1; else k = -1;
		sum+=k*a[0][i]*getDet(buf);

		}
		return sum;
		}
}
