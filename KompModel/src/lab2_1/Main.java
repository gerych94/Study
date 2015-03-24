package lab2_1;

import lab2_1.SMOs.*;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Queue queue1 = new Queue();
        Queue queue2 = queue1.clone();
        Queue queue3 = queue1.clone();

        model(queue1, new LIFO());

        model(queue2, new FB());

        model(queue3, new PER());


    }


    public static void model(Queue queue, SMO s){

        s.model(queue);

        System.out.println("" + s.getType());
        System.out.println("not processing task: "+SMO.count_not_processing_task);
//        System.out.println(SMO.getCurrent_time());
        System.out.printf("average time in system is X1= %1.5f\n", queue.getAverage_time_in_system());
        System.out.printf("average square time in system is X2= %1.5f\n", queue.getAverage_square_time_in_system());
        System.out.printf("ping is X3= %1.5f\n", queue.getPing());
        System.out.printf("task not processing X4= %1.5f\n", queue.getRatio());
        System.out.printf("total score relevance X5= %1.5f\n", queue.getSumMark());
        double x1 = queue.getAverage_time_in_system();
        double x2 = queue.getAverage_square_time_in_system();
        double x3 = queue.getPing();
        double x4 = queue.getRatio();
        double x5 =  queue.getSumMark();

        System.out.println("F = " + (Const.k1 * x1 + Const.k2 * x2 + Const.k3 * x3 + Const.k4 * x4 + Const.k5 * x5));

        SMO.clear();
        System.out.println();

    }
}

