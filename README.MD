Trabalho para a matéria Fundamentos de Teoria da Computação
Alunos: Gustavo Torres Bretas Alves e Maria Fernanda Guimarães

=======================================================================
## Como compilar

Para compilar, use "java AutomatosFTC.java"
E para executar: use "javac AutomatosFTC"

javac AutomatosFTC.java && java AutomatosFTC

## Como executar:

Tenha certeza que na pasta principal do projeto, existe uma pasta chamada "resources" e dentro dela, um arquivo chamado "AFN.jff"

e execute o comando;
```
java -cp build/Automato.jar AutomatosFTC AFN.jff
```

=======================================================================

## Exemplos de entradas e saídas

### Conversão de AFN para AFD

Entrada: java -cp Automato.jar AutomatosFTC AFN.jff
Saida: Conversão de AFN para AFD

Arquivo exportado com sucesso.
Arquivo salvo em resources/out_output.jff

### Simulação de AFD
Entrada: 
java -cp build/Automato.jar AutomatosFTC out_output.jff < entrada.txt

======================
Simulação do Automato

A palavra 'a' foi rejeitada
A palavra 'aa' foi aceita
A palavra 'aab' foi aceita
A palavra 'aaabbb' foi aceita
A palavra 'aaaabbbbb' foi aceita
A palavra 'abababab' foi rejeitada