package Conta.repository; 

import Conta.model.Conta;

public interface ContaRepository {
	
	
	//CRUD da conta
	
	public void procurarPorNumero(int numero);
	public void listarTodas();
	public void cadastrar(Conta Conta);
	public void atualizar(Conta Conta);
	public void deletar(int numero);

	
	
	//metodos
	
	public void sacar (int numero, float valor);
	public void depositar (int numero, float valor);
	public void transferir (int numeroOrigem, int numeroDestino, float valor);
	

}
