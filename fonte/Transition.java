public class Transition {

    String fromState;
    String toState;
    String symbol;

    public Transition(String fromState, String toState, String symbol) {
        this.fromState = fromState;
        this.toState = toState;
        this.symbol = symbol;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Transition [fromState=" + fromState + ", toState=" + toState + ", symbol=" + symbol + "]\n";
    }

    
}
