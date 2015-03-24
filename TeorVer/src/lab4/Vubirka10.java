package lab4;

public class Vubirka10 implements Strategy{
	double [] vubirka=new double [10];
	public Vubirka10(double vubirka []){
		this.vubirka=vubirka;
	}
	public double hita(){
		//Початкові параметри
		double a,b,c,d,h1,h2,h3;
		a=3;
		b=4;
		c=6;
		d=7;
		h1=0.2;
		h2=0.1;
		h3=0.6;
		vubirka[0]=3.1;   vubirka[4]=6.2;   vubirka[7]=6.7;
		vubirka[1]=3.7;   vubirka[5]=6.3;   vubirka[8]=6.8;
		vubirka[2]=4.8;   vubirka[6]=6.4;   vubirka[9]=6.9;
		vubirka[3]=5.2;
		//***************************************************************
		//Сортування масиву
		double temp;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9-i; j++) {
				if(vubirka[j]>vubirka[j+1]){
					temp=vubirka[j+1];
				  	vubirka[j+1]=vubirka[j];
					vubirka[j]=temp;
				}
			}	
		}	
		//****************************************************************
		
		double x []=new double[3];
		x[0]=3.5;
		x[1]=4.9;
		x[2]=6.5;
		int count []=new int[4];
		for (int i = 0; i < 10; i++) {
			if (vubirka[i]>a && vubirka[i]<x[0]) {
				count[0]++;
			}
			if (vubirka[i]>x[0] && vubirka[i]<x[1]) {
				count[1]++;
			}
			if (vubirka[i]>x[1] && vubirka[i]<x[2]) {
				count[2]++;
			}
			if (vubirka[i]>x[2]) {
				count[3]++;
			}
		}
		//******************************************************************
		//ПОШУК ЙМОВІРНОСТЕЙ
		double [] p=new double[4];
		double currentElem=a;
		double nextElem=x[0];
		for (int j = 0; j < 4; j++) {
			if(currentElem>=a && currentElem<b && nextElem<b){
				p[j]=(nextElem-currentElem)*h1;
				if(currentElem==x[1]){
					currentElem=x[2];
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
				if(currentElem==x[1]){
					currentElem=x[2];
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
				if(currentElem==x[1]){
					currentElem=x[2];
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
				if(currentElem==x[1]){
					currentElem=x[2];
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
				if(currentElem==x[1]){
					currentElem=x[2];
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
				if(currentElem==x[1]){
					currentElem=x[2];
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
		//***********************************************************************
		double xitakv=0;
		for (int i = 0; i < 4; i++) {
			xitakv+=(count[i]-10*p[i])*(count[i]-10*p[i])/10/p[i];
		}
		Xita xita=new Xita();
		double min=1000;
		byte pos=0;
		for (int i = 0; i < 11; i++) {
			if(Math.abs((xita.getl3()[i]-xitakv))<min){
				min=Math.abs(xita.getl3()[i]-xitakv);
				pos=(byte)i;
			}
		}
		System.out.println("Хі^2 = "+xitakv);
		System.out.println("Ймовірність належності вибірки до розподілу: "+(1-xita.getg()[pos]));
		return xita.getg()[pos];
	}
}