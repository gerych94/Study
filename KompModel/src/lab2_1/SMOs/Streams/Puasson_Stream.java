package lab2_1.SMOs.Streams;

public class Puasson_Stream extends Stream {

    public Puasson_Stream(double lyamda){
        super(lyamda);
    }

    public double next(){
        return -1/lyamda*Math.log(random.nextDouble());
    }

}