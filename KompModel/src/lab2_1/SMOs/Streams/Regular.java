package lab2_1.SMOs.Streams;

public class Regular extends Stream {

    public Regular(double lyamda) {
        super(lyamda);
    }

    @Override
    public double next() {
        return lyamda;
    }
}