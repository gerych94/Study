package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Клас котрий перетворює рядкове предсавлення функції в рекурсивну функцію
 *
 */
public class Parser {
    private boolean fl = false;
    private boolean fl2 = false;
    private boolean fl3 = false;
    static int key = 0;
    private String varName;
    static Stack<Function> stack = new Stack<Function>();

    public Parser(boolean fl){
        if (fl)
        key = 0;
        this.fl = fl;
    }

    Parser(boolean fl, boolean fl2){
        this.fl = fl;
        this.fl2 = fl2;
    }

    Parser(){
        fl3 = true;
    }
    public Parser(boolean fl, String name){
        this(fl);
        varName = name;
        key = 0;
    }

    /**
     * Повертає імя змінної
     * @return - імя змінної
     */
    public String getKey() {
        return varName;
    }

    /**
     * повертає функцію
     * @param func - функція в рядковому предсавленні
     * @return - функція в обєктному представленні
     */
    public Function getFunction(String func){

         if (func.contains("..")){
             String[] mas  = new String[2];
             int l = func.indexOf("..");
             mas[0] = func.substring(0,l);
             mas[1] = func.substring(l+2,func.length());
             stack.add(new Variable(new Parser(true).getFunction(mas[0]),new Parser(true).getFunction(mas[1]),getKey()));
         } else{
             boolean f = true;
       st: while ((f||fl2)&&key<func.length()){
            f = fl;
        if (key<func.length()&&func.charAt(key)>='0'&&func.charAt(key)<='9'){
          stack.add(Parser.getConst(func));
            if (fl3) break;
        }
        if (key<func.length()&&func.charAt(key)=='*'){
            key++;
            stack.add(new Dob(stack.pop(), new Parser().getFunction(func)));
            continue ;
        }

         if (key<func.length()&&func.charAt(key)=='+'){
             key++;
             stack.add(new Sum(stack.pop(), new Parser(false).getFunction(func)));
             continue ;
         }

         if (key<func.length()&&func.charAt(key)=='/'){
             key++;
             stack.add(new Dil(stack.pop(), new Parser().getFunction(func)));
             continue ;
         }

         if (key<func.length()&&func.charAt(key)=='-'){
             key++;
             stack.add(new Riz(stack.pop(), new Parser(false).getFunction(func)));
             continue ;
         }

         if (key<func.length()&&func.charAt(key)=='^'){
             key++;
             stack.add(new Pow(stack.pop(),new Parser().getFunction(func)));
             continue ;
         }

         if (key<func.length()&&func.startsWith("sin",key)){
             key+=3;
             stack.add(new Sin(new Parser().getFunction(func)));
             continue ;
         }
           if (key<func.length()&&func.startsWith("cos",key)){
               key+=3;
               stack.add(new Cos(new Parser().getFunction(func)));
               continue ;
           }

           if (key<func.length()&&func.startsWith("tg",key)){
               key+=2;
               stack.add(new Tg(new Parser().getFunction(func)));
               continue ;
           }

           if (key<func.length()&&func.startsWith("ctg",key)){
               key+=3;
               stack.add(new cTg(new Parser().getFunction(func)));
               continue ;
           }

           if (key<func.length()&&func.startsWith("ln",key)){
               key+=2;
               stack.add(new Ln(new Parser().getFunction(func)));
               continue ;
           }
           if (key<func.length()&&func.startsWith("acos",key)){
               key+=4;
               stack.add(new aCos(new Parser().getFunction(func)));
               continue ;
           }
           if (key<func.length()&&func.startsWith("asin",key)){
               key+=4;
               stack.add(new aSin(new Parser().getFunction(func)));
               continue ;
           }
           if (key<func.length()&&func.startsWith("pi",key)){
               key+=2;
               stack.add(new Pi());
               continue ;
           }

           if (key<func.length()&&func.startsWith("exp",key)){
               key+=3;
               stack.add(new Exp(new Parser().getFunction(func)));
               continue ;
           }

         if (key<func.length()&&func.charAt(key)=='('){
             key++;
             stack.add(new Parser(false,true).getFunction(func));
             continue ;
         }

           if (key<func.length()&&func.charAt(key)=='|'){
               key++;
               stack.add(new Modul(new Parser().getFunction(func)));
               continue ;
           }

         if (key<func.length()&&func.charAt(key)==')'){
             if (fl2)
             key++;
             fl2 = false;
             continue ;
         }

         for(String s:Function.changes.keySet()){
             if (key<func.length()&&func.startsWith(s,key)){
                 key+=s.length();
                 stack.add(new Func(Function.changes.get(s)));
                 continue st;
             }
         }
           break ;
        }

         }
        if (fl&&(varName!=null)) {
            Function function  = stack.pop();
            function.setName(varName);
            Function.changes.put(varName,function);
            return function;
        }
        return stack.pop();
    }


    /**
     * повертає функцію константи
     * @param func - рядкове представлення функції
     * @return - обєкт константи
     */
    static Function getConst(String func){
        StringBuilder buf = new StringBuilder();
        while ((func.charAt(key)>='0'&&func.charAt(key)<='9'||func.charAt(key)=='.'||func.charAt(key)==',')){
            buf.append(func.charAt(key++));
            if (key==func.length()) break;
        }
        return new Const(Double.parseDouble(buf.toString()));
    }


    /**
     * метод котрий перевіряє на коректність рядкове представлення функції
     * @param s - функція
     * @return - true, якщо функція коректна
     * @throws model.IncorrectFunction - якщо функція мість недопустимі символи
     */
    public static String correct(String s) throws IncorrectFunction {
        StringBuffer buf = new StringBuffer(s);
        testBrackets(buf);
        testVariable(buf);
        if (buf.length()==0) throw new IncorrectFunction(0,s,"Порожний рядок");
        int k;

        for (int i = 1; i<buf.length(); i++){
            if ((buf.charAt(i-1)<'0'||buf.charAt(i-1)=='^'&&!(buf.charAt(i-1)=='.'&&buf.charAt(i)=='.'))&&buf.charAt(i-1)!='('&&buf.charAt(i-1)!=')'&&(buf.charAt(i)<'0'||buf.charAt(i)=='^')&&buf.charAt(i)!='('&&buf.charAt(i)!=')')
            if (buf.charAt(i-1)!='.'&&buf.charAt(i)!='.')
            {
                buf.insert(i++,"(");
                int l = i;
                while (l<buf.length()&&(buf.charAt(l)<'0'||buf.charAt(l)=='^')) l++;
                while (l<buf.length()&&buf.charAt(l)>='0'&&buf.charAt(l)!='^') l++;
                buf.insert(l,")");
            }
        }
        for(int i  = 0; i<buf.length(); i++){
            if (buf.charAt(i)=='^'){
                k= 0;
                int l = i-1;
                while(l>0&&(buf.charAt(l)!='+'&&buf.charAt(l)!='-'&&buf.charAt(l)!='*'&&buf.charAt(l)!='/')||k!=0){
                    if (buf.charAt(l)==')') k++;
                    if (buf.charAt(l)=='(') k--;
                    l--;
                    if (l==0) break;
                }
                if (l>0)
                    buf.insert(l+1, "("); else
                    buf.insert(0, "(");
                i++;
                k = 0;
                l = i+1;
                while(l<buf.length()&&(buf.charAt(l)!='+'&&buf.charAt(l)!='-'&&buf.charAt(l)!='*'&&buf.charAt(l)!='/'&&buf.charAt(l)!='^')||k!=0){
                    if (buf.charAt(l)=='(') k++;
                    if (buf.charAt(l)==')') k--;
                    l++;
                    if (l==buf.length()) break;
                }
                buf.insert(l,")");
                i++;
            }
        }
        for (int i = 0; i<buf.length(); i++){
            if ((buf.charAt(i)=='-'||buf.charAt(i)=='+')&&(i==0||!(buf.charAt(i-1)==')'||isVar(i-1,buf)||(buf.charAt(i-1)>='0'&&buf.charAt(i-1)<='9'))))
            {
                buf.insert(i, "0");
                i++;
            }
        }
        return buf.toString();
    }

    /**
     * перевіряє чи рядок є змінною
     * @param i - індекс рядку
     * @param buf - рядок
     * @return - true, якщо рядок є змінною
     */
    private static boolean isVar(int i, StringBuffer buf) {
        Set<String> stack = Function.changes.keySet();
        for(String s:stack){
            if (buf.lastIndexOf(s,i)==i-s.length()&&i-s.length()>0) return true;
        }
        return false;
    }

    private static void testVariable(StringBuffer buf) throws IncorrectFunction{
        int l = 0;
        String [] mas = {"atg","actg","acos","asin",".","+","-","/","*","(",")","sin","cos","tg","ctg","exp","ln","|","^","pi"};
        String ss = buf.toString();
        buf = new StringBuffer(buf);
        st:while (true){
            for(String s:mas)
            if (buf.indexOf(s)>=0){
                buf.replace(buf.indexOf(s),buf.indexOf(s)+s.length(),"");
                continue st;
            }
            break ;
        }

            Set<String> set = Function.changes.keySet();
        st: while (true){
            for (String s:set)
                if (buf.indexOf(s)>=0){
                    buf.replace(buf.indexOf(s),buf.indexOf(s)+s.length(),"");
                    continue st;
                }
        break ;
        }
        st: while (true){
            for (char s = '0'; s<='9'; s++){
                if (buf.indexOf(s+"")>=0){
                    buf.replace(buf.indexOf(s+""),buf.indexOf(s+"")+1,"");
                    continue st;
                }

        }
            break ;
        }
        if (buf.length()>0)
            throw new IncorrectFunction(0,ss);

    }

    private static void testBrackets(StringBuffer buf) throws IncorrectFunction{
        int k = 0;
        for(int i = 0; i<buf.length();  i++){
            if (buf.charAt(i)=='[') buf.setCharAt(i,'(');
            if (buf.charAt(i)==']') buf.setCharAt(i,')');
            if (buf.charAt(i)=='(') k++;
            if (buf.charAt(i)==')') k--;
        }
        if (k!=0) throw new IncorrectFunction(0,buf.toString(),"Різна кількість відкритих закритих дужок");
    }

    public static void main(String[] args) throws IOException, IncorrectFunction {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner scaner=new Scanner(System.in);
        String s;
        System.out.println("Input a");
        double a=scaner.nextInt();
        System.out.println("Input b");
        double b=scaner.nextInt();
        double integ=0;
        int n=300;
        double h=(b-a)/n;
    	double S=0;
    	double  x=a+h/2;
    	Parser parser;
    	parser = new Parser(true,"x");
    	Const constx = new Const(x);
    	//Variable var = new Variable(constx, new Const(2),"x");
    	Function.changes.put("x",constx);
        
        while  (!(s=in.readLine()).endsWith("exit")){
            try {
            	
            	s = Parser.correct(s); 
        		Function func = parser.getFunction(s);
        		for (int i = 0; i < n; i++) {       
               S+=func.getRezult();
               x+=h;
               constx.arg = x;
        		}
        		integ=h*S;
        		System.out.println(integ);
            } catch (IncorrectFunction incorrectFunction) {
                incorrectFunction.showException();
            }
            
        }
    }
}