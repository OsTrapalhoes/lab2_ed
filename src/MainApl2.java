//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

//INTEGRANTES:
//Matheus Medeiros, RA: 10748040
//Pedro Botelho, RA: 10738317
//Daniel Arais, RA: 10419718

// Referências consultadas:
// - Goodrich, M. T.; Tamassia, R.; Goldwasser, M. H. Data Structures and Algorithms in Java. 6. ed. Wiley, 2014.
// - Deitel, P.; Deitel, H. Java: Como Programar. 10. ed. Pearson, 2017.
// - Oracle. Java SE 17 Documentation. Disponível em: https://docs.oracle.com/en/java/docs/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;

public class MainApl2 {

    public static void main(String[] args) {

        // ---------------------------------------------------------------
        // Código original do Apêndice B (comentado conforme instrução do
        // enunciado ao implementar o menu de opções)
        // ---------------------------------------------------------------

        LinkedListOriginal list = new LinkedListOriginal();
        txtFileConsumer(list);

        System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
        System.out.println(list);
        System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");

        DLinkedList fixedList = Operation.map(list);
        System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
        System.out.println(fixedList);
        System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");

        DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
        System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
        System.out.println(filteredGradedList);
        System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");

        DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
        System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
        System.out.println(filteredNonGradedList);
        System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

        float average = Operation.reduce(filteredGradedList);
        System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
        System.out.println(average);
        System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");

        DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
        System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
        System.out.println(aboveAverageList);
        System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");

        String contents = Operation.mapToString(fixedList);
        System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
        System.out.println(contents);
        System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");

        csvFileWriter(contents);

        Node test1 = fixedList.getNode("23.S1-999");
        System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

        Node test2 = fixedList.removeNode("23.S1-999");
        System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

        Node test3 = fixedList.getNode("23.S1-999");
        System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

        aboveAverageList.clear();
        System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList
                + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

        DLinkedList testList = new DLinkedList();
        testList.insert("ABC", "John Doe", 4.7f);
        testList.append("XYZ", "Jane Doe", 9.9f);
        testList.insert("321", "Test", 2.3f);
        testList.append("Nothing", "Yada yada yada", 99.9f);
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        System.out.println("testList.getHead(): " + testList.getHead());
        System.out.println("testList.getTail(): " + testList.getTail());
        System.out.println("testList.removeHead(): " + testList.removeHead());
        System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        System.out.println("testList.getHead(): " + testList.getHead());
        System.out.println("testList.getTail(): " + testList.getTail());
        System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        System.out.println("testList.getHead(): " + testList.getHead());
        System.out.println("testList.getTail(): " + testList.getTail() + '\n');
        testList.insert("qwerty", "QWERTY", 1.2f);
        testList.append("WASD", "wasd", 3.4f);
        testList.insert("ijkl", "IJKL", 5.6f);
        testList.append("1234", "Um Dois Tres Quatro", 7.8f);
        System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
        testList.clear();
        System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");

        // ---------------------------------------------------------------
        // Menu de opções — Sistema Conversor de Notas
        // ---------------------------------------------------------------

        /**LinkedListOriginal list = new LinkedListOriginal();
        DLinkedList fixedList = null;
        DLinkedList filteredGradedList = null;
        DLinkedList filteredNonGradedList = null;
        float average = 0.0f;
        DLinkedList aboveAverageList = null;*/

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("     Sistema Conversor de Notas");
            System.out.println("========================================");
            System.out.println("1) Dados originais");
            System.out.println("2) Dados convertidos");
            System.out.println("3) Lista notas filtradas validas");
            System.out.println("4) Lista notas filtradas invalidas");
            System.out.println("5) Media de notas validas");
            System.out.println("6) Notas acima da media");
            System.out.println("7) Lista mapeada para uma unica string");
            System.out.println("8) Finaliza sistema");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                scanner.next();
                System.out.println("Entrada invalida. Digite um numero entre 1 e 8.");
                continue;
            }

            switch (opcao) {
                case 1:
                    list = new LinkedListOriginal();
                    txtFileConsumer(list);
                    System.out.println("\n>>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
                    System.out.println(list);
                    System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<");
                    // Recarregamento invalida dados derivados
                    fixedList = null;
                    filteredGradedList = null;
                    filteredNonGradedList = null;
                    aboveAverageList = null;
                    average = 0.0f;
                    break;

                case 2:
                    if (list.getHead() == null) {
                        txtFileConsumer(list);
                    }
                    fixedList = Operation.map(list);
                    contents = Operation.mapToString(fixedList);
                    csvFileWriter(contents);
                    System.out.println("\n>>>>>>>>>> Dados convertidos para a nova representacao dos dados >>>>>>>>>>");
                    System.out.println(fixedList);
                    System.out.println("<<<<<<<<<< Dados convertidos para a nova representacao dos dados <<<<<<<<<<");
                    System.out.println("(arquivo dados.csv gerado com sucesso)");
                    // Conversao invalida dados derivados anteriores
                    filteredGradedList = null;
                    filteredNonGradedList = null;
                    aboveAverageList = null;
                    average = 0.0f;
                    break;

                case 3:
                    if (fixedList == null) {
                        System.out.println("Execute a opcao 2 primeiro para converter os dados.");
                        break;
                    }
                    filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
                    System.out.println("\n>>>>>>>>>> Lista filtrada (somente notas validas) >>>>>>>>>>");
                    System.out.println(filteredGradedList);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente notas validas) <<<<<<<<<<");
                    break;

                case 4:
                    if (fixedList == null) {
                        System.out.println("Execute a opcao 2 primeiro para converter os dados.");
                        break;
                    }
                    filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
                    System.out.println("\n>>>>>>>>>> Lista filtrada (somente 'ausencia de nota') >>>>>>>>>>");
                    System.out.println(filteredNonGradedList);
                    System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausencia de nota') <<<<<<<<<<");
                    break;

                case 5:
                    if (fixedList == null) {
                        System.out.println("Execute a opcao 2 primeiro para converter os dados.");
                        break;
                    }
                    if (filteredGradedList == null) {
                        filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
                    }
                    average = Operation.reduce(filteredGradedList);
                    System.out.println("\n>>>>>>>>>> Media das notas validas >>>>>>>>>>");
                    System.out.println(average);
                    System.out.println("<<<<<<<<<< Media das notas validas <<<<<<<<<<");
                    break;

                case 6:
                    if (fixedList == null) {
                        System.out.println("Execute a opcao 2 primeiro para converter os dados.");
                        break;
                    }
                    if (filteredGradedList == null) {
                        filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
                    }
                    if (average == 0.0f) {
                        average = Operation.reduce(filteredGradedList);
                    }
                    aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
                    System.out.println("\n>>>>>>>>>> Lista com notas acima da media >>>>>>>>>>");
                    System.out.println(aboveAverageList);
                    System.out.println("<<<<<<<<<< Lista com notas acima da media <<<<<<<<<<");
                    break;

                case 7:
                    if (fixedList == null) {
                        System.out.println("Execute a opcao 2 primeiro para converter os dados.");
                        break;
                    }
                    String mappedString = Operation.mapToString(fixedList);
                    System.out.println("\n>>>>>>>>>> Lista mapeada para uma unica string >>>>>>>>>>");
                    System.out.println(mappedString);
                    System.out.println("<<<<<<<<<< Lista mapeada para uma unica string <<<<<<<<<<");
                    break;

                case 8:
                    System.out.println("Sistema finalizado.");
                    break;

                default:
                    System.out.println("Opcao invalida. Digite um numero entre 1 e 8.");
            }

        } while (opcao != 8);

        scanner.close();
    }

    public static void txtFileConsumer(LinkedListOriginal list) {
        try (BufferedReader bf = new BufferedReader(new FileReader("dados.txt"))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] infos = line.split("#");
                list.append(Integer.parseInt(infos[0]), infos[1], Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void csvFileWriter(String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dados.csv"))) {
            bw.append(content);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}