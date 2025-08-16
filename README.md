# SimulacaoRoundRobin
Simulação do algoritmo de escalonamento Round Robin (RR), usado em sistemas operacionais para gerenciar a CPU entre vários processos concorrentes.

Justiça: cada processo recebe uma fatia de tempo (chamada quantum) para executar.

Como funciona:

Os processos ficam em uma fila circular (queue).
O sistema pega o primeiro processo da fila e o executa por um tempo máximo definido (quantum).
Se o processo terminar antes do quantum, ele sai da fila.
Se não terminar, ele é interrompido e colocado no fim da fila.
O próximo processo é executado pelo mesmo tempo.
O ciclo se repete até que todos os processos terminem.
