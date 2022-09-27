import java.util.ArrayList;

public class Automato {

    ArrayList<String> initialStates = new ArrayList <String>();
    ArrayList<String> states = new ArrayList<String>();
    ArrayList<String> finalStates = new ArrayList<String>();
    ArrayList<String> alphabet = new ArrayList<String>();
    ArrayList<Transition> transitions = new ArrayList<Transition>();



    public Automato() {

    }


    // If is Non Deterministic Automaton
    public boolean isAFN(){
        boolean isAFN = false;

        // q0 -> x transicoes para a mesma entrada
        // q1 -> y transicoes
        ArrayList<String> states = this.getStates();
        for (String state : states) {
            ArrayList<Transition> transitions = this.getTransitionsFromState(state);
            ArrayList<String> alreadyHaveTransition = new ArrayList<String>();

            for (Transition transition : transitions) {
                String symbol = transition.getSymbol();
                if(alreadyHaveTransition.contains(symbol)){
                    isAFN = true;
                    return isAFN;
                }else{
                    alreadyHaveTransition.add(symbol);
                }
            }
        }

        return isAFN;
    }

    // Gets
    public ArrayList<String> getInitialState() {
        return initialStates;
    }
    public ArrayList<String> getStates() {
        return states;
    }
    public ArrayList<String> getFinalStates() {
        return finalStates;
    }
    public ArrayList<String> getAlphabet() {
        return alphabet;
    }
    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public boolean isFinalState(String state){
        boolean isFinal = false;

        for (String finalState : finalStates) {
            if(finalState.equals(state)){
                isFinal = true;
                break;
            }
        }

        return isFinal;
    }

    public ArrayList<Transition> getTransitionsFromState(String state_from) {
        ArrayList<Transition> transitions = new ArrayList<Transition>();
        for (Transition transition : this.transitions) {
            if (transition.getFromState().equals(state_from)) {
                transitions.add(transition);
            }
        }
        return transitions;   
    }

    public boolean transitionsFromStateIsDeterministic(String state_from){
        boolean isDeterministic = true;

        ArrayList<Transition> transitions = this.getTransitionsFromState(state_from);
        // System.out.println("Transitions: "+transitions.toString());
        ArrayList<String> alreadyHaveTransition = new ArrayList<String>();

        for (Transition transition : transitions) {
            String symbol = transition.getSymbol();
            // System.out.println("Symbol: "+symbol);
            if(alreadyHaveTransition.contains(symbol)){
                isDeterministic = false;
                break;
            }else{
                alreadyHaveTransition.add(symbol);
            }
        }

        return isDeterministic;
    }

    // Sets
    public void setInitialState(ArrayList<String> initialStates) {
        this.initialStates = initialStates;
    }
    public void setStates(ArrayList<String> states) {
        this.states = states;
    }
    public void setFinalStates(ArrayList<String> finalStates) {
        this.finalStates = finalStates;
    }
    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }
    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    // Add
    public void addInitialState(String initialState) {
        this.initialStates.add(initialState);
    }
    public void addState(String state) {
        this.states.add(state);
    }
    public void addFinalState(String finalState) {
        this.finalStates.add(finalState);
    }
    public void addAlphabet(String symbol) {
        this.alphabet.add(symbol);
    }
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    // Remove
    public void removeInitialState(String initialState) {
        this.initialStates.remove(initialState);
    }
    public void removeState(String state) {
        this.states.remove(state);
    }
    public void removeFinalState(String finalState) {
        this.finalStates.remove(finalState);
    }
    public void removeAlphabet(String symbol) {
        this.alphabet.remove(symbol);
    }
    public void removeTransition(Transition transition) {
        this.transitions.remove(transition);
    }

    public void loadAlphabet(){
        for (Transition transition : this.transitions) {
            String symbol = transition.getSymbol();
            if(!this.alphabet.contains(symbol)){
                this.alphabet.add(symbol);
            }
        }
    }

    public String toString() {
        String automato = "Automato: \n";
        automato += "Initial State: " + initialStates + "\n";
        automato += "States: " + states + "\n";
        automato += "Final States: " + finalStates + "\n";
        automato += "Alphabet: " + alphabet + "\n";
        automato += "Transitions: " + transitions + "\n";
        return automato;
    }



}
