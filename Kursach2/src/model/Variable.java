package model;

import java.util.Stack;

/**
 * Клас який представляє змінну
 */

public class Variable extends FunctionWithTwoPAram{
    private double h,xmin,xmax;
    private boolean fl;
    private String key;

    Variable(Function arg1, Function arg2,String key){
        super(arg1,arg2);
        this.key = key;
        if (Function.changes.size()>3)
        h  = (arg2.getRezult() - arg1.getRezult())/100+0.000001; 
        else
        h  = (arg2.getRezult() - arg1.getRezult())/200+0.000001;
        xmin = arg1.getRezult();
        xmax = arg2.getRezult();
    }

    @Override
    public double getRezult(){
        return xmin;
    }

    @Override
    public Stack<Function> getVariable() {
        Stack<Function> stack = new Stack<Function>();
        stack.add(this);
        return stack;
    }

    /**
     * метод котрий інкрементує значення змінної
     */
    public void next(){
        xmin+=h;
        if (h>0)
            if (xmin>xmax) fl = true;
        if (h<0)
            if (xmin<xmax) fl = true;
    }

    /**
     *  метод котрий онуляє змінну
     */
    public void restart(){
        xmin = arg1.getRezult();
        fl = false;
    }

    /**
     * Метод котрий поертає true, якщо значення змінної в межах визначеності
     * @return - визначеність функції
     */
    public boolean hasNext() {
            return !fl;
    }

}