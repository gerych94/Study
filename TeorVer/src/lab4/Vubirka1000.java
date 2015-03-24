package lab4;

public class Vubirka1000 implements Strategy{
	double [] vubirka=new double [1000];
	double a,b,c,d,h1,h2,h3;
	public Vubirka1000(double vubirka [],double a,double b,double c,double d,double h1,double h2,double h3){
		this.vubirka=vubirka;
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
		this.h1=h1;
		this.h2=h2;
		this.h3=h3;
	}
	
	public double hita(){		
		//Сортування масиву
		double temp;
		for (int i = 0; i < 999; i++) {
			for (int j = 0; j < 999-i; j++) {
				if(vubirka[j]>vubirka[j+1]){
					temp=vubirka[j+1];
					vubirka[j+1]=vubirka[j];
					vubirka[j]=temp;
				}
			}	
		}
		//********************************************************
		int ji=0;
		double [] x=new double[30];
		for (int i = 20; i < 1000; i=i+30) {
			if(ji!=30){
				x[ji]=(vubirka[i]+vubirka[i+1])/2;
				ji++;
			}
			else{
				break;
			}
		}

		
		int count []=new int[31];
		
		
		for (int i = 0; i < 1000; i++) {
			if (vubirka[i]<x[0]) {
				count[0]++;
				continue;
			}
			if (vubirka[i]>x[29]) {
				count[30]++;
				continue;
			}
			for (int j = 0; j < 30; j++) {
				if (vubirka[i]>x[j] && vubirka[i]<x[j+1]) {
					count[j+1]++;
					break;
				}
		
			}
		}
		//********************************************************************
		//ПОШУК ЙМОВІРНОСТЕЙ
		double [] p=new double[31];
		double currentElem=a;
		double nextElem=x[0];
		for (int j = 0; j < 31; j++) {
			if(currentElem>=a && currentElem<b && nextElem<b){
				p[j]=(nextElem-currentElem)*h1;
				if(currentElem==x[28]){
					currentElem=x[29];
					nextElem=d;
					continue;
				}
				else{
					if(nextElem==d){
						break;
					}
					currentElem=x[j];
					nextElem=x[j+1];
					continue;
				}
			};
			if(currentElem>=a && currentElem<b && nextElem>=b && nextElem<c){
				p[j]=(nextElem-b)*h2+(b-currentElem)*h1;
				if(currentElem==x[28]){
					currentElem=x[29];
					nextElem=d;
					continue;
				}
				else{
					if(nextElem==d){
						break;
					}
				currentElem=x[j];
				nextElem=x[j+1];
				continue;
				}
			};
			if(currentElem>=a && currentElem<b && nextElem>=c){
				p[j]=(nextElem-c)*h3+(c-b)*h2+(b-currentElem)*h1;
				if(currentElem==x[28]){
					currentElem=x[29];
					nextElem=d;
					continue;
				}
				else{
					if(nextElem==d){
						break;
					}
				currentElem=x[j];
				nextElem=x[j+1];
				continue;
				}
			};
			if(currentElem>b && currentElem<=c && nextElem>=b && nextElem<=c){
				p[j]=(nextElem-currentElem)*h2;
				if(currentElem==x[28]){
					currentElem=x[29];
					nextElem=d;
					continue;
				}
				else{
					if(nextElem==d){
						break;
					}
				currentElem=x[j];
				nextElem=x[j+1];
				continue;
				}
				};
			if(currentElem>b && currentElem<=c && nextElem>c){
				p[j]=(nextElem-c)*h3+(c-currentElem)*h2;
				if(currentElem==x[28]){
					currentElem=x[29];
					nextElem=d;
					continue;
				}
				else{
					if(nextElem==d){
						break;
					}
				currentElem=x[j];
				nextElem=x[j+1];
				continue;
				}
			};
			if(currentElem>c && nextElem>c){
				p[j]=(nextElem-currentElem)*h3;
				if(currentElem==x[28]){
					currentElem=x[29];
					nextElem=d;
					continue;
				}
				else{
					if(nextElem==d){
						break;
					}
				currentElem=x[j];
				nextElem=x[j+1];
				continue;
				}
			};
		}
		//****************************************************************
		double xitakv=0;
		for (int i = 0; i < 31; i++) {
			xitakv+=(count[i]-1000*p[i])*(count[i]-1000*p[i])/1000/p[i];
		}
		Xita xita=new Xita();
		double min=100000;
		int pos=0;
		for (int i = 0; i < 11; i++) {
			if(Math.abs((xita.getl29()[i]-xitakv))<min){
				min=Math.abs(xita.getl29()[i]-xitakv);
				pos=(byte)i;
			}
		}
		System.out.println("Хі^2 = "+xitakv);
		System.out.println("Ймовірність належності вибірки до розподілу: "+(1-xita.getg()[pos]));
		return xita.getg()[pos];
	}
}
