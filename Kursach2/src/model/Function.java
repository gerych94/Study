package model;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Ruslan
 *
 */
public interface Function {
    static HashMap<String, Function> changes = new HashMap<String, Function>();
    public double getRezult();
    public Stack<Function> getVariable();
    void setName(String varName);
    String getName();
}
abstract class FunctionWithTwoPAram implements Function{
    Function arg1, arg2;
    String name;

    FunctionWithTwoPAram(Function arg1, Function arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    public Stack<Function> getVariable(){
        Stack<Function> buf = new Stack<Function>();
        buf.addAll(arg1.getVariable());
        buf.addAll(arg2.getVariable());
        return buf;
    }
    public void setName(String s){
        name = s;
    }
    public String getName(){
    	return name;}
}

abstract class OneParam implements Function{
    Function arg;
    String name;
    OneParam(Function arg){
        this.arg = arg;
    }
    public Stack<Function> getVariable(){
        Stack<Function> buf = new Stack<Function>();
        if (arg!=null)
        buf.addAll(arg.getVariable());
        return buf;
    }
    public void setName(String s){
        name = s;
    }
    public String getName(){return name;}

}

class Dob extends FunctionWithTwoPAram{
    Dob(Function arg1, Function arg2){
        super(arg1,arg2);
    }

    public double getRezult(){
        return arg1.getRezult()*arg2.getRezult();
    }
}
class Dil extends FunctionWithTwoPAram{
    Dil(Function arg1, Function arg2){
        super(arg1, arg2);
    }
    public double getRezult(){
        return arg1.getRezult()/arg2.getRezult();
    }
}
class Riz extends FunctionWithTwoPAram{
    Riz(Function arg1, Function arg2){
        super(arg1, arg2);
    }

    public double getRezult(){
        return arg1.getRezult()-arg2.getRezult();
    }
}
class Sum extends FunctionWithTwoPAram{
    Sum(Function arg1, Function arg2){
        super(arg1, arg2);
    }
    public double getRezult(){
        return arg1.getRezult()+arg2.getRezult();
    }
}
class Const implements Function{
    Number arg;
    Const(Number arg){
        this.arg = arg;
    }
    public double getRezult(){
        return arg.doubleValue();
    }


    @Override
    public Stack<Function> getVariable() {
        return new Stack<Function>();
    }
String name;
    @Override
    public void setName(String varName) {
        name = varName;
    }
    public String getName(){return name;}

}

class Pow extends FunctionWithTwoPAram{
    Pow(Function arg1, Function agr2){
        super(arg1,agr2);
    }
    @Override
    public double getRezult() {
        return Math.pow(arg1.getRezult(), arg2.getRezult());
    }
}
class Sin extends OneParam{
    Sin(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.sin(arg.getRezult());
    }


}
class Cos extends OneParam{

    Cos(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.cos(arg.getRezult());
    }

}
class Tg extends OneParam{


    Tg(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.tan(arg.getRezult());
    }

}
class cTg extends OneParam{


    cTg(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return 1/Math.tan(arg.getRezult());
    }

}
class Ln extends OneParam{

    Ln(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.log(arg.getRezult());
    }

}
class aSin extends OneParam{


    aSin(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.asin(arg.getRezult());
    }
}
class aCos extends OneParam{

    aCos(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.acos(arg.getRezult());
    }
}
class Exp extends OneParam{

    Exp(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.exp(arg.getRezult());
    }
}

class Pi extends OneParam{
    Pi() {
        super(null);
    }

    public  double getRezult(){
        return Math.PI;
    }
}

class Modul extends OneParam{
    Modul(Function arg){
        super(arg);
    }
    @Override
    public double getRezult() {
        return Math.abs(arg.getRezult());
    }
}