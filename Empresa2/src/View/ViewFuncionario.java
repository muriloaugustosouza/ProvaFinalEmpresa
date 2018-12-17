package View;

import Entidades.Funcionario;

public class ViewFuncionario {

	public Funcionario inserir(String nome, String cpf, String pis) {
		
		Funcionario Funcionario = new Funcionario();

		Funcionario.setNome(nome);

		Funcionario.setCPF(cpf);

		Funcionario.setPIS(pis);

		return Funcionario;
	}

}