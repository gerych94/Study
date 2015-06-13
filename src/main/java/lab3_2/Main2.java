package lab3_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main2 {
    static Robot r;
    static JFrame frame;
    static Cursor cursor1;
    static Cursor cursor2;
    static boolean isChanged = false;
    static  int RobotMove = 0;
    static boolean isRobotMove=false;
    static int x = -10000;
    static int y = -10000;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws AWTException {
        setFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        cursor1 = toolkit.createCustomCursor(toolkit.getImage("C://Users//Vitaliy//Study//src/lab3_2/kurs1.png"), new Point(0, 0), "cursor1");
        cursor2 = toolkit.createCustomCursor(toolkit.getImage("C://Users//Vitaliy//Study//src/lab3_2/kurs2.png"), new Point(0, 0), "cursor2");
        frame.setCursor(cursor1);
        r = new Robot();
        try {
            frame.addMouseMotionListener(new MouseMotionListener() {
                Robot r = new Robot();

                @Override
                public void mouseDragged(MouseEvent e) {
                    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    r.mouseMove(e.getX(),e.getY());
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    //frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ), new Point(), null ) );
                    int dx;
                    int dy;
                    if (x!=-10000){
                        dx=e.getX()-x;
                        dy=e.getY()-y;
                    }else {
                        x=e.getX();
                        y=e.getY();
                        return;
                    }
                    System.out.println("dx:" + dx + " dy:" + dy);
                    if (Math.abs(dx)>Math.abs(dy)){ //x
                        if (dx>0){
                            System.out.println("right");
                            //RobotMove =1;
                            //e.translatePoint(-10, 0);
                        }else {
                            System.out.println("left");
                            //RobotMove =2;
                            //e.translatePoint(+10, +10);
                        }
                    }else{                          //y
                        if (dy>0){
                            System.out.println("down");
                            //RobotMove =2;
                            //e.translatePoint(-10, -10);
                        }else {
                            System.out.println("up"); //ok
                        }
                    }
                    x = e.getX();
                    y = e.getY();
                    System.out.println(e.paramString());
                    System.out.println("x:" + x + " y:" + y);
                    /*if (isRobotMove==true) {
                        if (RobotMove!=0) {
                            RobotMove--;
                            x = e.getX();
                            y = e.getY();
                        }else isRobotMove=false;

                    }else {
                        isRobotMove=true;
                        r.mouseMove(e.getX(), e.getY());
                    }*/
                }
            });
            frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (!isChanged) {
                            frame.setCursor(cursor2);
                            isChanged = true;

                        } else {
                            frame.setCursor(cursor1);
                            isChanged = false;
                        }
                    } else {
                        System.exit(0);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

            frame.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                }
            });
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void setFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
