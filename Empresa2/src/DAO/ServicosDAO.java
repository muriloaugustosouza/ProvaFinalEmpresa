package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexao.CNXHSQLDB;
import Entidades.Servicos;

public class ServicosDAO {
	private final String SQL_INSERIR_SERVICOS = "INSERT INTO SERVICOS (" + "SETOR, DESCRICAO)" + "VALUES (?, ?)";
	private final String SQL_ALTERAR_SERVICOS = "UPDATE SERVICOS SET SETOR=?, DESCRICAO=? WHERE ID=?";
	private final String SQL_EXCLUIR_SERVICOS = "DELETE FROM SERVICOS WHERE ID=?";
	private final String SQL_SELECIONAR_SERVICOS = "SELECT * FROM SERVICOS";

	private PreparedStatement pst = null;

	public boolean inserirServicos(Servicos umServicos) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_SERVICOS);
			pst.setString(1, umServicos.getSetor());
			pst.setString(2, umServicos.getDescricao());
			ret = pst.execute();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public ArrayList<Servicos> listarServicos() {
		ArrayList<Servicos> listaServicos = new ArrayList<Servicos>();
		Connection conn = CNXHSQLDB.conectar();
		Servicos umServicos;
		try {
			pst = conn.prepareStatement(SQL_SELECIONAR_SERVICOS);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				umServicos = new Servicos();
				umServicos.setID(rs.getInt("ID"));
				umServicos.setSetor(rs.getString("SETOR"));
				umServicos.setDescricao(rs.getString("DESCRICAO"));
				listaServicos.add(umServicos);
			}
			rs.close();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return listaServicos;
	}

	public boolean alterarServicos(Servicos umServicos) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERAR_SERVICOS);
			pst.setString(1, umServicos.getSetor());
			pst.setString(2, umServicos.getDescricao());
			pst.setInt(3, umServicos.getID());
			ret = pst.execute();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

	public boolean excluirServicos(Servicos umServicos) {
		boolean ret = false;
		Connection conn = CNXHSQLDB.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUIR_SERVICOS);
			pst.setInt(1, umServicos.getID());
			ret = pst.execute();
			pst.close();
			CNXHSQLDB.fecharCNX();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement " + e.toString());
		}
		return ret;
	}

}
