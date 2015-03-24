package model;

import javax.swing.*;

/**
 * Клас що оповіщає про помилки, введеня функції
 */
public class IncorrectFunction extends Exception {
    private int position;
    private String s;
    private String message;
    public IncorrectFunction(int position, String s){
        this(position, s,"Помилка введення функції, символ:"+position);
    }

    public IncorrectFunction(int position, String s, String message){
        this.position = position;
        this.s = s;
        this.message = message;
    }


    /**
     * метод який створює діалогове вікно з помилкою
     */
    public void showException(){
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(jf,message,s,JOptionPane.CLOSED_OPTION);
    }


}
