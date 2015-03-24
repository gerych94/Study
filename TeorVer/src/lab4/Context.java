package lab4;

public class Context {
	private Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public double hita() {
        return strategy.hita();
    }
}
