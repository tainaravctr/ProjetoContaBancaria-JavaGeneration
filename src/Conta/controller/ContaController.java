package Conta.controller;
import java.util.ArrayList;

import Conta.model.Conta;
import Conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
	
	
	private ArrayList <Conta> listaContas = new ArrayList <Conta>();
	int numero = 0;

	
	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null)  //se a conta nao for encontrada
			conta.visualizar();
		else
			System.out.println("\nA Conta numero: " + numero + " não foi encontrada!"); //entao imprima na tela...
		
		
	}

	
	
	@Override
	public void listarTodas() {
		for (var Conta: listaContas) {
			Conta.visualizar();
		}
	}
	
	

	@Override
	public void cadastrar(Conta Conta) {
		listaContas.add(Conta);
		System.out.println("\nA Conta número: " + Conta.getNumero() + " foi criada com sucesso!");
	}

	
	
	@Override
	public void atualizar(Conta Conta) {
		var buscarConta = buscarNaCollection(Conta.getNumero());
		
		
		if (buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), Conta);
			System.out.println("\nA Conta numero: " + Conta.getNumero() + " foi atualizada com sucesso!");
		}else 
			System.out.println("\nA conta numero: " + Conta.getNumero()+ "não foi encontrada!");
	}
	

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		
		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("\nA conta numero: " +numero+ " foi deletada com sucesso!");
		}else
			System.out.println("\nA conta numero: " +numero+ " não foi encontrada!");
	}

	
	
	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if (conta.sacar(valor) == true)
				System.out.println("\nO saque na conta numero: " +numero+ " foi efetuado com sucesso!");
		}else
			System.out.println("\nA conta numero: " +numero+ " não foi encontrada!");
	}

	
	
	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			conta.depositar(valor);
			System.out.println("\nO deposito na conta numero: " +numero+ " foi efetuado com sucesso!");
		}else
			System.out.println("\nA conta numero: "+numero+ " não foi encontrada ou a conta destino não é uma conta corrente!");		
	}

	
	
	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("\nA transferencia foi efetuada com sucesso!");
			}
		}else
			System.out.println("\nA conta de origem ou/e destino não foram encontradas!");
		
	}

	
	
	public ArrayList <Conta> getListaContas() {
		return listaContas;
	}

	
	
	public void setListaContas(ArrayList <Conta> listaContas) {
		this.listaContas = listaContas;
	}

	
	
	// metodo auxiliar
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	
	public Conta buscarNaCollection (int numero) {
		for(var conta: listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		
	 return null;
	 
	}
	
	public int retornaTipo(int numero) {
		for (var conta: listaContas) {
			if(conta.getNumero() == numero) {
				return conta.getTipo();			
		}
	}
	
		
	return 0;

	
	}
}
