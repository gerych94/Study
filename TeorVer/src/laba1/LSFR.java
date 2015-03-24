package laba1;

import java.util.Random;

public class LSFR {

     private int [] Polinom;	
	 private int [] Array;
	
	public LSFR (int [] Polinom){
		Random nm = new Random();
		this.Polinom=Polinom;
		Array = new int[Polinom.length];
		for (int i = 0; i < Polinom.length; i++) {
			Array[i]=nm.nextInt(2);
			
		}
	}
	public int Next(){
		byte a=(byte) Array[Array.length-1];
		byte buf  = 0;
		for (int i = Polinom.length-1; i >=0 ; i--) {
		    if(Polinom[i]==1 ){
			  buf= (byte)(buf^Array[i]);
	
		    }
		     if (i>0)
		     Array[i]=Array[i-1];
         }
		Array[0]=buf;
		return a;
	 }
	}
	
		      
		
              

        

