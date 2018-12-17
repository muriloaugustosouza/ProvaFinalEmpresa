package Entidades;

public class Funcionario {
	private int ID;
	private String Nome;
	private String CPF;
	private String PIS;
	private Servicos Servicos;
	
	public Servicos getServicos() {
		return Servicos;
	}
	
	public void setServicos(Servicos servicos) {
		Servicos = servicos;
	}
	public Funcionario() {
		
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return Nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		Nome = nome;
	}
	/**
	 * @return the cPF
	 */
	public String getCPF() {
		return CPF;
	}
	/**
	 * @param cPF the cPF to set
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	/**
	 * @return the pIS
	 */
	public String getPIS() {
		return PIS;
	}
	/**
	 * @param pIS the pIS to set
	 */
	public void setPIS(String pIS) {
		PIS = pIS;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ID;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((PIS == null) ? 0 : PIS.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (ID != other.ID)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (PIS == null) {
			if (other.PIS != null)
				return false;
		} else if (!PIS.equals(other.PIS))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Funcionario [ID=" + ID + ", Nome=" + Nome + ", CPF=" + CPF + ", PIS=" + PIS + "]";
	}
	public Funcionario(int iD, String nome, String cPF, String pIS) {
		super();
		ID = iD;
		Nome = nome;
		CPF = cPF;
		PIS = pIS;
	}
	

}
