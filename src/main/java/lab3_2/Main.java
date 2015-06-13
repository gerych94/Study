package lab3_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mpi.*;



public class Main {
    static Robot r;
    static JFrame frame;
    static Cursor cursor;
    static boolean isChanged = false;
    static  boolean  isRobotMove = false;
    static int x = -1;
    static int y = -1;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws AWTException {

        setFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("C://Users//Vitaliy//Study//src/lab3_2/cursor.GIF");
        Point hotSpot = new Point(0, 0);
        cursor = toolkit.createCustomCursor(image, hotSpot, "cursor");
        frame.setCursor(cursor);
        r = new Robot();
        try {
            frame.addMouseMotionListener(new MouseMotionListener() {
                Robot r = new Robot();

                @Override
                public void mouseDragged(MouseEvent e) {
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    //e.translatePoint(1, 1);
                    System.out.println(e.paramString());

                    if (!isRobotMove) {
                        System.out.println("1: "+e.getX() + " " + e.getY());
                        e.paramString();
                        e.translatePoint(-1, 5);
                        r.mouseMove(e.getX(),e.getY());
                        int dY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                        if ((y - dY) < 0){
                            if (MouseInfo.getPointerInfo().getLocation().getY() < 35){
                                //r.mouseMove((int) MouseInfo.getPointerInfo().getLocation().getX(),500);
                            }
                            y = dY + Math.abs(y - dY);
                            //r.mouseMove((int) MouseInfo.getPointerInfo().getLocation().getX(),y);
                            isRobotMove = true;
                        }
                        System.out.println(x + " " + y + "=====" + (int) MouseInfo.getPointerInfo().getLocation().getX() + " " + dY);
                    } else {
                        isRobotMove = false;
                        x = (int) MouseInfo.getPointerInfo().getLocation().getX();
                        y = (int) MouseInfo.getPointerInfo().getLocation().getY();
                    }
                }
            });
            frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (!isChanged) {
                            frame.setCursor(Cursor.DEFAULT_CURSOR);
                            isChanged = true;
                        } else {
                            frame.setCursor(cursor);
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
