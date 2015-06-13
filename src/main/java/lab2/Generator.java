package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Vitaliy on 15.04.2015.
 */
public class Generator {

    public static void main (String[] args){

        int inf = 3;
        int con = 6;
        int[] infPart = {1, 1, 1};
        infPart = new int[inf];
        Arrays.fill(infPart, 1);
        String path = "F:/file.txt";
        FileOutputStream outputStream = null;
        Scanner scanner=null;
        try {
            outputStream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter=new PrintWriter(outputStream,true);

        //infPart[1] = 2;*/
        int[][] errors = {{1, 5}, {2, 4}, {3, 3}};
       // System.out.println("--- Encoding ---");
        printWriter.println("--- Encoding ---");
        printWriter.flush();
        Message m = new Message(inf, con, infPart);
        int[] mes = m.createMessage();
       // System.out.println("Message: " + Arrays.toString(mes));
      //  printWriter.println("Message: "+Arrays.toString(mes));
          printWriter.println("Message: ");
        for (int i = 0; i <mes.length ; i++) {
            printWriter.print(String.valueOf(mes[i])+",");
       }

        printWriter.flush();
      /*  int[] or = new int[mes.length];
        System.arraycopy(mes, 0, or, 0, or.length);
        Decryptor.reverse(or);
        m.damage(mes, errors);
     //   System.out.println("Damaged message: " + Arrays.toString(mes));
      //  printWriter.println("Damaged message: " );
      /*  for (int i = 0; i <mes.length ; i++) {
            printWriter.print(String.valueOf(mes[i]) + ",");
        }

        printWriter.flush();

        System.out.println("--- end encoding ---");*/
       // printWriter.println("--- end encoding ---");
       // printWriter.flush();
        /*String string="";

        try {
             scanner=new Scanner(new File("D:/file.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()){
            string=scanner.nextLine();
        }
        String[] str=string.split(",");
        for (int i = 0; i <str.length ; i++) {
             mes[i]= Integer.parseInt(str[i]);
        }

        Decryptor d = new Decryptor(mes, or, con);
        boolean er = d.hasErrors();
        if(er){
            d.localization();
        } else {
            System.out.println("Message: " + Arrays.toString(d.message));
        }
        System.out.println("--- end decoding ---");*/
    }
}
