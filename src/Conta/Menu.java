package Conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Conta.controller.ContaController;
import Conta.model.Conta;
import Conta.model.ContaCorrente;
import Conta.model.ContaPoupanca;
import Conta.util.Cores;



public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ContaController contas = new ContaController();
		
		
		Scanner ler = new Scanner (System.in);
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor = 0;
		
		
		//Objetos para alimentar a Collection listaContas
		
		System.out.println("\nCriar Contas\n");
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 126, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		
		while (true){
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "------------------------------------------------------------------------------------");
			System.out.println("                                                                       ");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>BANCO DO BRAZIL COM Z>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("                                                                       ");
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("                                                                       ");
			System.out.println(" 1- Criar conta");
			System.out.println("\n 2- Listar todas as contas");
			System.out.println("\n 3- Buscar conta por numero");
			System.out.println("\n 4- Atualizar dados da conta");
			System.out.println("\n 5- Apagar conta");
			System.out.println("\n 6- Sacar");
			System.out.println("\n 7- Depositar");
			System.out.println("\n 8- Transferir valores entre contas");
			System.out.println("\n 9- Sair");
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");

			
					
			
			try {
				opcao = ler.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				opcao = 0;
			}
			
			if(opcao == 9) {
				System.out.println("\\nBanco do Brazil com z - O seu Futuro começa aqui!");
				ler.close();
				System.exit(0);
			}
			
			
			
			
			switch (opcao) {
			
			
			case 1:
				System.out.println(Cores.TEXT_WHITE +"Criar Conta\n");
				
				System.out.println("Digite o Número da Agência: ");
				agencia = ler.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				ler.skip("\\R?"); 
				titular = ler.nextLine();
				
				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					tipo = ler.nextInt();
				} while(tipo < 1 && tipo > 2);
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = ler.nextFloat();
				
				
				switch(tipo) {
					case 1 -> {
						System.out.println("Digite o limite de Crédito (R$): ");
						limite = ler.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = ler.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}
				keyPress();
				break;
				
				
				
			case 2: 
				System.out.println(Cores.TEXT_WHITE + "Listar todas as contas \n\n");
				contas.listarTodas();
				keyPress();
				break;
			
				
			case 3: 
				System.out.println(Cores.TEXT_WHITE + "Consltar dados da conta - por número\n\n");
				
				
				System.out.println("Digite o número da conta: ");
				numero = ler.nextInt();
				
				contas.procurarPorNumero(numero);
				
				
				keyPress();
				break;
				
				
			case 4: 
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = ler.nextInt(); 
				
				
				
				if(contas.buscarNaCollection(numero)!= null) {
					System.out.println("Digite o Número da Agência: ");
					agencia = ler.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					ler.skip("\\R?"); 
					titular = ler.nextLine();
					
					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = ler.nextFloat();
					
					tipo = contas.retornaTipo(numero);
					
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o limite de Crédito (R$): ");
						limite = ler.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = ler.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
				    }
				}else
					
					
					System.out.println("A Conta número: "+numero+" não foi encontrada!");
				
				keyPress();
				break;
				
				
			case 5: 
				System.out.println(Cores.TEXT_WHITE + "Apagar a conta\n\n");
				
				
				System.out.println("Digite o número da conta: ");
				numero = ler.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
				break;
			
			
			case 6: 
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = ler.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = ler.nextFloat();
				}while(valor <=0);
				
				contas.sacar(numero, valor);
				
				keyPress();
				break;
			
				
			case 7: 
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = ler.nextInt();
				
				do {
					System.out.println("Digite o valor do deposito (R$): ");
					numero = ler.nextInt();
				}while(valor <=0);
				
				contas.depositar(numero, valor);
				
				keyPress();
				break;
			
				
			case 8: 
				System.out.println(Cores.TEXT_WHITE + "Transferência entre contas\n\n");
				
				
				System.out.println("Digite o numero da conta de origem: ");
				numero = ler.nextInt();
				System.out.println("Digite o numero da conta de destino: ");
				numeroDestino = ler.nextInt();
				
				do {
					System.out.println("Digite o valor da transferencia (R$): ");
					valor = ler.nextFloat();
				}while(valor <=0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				
				keyPress();
				break;
				
				
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida" + Cores.TEXT_RESET);
				
				
				keyPress();
				break;
			
			
			}}
		}
			
			public static void keyPress() {

				try {

					System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
					System.in.read();

				} catch (IOException e) {

					System.out.println("Você pressionou uma tecla diferente de enter!");
			
		}
		
		
		
			}
		}