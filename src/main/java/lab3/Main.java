package lab3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Created by Vitaliy on 14.05.2015.
 */
public class Main {

    public static Robot r;
    public static JFrame frame;
    public static Cursor cursor;
    public static Cursor cursor2;
    public static int height = 500;
    public static int width = 500;
    public static int count = 0;

    public static void main(String[] args) throws AWTException {
        setFrame();
        r = new Robot();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        cursor = toolkit.createCustomCursor(toolkit.getImage("C://Users//Vitaliy//Study//src/lab3/Procent-teken.png"), new Point(0, 0), "cursor");
        cursor2=toolkit.createCustomCursor(toolkit.getImage("C://Users//Vitaliy//Study//src/lab3/Bezymyanny2.png"), new Point(0, 0), "cursor");
        frame.setCursor(cursor);
        frame.addMouseMotionListener(new MyMouseMotion());
        frame.addMouseListener(new MyMouseLitener());
    }

    public static void setFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocation(0,0);
        frame.setTitle("Драйвер миші");
        frame.setVisible(true);
    }
}
class MyMouseLitener implements MouseListener{
boolean flag=true;
   // public  MyMouseMotion myMouseMotion;

  //  Toolkit toolkit = Toolkit.getDefaultToolkit();
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1){
             Main.width=e.getX()+10;
            Main.height=e.getY()+10;


                  Main.frame.setSize(Main.width,Main.height);

            if (flag) {
                Main.frame.setCursor(Main.cursor2);
                flag=!flag;
            }else {
                Main.frame.setCursor(Main.cursor);
                flag=!flag;
            }

          //  if(flag){

          //  }

         //   Main.r.mouseMove(Main.width / 2, Main.height/2);
            Main.count++;
        }else {
            Main.frame.setVisible(false);
        }
      //  if (e.getButton() == MouseEvent.BUTTON3)
          //  Main.r.mouseMove(Main.width / 2, Main.height/2);

    }



    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
}

class MyMouseMotion implements MouseMotionListener{

    private MyXYListener listener;


    public MyMouseMotion(){
        this.listener = new MyXYListener();
    }

    public void mouseDragged(MouseEvent e) {
    }


    public void mouseMoved(MouseEvent e) {
       int  x = e.getX();
       int  y = e.getY();

        int newX = x;
        int newY = y;

/*if(x <= 20) newX = 20;
if(x >= Main.width-20) newX = Main.width-20;
if(y <= 50) newY = 50;
if(y >= Main.height-30) newY = Main.height-30;*/
        Pair point = listener.getPoint(newX,newY);
        newX = point.getX();
        newY = point.getY();


      /*  if(e.getX()==Main.frame.getWidth()-30){
            newX=0;
            newY=point.getY();
        }
        if (e.getY()==Main.frame.getHeight()-30){
            newY=0;
            newX=point.getX();
        }
       /* if (newX==0){
            newX=Main.frame.getWidth()-20;
            newY=point.getY();
        }

        if(newY==0){
            newY=Main.frame.getHeight()-20;
            newX=point.getX();
        }*/


        Main.r.mouseMove(newX,newY);
    }

    class MyXYListener{
        private int prevX = -1;
        private int prevY = -1;

        Pair getPoint(int x, int y){
            int newX = x;
            int newY = y;

            if(x==(Main.frame.getWidth())){
                Main.r.mouseMove(10,y);
            }
            if(y==(Main.frame.getHeight())){
                Main.r.mouseMove(x,10);
            }

/* // коли ліворуч то праворуч
if (prevX > x+2){
newX += 10;
}
// коли вниз то вниз праворуч
if (prevY < y){
newX += 3;
}
if (prevY > y){
newX += 3;
}
if (prevX < x){
newX-= 10;
newY-= 10;
}*/
            if(Main.count % 4 == 1){
//up
                if (prevY > y) {
                    newX -= 20;
                    newY = y;
                }
//right
                if (x > prevX){
                    newX = x;
                    newY +=10;
                }
//down
                if (prevY < y){
                    newX = x;
                    newY -=10 ;
                }
//left
                if (prevX > x){
                    newX -= 5;
                    newY +=10 ;
                }
            }
            prevX = newX;
            prevY = newY;
            return new Pair(newX,newY);
        }
    }

    class Pair{
        private int x,y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public String toString(){
            return "("+x+","+y+")";
        }
    }

}
