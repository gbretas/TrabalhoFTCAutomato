import java.util.*;


public class ConversorAFN {
    
    public Automato main(Automato at){
        System.out.println("======================");
        System.out.println("Conversão de AFN para AFD");
        System.out.println("");
        
        // Pegar o estado inicial
        // ArrayList<String> estadosFinais = at.getFinalStates();
        ArrayList<String> estados = at.getInitialState();
        String estadoInicial = estados.get(0);
        ArrayList<String> alfabeto = at.getAlphabet();



        Automato AFD = new Automato();
        AFD.setAlphabet(alfabeto);
        AFD.addInitialState(estadoInicial);


        ArrayList<String> pilhaEstados = new ArrayList<String>();
        ArrayList<String> pilhaAlready = new ArrayList<String>();
        pilhaEstados.add(estadoInicial);

        ArrayList<Transition> AFDTransitions = new ArrayList<Transition>();




        while(pilhaEstados.size() > 0){

            ArrayList<Transition> newTransitions = new ArrayList<Transition>();
            
            String estadoAtual = pilhaEstados.get(0);
            pilhaAlready.add(estadoAtual);
            pilhaEstados.remove(0);
            
            // System.out.println("Estado atual: " + estadoAtual);
            if(AFD.getState(estadoAtual) == false){
                AFD.addState(estadoAtual);
            }


            // if estado atual contains ;
            String[] allStates = estadoAtual.split(";");
            ArrayList<Transition> transitionsFromState = new ArrayList<Transition>();
            for (String state_1 : allStates) {
                transitionsFromState.addAll(at.getTransitionsFromState(state_1));
            }


            for (Transition transition : transitionsFromState) {
                String symbol = transition.getSymbol();
                String nextState = transition.getToState();

                // if symbol already exists in newTransitions concat nextState
                boolean symbolExists = false;
                for (Transition newTransition : newTransitions) {
                    if(newTransition.getSymbol().equals(symbol)){
                        symbolExists = true;
                        String newToState = newTransition.getToState() + ";" + nextState;

                        // explode ; and sort
                        String[] newToStateArray = newToState.split(";");
                        Arrays.sort(newToStateArray);

                        // remove all duplicates
                        Set<String> hs = new HashSet<>();
                        hs.addAll(Arrays.asList(newToStateArray));
                        newToStateArray = hs.toArray(new String[hs.size()]);


                        newToState = String.join(";", newToStateArray);

                        
                        newTransition.setToState(newToState);
                        nextState = newToState;

                        // System.out.println("Transição alterada: " + newTransition.getFromState() + " - " + newTransition.getSymbol() + " -> " + newTransition.getToState());

                    }
                }

                if(!symbolExists){
                    Transition newTransition = new Transition(estadoAtual, nextState, symbol);
                    newTransitions.add(newTransition);
                    // System.out.println("Nova transição: " + newTransition.getFromState() + " -> " + newTransition.getToState() + " : " + newTransition.getSymbol());
                }




                if(!pilhaAlready.contains(nextState)){
                    pilhaEstados.add(nextState);
                }
            }

            // System.out.println("Transições: " + newTransitions.toString());

            // add newTransitions to AFDTransitions
            for (Transition transition : newTransitions) {
                AFDTransitions.add(transition);
            }

           
        }



        // create all states from transitions

        AFD.setTransitions(AFDTransitions);

        //PASS IN ALL STATES
        for (String state : AFD.getStates()) {
            String actState = state;
            String[] actStateArray = actState.split(";");
            for (String stateInArray : actStateArray) {
                if(at.isFinalState(stateInArray)){
                    AFD.addFinalState(actState);
                    break;
                }
            }
        }


        // limpar transicoes duplicadas
        ArrayList<Transition> transitions = AFD.getTransitions();
        ArrayList<Transition> transitionsClean = new ArrayList<Transition>();
        for (Transition transition : transitions) {
            // check if transition already exists
            // fromState, toState, symbol
            boolean exists = false;
            for (Transition transitionClean : transitionsClean) {
                if(transitionClean.getFromState().equals(transition.getFromState()) && transitionClean.getToState().equals(transition.getToState()) && transitionClean.getSymbol().equals(transition.getSymbol())){
                    exists = true;
                }
            }

            if(!exists){
                transitionsClean.add(transition);
            }
          
        }

        AFD.setTransitions(transitionsClean);


        // System.out.println("Transições: " + AFD.toString());

        
        return AFD;
        
      
    }


}
