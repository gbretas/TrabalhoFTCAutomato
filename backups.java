  // Metodo de exemplo para transformar um automato em um arquivo .jff
  static public void FromFlap(){
    JFLAPmanager jflap = new JFLAPmanager();
    try{
        Automato at = jflap.importAutomatoFromJflap("output/filename.jff");
        // at.isAFN();
        System.out.println(at.toString());
    }catch(Exception e){
        System.out.println("Erro ao importar automato");
    }
}

// Metodo de exemplo para transformar um arquivo .jff em um automato
static public void ToFlap(){
    Automato automato = new Automato();

    // set automato
    automato.addInitialState("q0");
    automato.addState("q0");
    automato.addState("q1");
    automato.addState("q2");

    automato.addFinalState("q2");

    automato.addAlphabet("a");
    automato.addAlphabet("b");

    automato.addTransition(new Transition("q0", "q1", "a"));
    automato.addTransition(new Transition("q1", "q2", "b"));
    automato.addTransition(new Transition("q2", "q0", "a"));
    automato.addTransition(new Transition("q2", "q0", ""));


    JFLAPmanager jflap = new JFLAPmanager();
    jflap.exportAutomatoToJflap(automato, "arquivo");
}
