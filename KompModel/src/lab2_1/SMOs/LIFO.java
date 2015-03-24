package lab2_1.SMOs;
import java.util.LinkedList;

public class LIFO extends SMO {



    @Override
    public void model(Queue queue) {

        LinkedList<Problem> tasks =  new LinkedList<Problem>();

        for (int i = 0; i<queue.getProblems().length; i++){
            if (SMO.getCurrent_time() >= queue.getProblems()[i].arrival_time) {
                tasks.add(queue.getProblems()[i]);
                continue;
            }
            if (tasks.size() > 0){
                i--;
                Problem problem = tasks.getLast();
                tasks.remove(problem);
                problem.setFirs(SMO.getCurrent_time());
                double time = Math.min(problem.arrival_time + Const.t1 + Const.t2, SMO.getCurrent_time() + problem.treatment_time);
                if (SMO.getCurrent_time() < time){
                    problem.processing_time = time - SMO.getCurrent_time();
                    SMO.setCurrent_time(time);
                    problem.setEnd_Time(SMO.getCurrent_time());
                    if (time == problem.arrival_time + Const.t1 + Const.t2) SMO.count_not_processing_task ++;
                } else {
                    SMO.count_not_processing_task++;
                    problem.setEnd_Time(SMO.getCurrent_time());
                }

            } else  {
                SMO.setCurrent_time(SMO.getCurrent_time() + Const.quantum);
                i--;
            }


            }
        while (tasks.size()>0){
            Problem problem = tasks.getLast();
            tasks.remove(problem);
            problem.setFirs(SMO.getCurrent_time());
            double time = Math.min(problem.arrival_time + Const.t1 + Const.t2, SMO.getCurrent_time() + problem.treatment_time);
            if (SMO.getCurrent_time() < time){
                problem.processing_time = time - SMO.getCurrent_time();
                SMO.setCurrent_time(time);
                problem.setEnd_Time(SMO.getCurrent_time());
                if (time == problem.arrival_time + Const.t1 + Const.t2) SMO.count_not_processing_task ++;
            } else {
                SMO.count_not_processing_task++;
                problem.setEnd_Time(SMO.getCurrent_time());
            }

        }


        /*for (Problem problem:queue.getProblems()){
            if (getCurrent_time() < problem.arrival_time) SMO.setCurrent_time(problem.arrival_time);
            problem.setFirs(SMO.getCurrent_time());
            double time = Math.min(problem.arrival_time + Const.t1 + Const.t2, SMO.getCurrent_time() + problem.treatment_time);
            if (SMO.getCurrent_time() < time){
                problem.processing_time = time - SMO.getCurrent_time();
                SMO.setCurrent_time(time);
                problem.setEnd_Time(SMO.getCurrent_time());
                if (time == problem.arrival_time + Const.t1 + Const.t2) SMO.count_not_processing_task ++;
            }
            else {
                SMO.count_not_processing_task++;
                problem.setEnd_Time(SMO.getCurrent_time());
            }
        }*/
    }

    @Override
    public String getType() {
        return "LIFO";
    }
}