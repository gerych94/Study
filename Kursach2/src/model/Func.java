package model;

/**
 * клас який представляє функцію через функцію
 *
 */
public class Func extends OneParam {
    Func(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        if (arg!=null)
        return arg.getRezult(); else return 0;
    }

}
