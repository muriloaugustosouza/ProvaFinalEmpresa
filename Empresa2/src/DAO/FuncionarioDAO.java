package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexao.CNXHSQLDB;
import Entidades.Funcionario;

public class FuncionarioDAO {

	private final String SQL_INSERIR_FUNCIONARIO = "INSERT INTO FUNCIONARIO ("+ "NOME, CPF, PIS, IDSERVICOS)" + "VALUES (?, ?, ?, ?)";
	private final String SQL_ALTERAR_FUNCIONARIO = "UPDATE FUNCIONARIO SET NOME=?, CPF=?, PIS=? WHERE ID=?";
	private final String SQL_EXCLUIR_FUNCIONARIO = "DELETE FROM FUNCIONARIO WHERE ID=?";
	private final String SQL_SELECIONAR_FUNCIONARIO = "SELECT * FROM FUNCIONARIO";
	private final String SQL_PESQUISAR_FUNCIONARIO = "SELECT DISTINCT * FROM FUNCIONARIO WHERE NOME=?";

	
private PreparedStatement pst = null;
	
	public boolean inserirFuncionario(Funcionario umFuncionario) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_FUNCIONARIO);
			pst.setString(1, umFuncionario.getNome());
			pst.setString(2, umFuncionario.getCPF());
			pst.setString(3, umFuncionario.getPIS());
			pst.setInt(4, umFuncionario.getServicos().getID());
			ret = pst.execute();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;
	}
	
	public ArrayList<Funcionario> listarFuncionarios(){
		ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		Connection conn = CNXHSQLDB.conectar();
		Funcionario umFuncionario;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_FUNCIONARIO);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				umFuncionario = new Funcionario();
				umFuncionario.setID(rs.getInt("ID"));
				umFuncionario.setNome(rs.getString("NOME"));
				umFuncionario.setCPF(rs.getString("CPF"));
				umFuncionario.setPIS(rs.getString("PIS"));
				listaFuncionario.add(umFuncionario);
			}
			rs.close();
			pst.close();
			CNXHSQLDB.fecharCNX();			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return listaFuncionario;
	}
	
	public boolean alterarFuncionario(Funcionario umFuncionario) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_FUNCIONARIO);
			pst.setString(1, umFuncionario.getNome());
			pst.setString(2, umFuncionario.getCPF());
			pst.setString(3, umFuncionario.getPIS());
			pst.setInt(4, umFuncionario.getID());
			ret = pst.execute();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}
	
	public boolean excluirFuncionario(Funcionario umFuncionario) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_FUNCIONARIO);
			pst.setInt(1, umFuncionario.getID());
			ret = pst.execute();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e){
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return ret;	
	}
	public Funcionario pesquisar(String pesquisar){
		Connection conn = CNXHSQLDB.conectar();
		Funcionario umFuncionario = new Funcionario();
		try {
			pst = conn.prepareStatement(SQL_PESQUISAR_FUNCIONARIO);
			pst.setString(1, pesquisar);
			ResultSet rs = pst.executeQuery();			
			while(rs.next()) {				
				umFuncionario.setID(rs.getInt("ID"));
				umFuncionario.setNome(rs.getString("NOME"));
				umFuncionario.setCPF(rs.getString("CPF"));
				umFuncionario.setPIS(rs.getString("PIS"));			
			}
			rs.close();
			pst.close();
			CNXHSQLDB.fecharCNX();			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " +e.toString() );
		}
		return umFuncionario;
	}
	
}
