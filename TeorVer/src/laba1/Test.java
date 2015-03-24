package laba1;

public class Test {
   public Test(int[] a){
	  mas = a;
   }
   int [] mas;
	
	public float Chast(){
		int S=0;
		float f;
		 for (int i = 0; i <mas.length; i++) {
		
			S+=mas[i];
		}
		 
		f= (float)S/10000;
		return f;
	}
     public float ChastDif(){
    	 int k = 0;
    	for (int i = 1; i < mas.length; i++) {
    		if(mas[i-1]!=mas[i]){
    	    	 k++;
    	    }
		}
		return (float)k/9999; 
	    
      }
     public int[] Rang(){
    	 int [] g= new int[8];
    	 
    	 for (int i = 0; i < mas.length-2; i++) {
    		g[ mas[i]*4+mas[i+1]*2+mas[i+2]]++;
		}
		return g;
     }
      
   public int BerlekampTest(){
	  int x=1,L=0,N=0,d;
	  int [] C= new int[mas.length];
	  int [] B=new int [mas.length];
	  int [] T=new int[mas.length];
	  C[0]=1;
	  B[0]=1;
	   while(N<mas.length){
		   d = mas[N]; 
		   for(int i=1;i<=L;i++)
			  d = d ^ mas[N-i] & C[i];
		   if(d==0){
			   x++;
		   }
		   else{
			if(2*L>N){
	           Corect(B,C,x);
	           x++;
			}
			else{
				L=N+1-L;
                	T = C.clone();
                	Corect(B, C, x);
                	B = T.clone();
                	x=1;
			} 
		   }
		   N++;
		 }
	   return L;
   }
   public static void Corect(int [] B, int [] C, int x){
	   int [] CD = new int [C.length];
	   for(int i=B.length-1;i>=0;i--){
		  if(B[i]==1){
			  CD[i+x]=1;
			  CD[i]=0;
		  }
	   }
	   for(int i=0;i<CD.length;i++){
		   C[i]=C[i]^CD[i];
	   } 
	   System.out.println(C[x]);
   }   
}