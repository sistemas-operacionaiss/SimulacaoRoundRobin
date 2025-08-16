import java.util.ArrayList;
import java.util.Scanner;

public class RoundRobin {

    public static Processo processoAnterior = null;
    public static int quantum = 4;

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        ArrayList<Processo> processos = new ArrayList<>();
        Processo teste1 = new Processo(111, 1, 15, 15, 5);
        processos.add(teste1);
        Processo teste2 = new Processo(222, 2, 25, 25, 8);
        processos.add(teste2);
        Processo teste3 = new Processo(333, 3, 5, 5, 10);
        processos.add(teste3);
        Processo teste4 = new Processo(444, 4, 10, 10, 5);
        processos.add(teste4);

        int opcao = 45;

        Object entrada = "";

        do {
            System.out.println(" ");
            System.out.println("--------------------------------------------------------- MENU --------------------------------------------------------------- \n");
            System.out.println("\t1 - Cadastrar novo processo");
            System.out.println("\t2 - Consultar processos cadastrados");
            System.out.println("\t3 - Simular algoritmo de Round Robin");
            System.out.println("\t0 - Encerrar");
            System.out.println(" ");
            System.out.print("\tDigite a opção desejada: ");
            entrada = teclado.next();
            System.out.println(" ");

            try {
                opcao = Integer.parseInt(entrada.toString());
            } catch (NumberFormatException e) {
            }

            switch (opcao) {
                case 1: {
                    int PID = processos.size() + 1;
                    int lugarNaFila = processos.size() + 1;
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("-------------------------------------------------- INSERIR PROCESSO ----------------------------------------------------------");
                    System.out.println("\tPID: " + PID + " | Posição: " + lugarNaFila);
                    System.out.print("\tTempo de execução: ");
                    int tempoDuracao = teclado.nextInt();
                    System.out.print("\tPrioridade: ");
                    int prioridade = teclado.nextInt();
                    int tempoRestante = tempoDuracao;
                    Processo processo1 = new Processo(PID, lugarNaFila, tempoDuracao, tempoRestante, prioridade);
                    processos.add(processo1);
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                }
                break;

                case 2: {
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("----------------------------------------------- PROCESSOS CADASTRADOS --------------------------------------------------------");
                    for (Processo processo : processos) {
                        System.out.println(processo);
                    }
                    System.out.println("----------------------------------------------- Quantidade total de processos: " + processos.size() + " ---------------------------------------------");
                }
                break;

                case 3: {
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("----------------------------------------------------- SIMULAÇÃO ROUND ROBIN ---------------------------------------------------");

                    int contadorTotal = 1;//acumula tempo total
                    
                    while (!verificarProcessosFinilizados(processos)) { //loop que roda até que verificarProcessosFinilizados retorne vdd
                        //Processo processoDaVez = encontrarProcessoDaVez(processos);
                        for(Processo processoDaVez : processos){
                            
                            if(processoDaVez.finalizado){
                                continue;
                            }
                            
                            System.out.println("----------------------------------------- INICIO DO PROCESSAMENTO DO PROCESSO DE PID " + processoDaVez.PID + " -------------------------------------");
                            if(processoAnterior == null || processoAnterior.finalizado){
                                processoAnterior = processoDaVez;
                            } else if ( !processoAnterior.finalizado && processoAnterior.PID != processoDaVez.PID &&  processoDaVez.tempoRestante > quantum){
                                contadorTotal++; //Simula tempo gasto pelo salvamento de contexto
                                System.out.println(" Salvando contexto tempo total -> " + (contadorTotal - 1));
                                processoAnterior = processoDaVez;
                            }

                            for (int i = 0; i < processoDaVez.tempoDuracao; i++) {
                                processoDaVez.contadorDeTempoDoProcesso = contadorTotal;
                                processoDaVez.tempoRestante--;
                                System.out.println(processoDaVez.toString());
                                if (processoDaVez.tempoRestante == 0) {
                                    processoDaVez.finalizado = true;
                                    contadorTotal++;
                                    break;
                                }
                                if (i == quantum) {//
                                    contadorTotal++;
                                    break;
                                }
                                contadorTotal++;
                            }
                            System.out.println("--------------------------------------------- TEMPO TOTAL DE PROCESSAMENTO: " + (contadorTotal - 1) + " ------------------------------------------------"); 
                        }
                        System.out.println("");
                        for (Processo processo : processos) {
                            if(processo.finalizado){
                                System.out.println(processo.PID + " -- finalizado " + processo.finalizado);
                            }else{
                                System.out.println(processo);
                            }
                        }
                    }
                }
                break;
                
                case 0: {
                    System.out.println("Encerrando o programa.");
                }
            }
        } while (opcao != 0);
    }
    

    private static boolean verificarProcessosFinilizados(ArrayList<Processo> processos) {
        if (processos.isEmpty()) {
            return true;
        }
        for (Processo processo : processos) {
            if (processo.finalizado == false) {
                return false;
            }
        }
        return true;
    }
}
