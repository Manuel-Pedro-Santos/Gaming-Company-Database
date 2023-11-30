/*
 Walter Vieira (2022-04-22)
 Sistemas de Informa��o - projeto JPAAulas_ex3
 C�digo desenvolvido para iulustra��o dos conceitos sobre acesso a dados, concretizados com base na especifica��o JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE vers�o 2022-03 (4.23.0).
 
N�o existe a pretens�o de que o c�digo estaja completo.

Embora tenha sido colocado um esfor�o significativo na corre��o do c�digo, n�o h� garantias de que ele n�o contenha erros que possam 
acarretar problemas v�rios, em particular, no que respeita � consist�ncia dos dados.  
 
*/

package presentation;

import businessLogic.BLService;

import java.util.Scanner;



/**
 * Hello world!
 *
 */

public class App 
{
	public static void main(String[] args) {
		App app = new App();
		app.menu();
	}
	String[] choices = {"DA", "DB", "DC", "E", "F", "G", "HA","HB","HC", "I", "J", "K", "L", "M", "N"};

	public void menu() {
		while (true) {
			System.out.println("Opção: " + choices[0] + "  -> Criar jogador");
			System.out.println("Opção: " + choices[1] + "  -> Desativar jogador");
			System.out.println("Opção: " + choices[2] + "  ->  Banir jogador");
			System.out.println("Opção: " + choices[3] + "  ->  Obter total de pontos de um jogador");
			System.out.println("Opção: " + choices[4] + "  ->  Obter total de jogos de um jogador");
			System.out.println("Opção: " + choices[5] + "  ->  Obter pontos por jogador em um jogo");
			System.out.println("Opção(usando pgsql): " + choices[6] + "  ->  Associar crachá a um jogador");
			System.out.println("Opção(nao usando pgsql): " + choices[7] + "  ->  Associar crachá a um jogador");
			System.out.println("Opção(nao usando pgsql): " + choices[8] + "  ->  Associar crachá a um jogador");
			System.out.println("Opção: " + choices[9] + "  ->  Iniciar uma conversa");
			System.out.println("Opção: " + choices[10] + "  ->  Juntar um jogador a uma conversa");
			System.out.println("Opção: " + choices[11] + "  ->  Enviar mensagem em uma conversa");
			System.out.println("Opção: " + choices[12] + "  ->  Acessar informações completas do jogador");
			System.out.println("Opção: " + choices[13] + "  ->  Incrementar(Optimistic) pontos de um crachá por 20%");
			System.out.println("Opção: " + choices[14] + "  ->  Incrementar(Pessimistic) pontos de um crachá por 20%");
			System.out.print("Escolha uma opção:");

			Scanner scanner = new Scanner(System.in);
			String userInput = scanner.next().toUpperCase();

			BLService srv = new BLService();
			switch (userInput) {
				case "DA" -> srv.criarJogador();
				case "DB" -> srv.desativarJogador();
				case "DC" -> srv.banirJogador();
				case "E" -> srv.obterTotalPontosJogador();
				case "F" -> srv.obterTotalJogosJogador();
				case "G" -> srv.obterPontosPorJogadorEmJogo();
				case "HA" -> srv.associarCrachaJogador1();
				case "HB" -> srv.associarCrachaJogador2();
				case "HC" -> srv.associarCrachaJogador3();
				case "I" -> srv.iniciarConversa();
				case "J" -> srv.juntarJogadorConversa();
				case "K" -> srv.enviarMensagemConversa();
				case "L" -> srv.acessarInformacoesCompletasJogador();
				case "M" ->srv.incrementarPontosCrachaOptimistic();
				case "N" ->srv.incrementarPontosCrachaPessimistic();
				default -> System.out.println("Opção inválida!");
			}
		}
	}
}

