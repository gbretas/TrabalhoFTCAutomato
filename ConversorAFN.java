import java.util.*;


public class ConversorAFN {
    
    public void main(Automato at){
        System.out.println("======================");
        System.out.println("Conversão de AFN para AFD");
        System.out.println("");
        
        // Pegar o estado inicial
        ArrayList<String> estados = at.getInitialState();
        String estadoInicial = estados.get(0);
        // Pegar os estados finais
        ArrayList<String> estadosFinais = at.getFinalStates();
        // Pegar o alfabeto
        ArrayList<String> alfabeto = at.getAlphabet();
        // Pegar as transições
        ArrayList<Transition> transicoes;
        // Criar o novo automato
        Automato novoAutomato = new Automato();
        // Adicionar o estado inicial
        novoAutomato.addInitialState(estadoInicial);

        
        boolean isDeterministicInitial = at.transitionsFromStateIsDeterministic(estadoInicial);
        if(!isDeterministicInitial){
            // converter o estado inicial em deterministico
        }else{
            // adicionar o estado inicial ao novo automato
            novoAutomato.addState(estadoInicial);            
          
        }
    
 

        
      
    }


}
