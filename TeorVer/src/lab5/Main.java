package lab5;

public class Main {
	public static void main(String[] args) {
		double k[][]={{1,1,1,1},{0.5,-2.3,0,3},{0,2,-1,0.7},{0,0,1,-4.1} };
		double p[][]={{1,1,1,1},{0,-2.3,0,3},{0,2,-1,0.7},{0,0,1,-4.1} };
		double t[][]={{1,1,1,1},{0.5,0,0,3},{0,0,-1,0.7},{0,0,1,-4.1} };
		double v[][]={{1,1,1,1},{0.5,-2.3,0,3},{0,2,0,0.7},{0,0,0,-4.1} };
		double n[][]={{1,1,1,1},{0.5,-2.3,0,0},{0,2,-1,0},{0,0,1,0}};
		TeorZnachena f=new TeorZnachena(k);
	    TeorZnachena f1=new TeorZnachena(p);
	    TeorZnachena f2=new TeorZnachena(t);
	    TeorZnachena f3=new TeorZnachena(v);
	    TeorZnachena f4=new TeorZnachena(n);
	    double p1=f1.getDet(p)/f.getDet(k);
	    double p2=f2.getDet(t)/f.getDet(k);
	    double p3=f3.getDet(v)/f.getDet(k);
	    double p4=f4.getDet(n)/f.getDet(k);
	    System.out.println("p1 "+p1+" p2 "+p2+" p3 "+p3+" p4 "+p4);
	    double a[][]={{0,0.5,0,0},
	    		      {0.3,0,2,0},
	    		      {0,0,0,1},
	    		      {0.4,3,0.7,4} };
	    ExperemZnachena f5=new ExperemZnachena(a);
	    f5.pass();
	    
	    
	}
}
