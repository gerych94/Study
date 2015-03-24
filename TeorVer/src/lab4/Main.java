package lab4;
import java.util.Scanner;
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double [] vubirka;
		System.out.println("Оберіть значення хі квадрат:1.для 1000 2.для 10");
	//	@SuppressWarnings("resource")
		Scanner scanByte=new Scanner(System.in);
		byte c=scanByte.nextByte();
		Context context;
		switch(c){
		case 1:{
			ThreeStageGenerator gen=new ThreeStageGenerator();
			vubirka=gen.generator();
			context=new Context(new Vubirka1000(vubirka,gen.a,gen.b,gen.c,gen.d,gen.h1,gen.h2,gen.h3));
			context.hita();
			break;
		}
		case 2:{
			vubirka=new double[10];
			context=new Context(new Vubirka10(vubirka));
			context.hita();
			break;
		}
		}	
	}
}
