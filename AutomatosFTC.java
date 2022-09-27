import java.util.*;
import java.io.*;
class AutomatosFTC {
    public static Scanner scanner = new Scanner(System.in);

    public static String Digita(String mensagem) throws Exception {
        System.out.print(mensagem);
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        String entradaDoTeclado = buff.readLine();

        // buff.close();
        return entradaDoTeclado;
    }

    public static void main(String[] args) {
        // ToFlap(); // Metodo de exemplo para transformar um automato em um arquivo .jff
        // FromFlap(); // Metodo de exemplo para transformar um arquivo .jff em um automato
        // JFLAPmanager jflap = new JFLAPmanager();
        menu();
    }

    public static void menu(){
        // System.out.println("1 - Transformar um automato em um arquivo .jff");
        System.out.println("1 - Ler um automato em formato .jff");
        System.out.println("5 - Sair");

        System.out.print("Digite a opção desejada: ");

        // int option = scanner.nextInt();
        int option = 1;

        switch (option) {
            case 1:
                readFromJflapFile();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }


    }



    static public void readFromJflapFile(){
        JFLAPmanager jflap = new JFLAPmanager();

        // digite o nome do arquivo, ele deve estar na pasta resources
        System.out.println("O arquivo deve estar na pasta resources");
        
        
        try{
            // String filename = Digita("Digite o nome do arquivo: ");
            String filename = "mod3.jff";
            File file = new File("resources/"+filename+"");

            System.out.println("Arquivo encontrado: "+file.getAbsolutePath());

            // Checar se o arquivo existe
            if (file.exists()) {
                try{

                    Automato at = jflap.importAutomatoFromJflap("resources/"+filename);
                    at.loadAlphabet();

                    if (at.isAFN()) {
                        System.out.println("É AFN");
                        // Transformar em AFD
                    }else{

                        System.out.println("Digite 'fim' para parar de digitar palavras");
                        ArrayList<String> palavras = new ArrayList<String>();
                        String tmpPalavra = Digita("Digite uma palavra: ");
                        while(!tmpPalavra.equals("fim")){
                            palavras.add(tmpPalavra);
                            tmpPalavra = Digita("Digite uma palavra: ");
                        }


                        SimuladorAFD simulador = new SimuladorAFD();
                        simulador.main(at, palavras);
                    }
                    


                }catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("Erro ao importar o arquivo");
                }
    
            } else {
                System.out.println("Arquivo não encontrado");
            }
    
    

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Erro ao ler o nome do arquivo");
            
        }


      


    }

  
}