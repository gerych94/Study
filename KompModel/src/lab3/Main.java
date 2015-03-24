package lab3;

import lab3.model.interfaces.Device;
import lab3.model.simpledevice.CP;
import lab3.model.simpledevice.SimpleDevice;
import lab3.model.statustable.StatusTable;
//import lab3.statustable.*;//statustable.StatusTable;

/**
 * Created by byte on 10.11.14.
 *
 */
public class Main {
    public static void main(String[] args) {
        Device CPU = new CP("CP", /*COUNT OF TASKS*/ 100000,/*TIME OF PROCESSING*/ 1,/*COUNT OF PROCESSOR*/ 2);

        Device north_bridge = new SimpleDevice("North bridge", 2.3);

        Device south_bridge = new SimpleDevice("South bridge", 7.5);

        Device RAM = new SimpleDevice("RAM", 2.5);

        Device video_Processor = new SimpleDevice("Video Processor", 7.5);

        Device ISA = new SimpleDevice("ISA", 20);

        Device LPT = new SimpleDevice("LPT", 20);

        Device audio_Procesor=new SimpleDevice("Audio Procesor", 20);
        
    //    Device COM = new SimpleDevice("COM", 375);

     //  Device USB = new SimpleDevice("USB", 375);

   /*     
        CPU.addDevice(north_bridge, 0.4);
        north_bridge.addDevice(CPU, 0.5);

        CPU.addDevice(CPU, 0.6);

        RAM.addDevice(north_bridge, 1);
        north_bridge.addDevice(RAM, 0.4);


        north_bridge.addDevice(south_bridge, 0.1);
        south_bridge.addDevice(north_bridge, 0.495);

        south_bridge.addDevice(video_Processor, 0.5);
        video_Processor.addDevice(CPU, 1);

        south_bridge.addDevice(ISA, 0.005);
        ISA.addDevice(south_bridge, 0.7);

        ISA.addDevice(LPT, 0.1);
        LPT.addDevice(CPU, 1);

        COM.addDevice(ISA, 1);
        ISA.addDevice(COM, 0.1);

        USB.addDevice(CPU, 1);
        ISA.addDevice(USB, 0.1);
*/
  CPU.addDevice(north_bridge, 0.2);
  north_bridge.addDevice(CPU, 0.4);
  
  CPU.addDevice(CPU, 0.8);
  
  north_bridge.addDevice(RAM, 0.3);
  north_bridge.addDevice(south_bridge, 0.3);
  
  RAM.addDevice(north_bridge, 1);
  
  south_bridge.addDevice(video_Processor, 0.1);
  south_bridge.addDevice(ISA, 0.1);
  south_bridge.addDevice(north_bridge, 0.75);
  south_bridge.addDevice(audio_Procesor, 0.05);
  
  ISA.addDevice(south_bridge, 0.4);
  ISA.addDevice(LPT, 0.6);
  
  LPT.addDevice(CPU, 1);

  audio_Procesor.addDevice(CPU, 1);
  
  video_Processor.addDevice(CPU, 1);
  
        StatusTable table  = StatusTable.getStatusTable();

        
        
        for (int i = 0; i < 100000; i++) {
            table.next();
        }


        System.out.print(CPU);

        System.out.print(north_bridge);

        System.out.print(south_bridge);

        System.out.print(RAM);

        System.out.print(video_Processor);

        System.out.print(ISA);

        System.out.print(LPT);

        System.out.print(audio_Procesor);
    //    System.out.print(COM);

     //   System.out.print(USB);

        System.out.println("ʳ������ �������� " + SimpleDevice.sum);
        System.out.println(table.getCurrentTime());
             

        /*System.out.println(CPU.getChildrenDevice().test());
        System.out.println(RAM.getChildrenDevice().test());
        System.out.println(south_bridge.getChildrenDevice().test());
        System.out.println(north_bridge.getChildrenDevice().test());
        System.out.println(video_Processor.getChildrenDevice().test());
        System.out.println(ISA.getChildrenDevice().test());
        System.out.println(COM.getChildrenDevice().test());
        System.out.println(USB.getChildrenDevice().test());
        System.out.println(LPT.getChildrenDevice().test());*/

/*        CPU.makeTree();*/





    }
}
