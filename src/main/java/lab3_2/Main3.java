package lab3_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main3 {
    static Robot r;
    static JFrame frame;
    static Cursor cursor1;
    static Cursor cursor2;
    static boolean isChanged = false;
    static  boolean  isRobotMove = false;
    static int x = -1;
    static int y = -1;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws AWTException {
        setFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        cursor1 = toolkit.createCustomCursor(toolkit.getImage("C://Users//Vitaliy//Study//src/lab3_2/Bezymyanny2.png"), new Point(0, 0), "cursor1");
        cursor2 = toolkit.createCustomCursor(toolkit.getImage("C://Users//Vitaliy//Study//src/lab3_2/Procent-teken.png"), new Point(0, 0), "cursor2");
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
                    System.out.println(e.paramString());
                    int dx;
                    int dy;
                    System.out.println("x:" + x + " y:" + y);
                    System.out.println("frame x:" + frame.getLocation().getX() + " y:" + frame.getLocation().getY());
                    if(x>frame.getWidth()-20){
                        r.mouseMove(20,y);
                    }
                    if(y>frame.getHeight()-20){
                        r.mouseMove(50,x);
                    }
                    if(x<20){
                        r.mouseMove(frame.getWidth()-50,y);
                    }
                    if(y<40){
                        r.mouseMove(x,frame.getHeight()-50);
                    }


                    if (!isRobotMove) {
                        dx=e.getX()-x;
                        dy=e.getY()-y;
                      /*  if(e.getX()==500){
                            r.mouseMove(0,frame.getY()+e.getY());
                        }
                        if(e.getY()==500){
                            r.mouseMove(e.getX()+frame.getX(),0);
                        }*/


                        System.out.println("dx:" + dx + " dy:" + dy);
                        if (Math.abs(dx)>Math.abs(dy)){ //x

                            if (dx>0){
                                System.out.println("right");
                                e.translatePoint(0, +10);
                            }if (dx<0) {
                                System.out.println("left");
                                e.translatePoint(-10, +10);
                            }


                        }else{                          //y

                            if (dy>0){
                                System.out.println("down");
                                e.translatePoint(0, -10);
                            }if (dy<0) {
                                System.out.println("up");
                                e.translatePoint(-10, 0);
                            }
                        }
                      /*  */

                        isRobotMove = true;
                        r.mouseMove(e.getX()+frame.getX(), e.getY()+frame.getY());
                    } else {
                        isRobotMove = false;
                        x = e.getX();
                        y = e.getY();
                    }
                }
            });
            frame.addMouseListener(new MouseListener() {
                int prevX=0;
                int prevY=0;
                int count=0;

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                          count++;
                        //if(count%2==0){
                         r.mouseMove(prevX,prevY);
                            prevX=e.getX();
                            prevY=e.getY();
                        //}

                       // frame.setSize(e.getX(),e.getY());

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
                    x = e.getX();
                    y = e.getY();
                    System.out.println("in x:" + x + " y:" + y);
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
        frame.setSize(700, 700);
        frame.setTitle("Драйвер миші");
        frame.setVisible(true);
    }
}
