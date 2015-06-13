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
        cursor1 = toolkit.createCustomCursor(toolkit.getImage("kurs1.png"), new Point(0, 0), "cursor1");
        cursor2 = toolkit.createCustomCursor(toolkit.getImage("kurs3.png"), new Point(0, 0), "cursor2");
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
                    if (!isRobotMove) {
                        dx=e.getX()-x;
                        dy=e.getY()-y;
                        System.out.println("dx:" + dx + " dy:" + dy);
                        if (Math.abs(dx)>Math.abs(dy)){ //x
                            if (dx>0){
                                System.out.println("right");
                                e.translatePoint(0, -10);
                            }if (dx<0) {
                                System.out.println("left");
                                e.translatePoint(-10, -10);
                            }
                        }else{                          //y
                            if (dy>0){
                                System.out.println("down");
                                e.translatePoint(+10, +10);
                            }if (dy<0) {
                                System.out.println("up");
                                e.translatePoint(+10, -10);
                            }
                        }
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
        frame.setSize(500, 500);
        frame.setTitle("Коропатник. Лабораторна №3");
        frame.setVisible(true);
    }
}
