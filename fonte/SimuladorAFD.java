// import java.io.*;
import java.util.*;
public class SimuladorAFD {

    public void main(Automato at, ArrayList<String> palavras) {
        // at.loadAlphabet();

        System.out.println("======================");
        System.out.println("Simulação do Automato");
        System.out.println("");

        // System.out.println(at.toString());
        if(at.isAFN()){
            System.out.println("Essa função não aceita AFN");
            return;
        }

        // Para cada palavra simular o automato e mostrar o resultado

        for (String palavra : palavras) {
            boolean simulacao = simular(at, palavra);
            if(simulacao){
                System.out.println("A palavra '"+palavra+"' foi aceita");
            }else{
                System.out.println("A palavra '"+palavra+"' foi rejeitada");
            }
            // System.out.println("Resultado: "+simular(at, palavra));
        }


    }

    public boolean simular(Automato at, String palavra){
        boolean accept = false;

        // Pegar o estado inicial
        ArrayList<String> estados = at.getInitialState();
        String actualState = estados.get(0);

        // Para cada símbolo da palavra
        for (int i = 0; i < palavra.length(); i++) {
            String symbol = palavra.substring(i, i+1);
            // System.out.println("Symbol: "+symbol);

            // verificar se o símbolo está no alfabeto
            if(!at.getAlphabet().contains(symbol)){
                System.out.println("O símbolo '"+symbol+"' não pertence ao alfabeto");
                return false;
            }

            // Pegar as transições do estado atual
            ArrayList<Transition> transitions = at.getTransitionsFromState(actualState);

            boolean errorState = true;

            for (Transition transition : transitions) {
                // System.out.println("Transition: "+transition.toString());
                if(transition.getSymbol().equals(symbol)){
                    actualState = transition.getToState();
                    errorState = false;
                    // System.out.println("Actual State: "+actualState);
                }
            }

            if(errorState){
                return false;
            }

        }

        // Verificar se o estado atual é final
        if(at.isFinalState(actualState)){
            accept = true;
        }

        return accept;
    }

    
}
