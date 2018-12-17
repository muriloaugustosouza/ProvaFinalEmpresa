package View;

import Entidades.Servicos;

public class ViewServicos {
	
	private int controleID = 0;
	
	public Servicos inserir(String setor, String descricao) {
		
		Servicos Servicos = new Servicos();
		
		Servicos.setSetor(setor);
		
		Servicos.setDescricao(descricao);
		
		Servicos.setID(controleID);
		controleID++;
		
		return Servicos;
		
		
	}

}
