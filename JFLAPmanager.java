import java.io.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
  
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
  
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
  


public class JFLAPmanager {

    // Função auxiliar para exportar um automato para um arquivo .jff
    public void exportAutomatoToJflap(Automato automato, String filename){
        // export automato to jflap
        try {
            //create folder output if not exists
            File folder = new File("output");
            if (!folder.exists()) {
                folder.mkdir();
            }

            FileWriter myWriter = new FileWriter("output/"+filename+".jff");
            myWriter.write(transformAutomatoToJflap(automato));
            myWriter.close();
            System.out.println("Arquivo exportado com sucesso.");
        } catch (IOException e) {
            System.out.println("Algum erro aconteceu.");
            e.printStackTrace();
        }
    }

    // Função principal para exportar um automato para um arquivo xml do jflap
    public String transformAutomatoToJflap(Automato automato){
        String jflap = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r";
        jflap += "<structure>\r";
        jflap += "\t<type>fa</type>\r";
        jflap += "\t<automaton>\r";

        // Lista de states
        jflap += "\t\t<!-- The list of states. -->\r";
        int idState = 0;
        for (String state : automato.getStates()) {
            jflap += "\t\t<state id=\"" + idState + "\" name=\"" + state + "\">\r";
            if (automato.getInitialState().contains(state)) {
                jflap += "\t\t\t<initial/>\r";
            }
            if (automato.getFinalStates().contains(state)) {
                jflap += "\t\t\t<final/>\r";
            }
            jflap += "\t\t</state>\r";
            idState++;
        }

        // Transições
        jflap += "\t\t<!-- The list of transitions. -->\r";
        for (Transition transition : automato.getTransitions()) {
            jflap += "\t\t<transition>\r";
            jflap += "\t\t\t<from>" + automato.getStates().indexOf(transition.getFromState()) + "</from>\r";
            jflap += "\t\t\t<to>" + automato.getStates().indexOf(transition.getToState()) + "</to>\r";

            if (transition.getSymbol() == ""){
                jflap += "\t\t\t<read/>\r";
            } else {
                jflap += "\t\t\t<read>" + transition.getSymbol() + "</read>\r";
            }

            jflap += "\t\t</transition>\r";
        }

        jflap += "\t</automaton>\r";
        jflap += "</structure>\r";

        return jflap;



    }

    // Função principal para importar um automato de um arquivo xml do jflap
    public Automato importAutomatoFromJflap(String path) throws ParserConfigurationException, SAXException, IOException {

        // Document builder para ler o arquivo xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

       // Construindo o documento
        Document document = builder.parse(new File(path));

        // Normalizando o documento
        document.getDocumentElement().normalize();

        // Pegando a tag raiz
        // Element root = document.getDocumentElement();

        // Inicializando as variaveis do automato
        ArrayList<String> states = new ArrayList<String>();
        ArrayList<String> initialStates = new ArrayList<String>();
        ArrayList<String> finalStates = new ArrayList<String>();
        ArrayList<String> alphabet = new ArrayList<String>();
        
        // Pegando a lista de states
        NodeList nList = document.getElementsByTagName("state");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                Boolean initial = eElement.getElementsByTagName("initial").getLength() > 0;
                Boolean finalState = eElement.getElementsByTagName("final").getLength() > 0;
                
                // Adicionando o state na lista de states
                states.add(eElement.getAttribute("name"));
                // Verificando se o estado é inicial
                if (initial) {
                    initialStates.add(eElement.getAttribute("name"));
                }

                // Verificando se o estado é final
                if (finalState) {
                    finalStates.add(eElement.getAttribute("name"));
                }

               
            }
        }

        // Pegando a lista de transições
        nList = document.getElementsByTagName("transition");
        ArrayList<Transition> transitions = new ArrayList<Transition>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String fromState = states.get(Integer.parseInt(eElement.getElementsByTagName("from").item(0).getTextContent()));
                String toState = states.get(Integer.parseInt(eElement.getElementsByTagName("to").item(0).getTextContent()));
                String symbol = eElement.getElementsByTagName("read").item(0).getTextContent();

                //Verificar se estar no alfabeto, se não estiver, adicionar
                if (!alphabet.contains(symbol)) {
                    alphabet.add(symbol);
                }

                transitions.add(new Transition(fromState, toState, symbol));
            }
        }


        // Criando o automato
        Automato automato = new Automato();

        automato.setStates(states); // Adicionando os states
        automato.setInitialState(initialStates); // Adicionando o estado inicial
        automato.setFinalStates(finalStates); // Adicionando os estados finais
        automato.setTransitions(transitions); // Adicionando as transições

        return automato;
    }


}
