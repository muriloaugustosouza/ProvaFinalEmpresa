package EFAXFuncionario;


import Entidades.Funcionario;
import Entidades.Servicos;
import View.ViewFuncionario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


import DAO.FuncionarioDAO;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import DAO.ServicosDAO;
import View.ViewServicos;

public class ControleFuncionario implements Initializable {

	private ViewFuncionario ViewFuncionario = new ViewFuncionario();
    private FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
    private ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();	
    private Funcionario FuncionarioEscolhido;
    
    private ServicosDAO ServicosBanco = new ServicosDAO();
    private ViewServicos ViewServicos = new ViewServicos();
    private ArrayList<Servicos> listaServicos = new ArrayList<Servicos>();
    private Servicos servicoEscolhido;
    
    @FXML
    private TextField TXTNOME;

    @FXML
    private Button BTNREGISTRAR;

    @FXML
    private Button BTNALTERAR;

    @FXML
    private Button BTNDELETAR;

    @FXML
    private TextField TXTCPF;

    @FXML
    private TextField TXTPIS;

    @FXML
    private TextField TXTPESQUISAR;

    @FXML
    private Button BTNPESQUISAR;

    @FXML
    private Button BTNSAIR;
    
    @FXML
    private Button BTNATUALIZAR;

    @FXML
    private TableView<Funcionario> TABLE;

    @FXML
    private TableColumn<Funcionario, Integer> ID;

    @FXML
    private TableColumn<Funcionario, String> NOME;

    @FXML
    private TableColumn<Funcionario, String> CPF;

    @FXML
    private TableColumn<Funcionario, String> PIS;
    
    @FXML
    private TableView<Servicos> TABLE2;
    
    @FXML
    private TableColumn<?, ?> SETOR;

    @FXML
    private TableColumn<?, ?> DESCRICAO;
    
    @FXML
    private TextField TXTSETOR;

    @FXML
    private TextField TXTDESCRICAO;
    
    
    public void initialize(URL location, ResourceBundle resources) {
	    ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		NOME.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		CPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		PIS.setCellValueFactory(new PropertyValueFactory<>("PIS"));

		refreshTable();

		TABLE.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Funcionario>() {
			
			@Override
			public void changed(ObservableValue<? extends Funcionario> observable, Funcionario oldValue, Funcionario newValue) {
				FuncionarioEscolhido = newValue;	
					
			}
		
		});
		
		SETOR.setCellValueFactory(new PropertyValueFactory<>("SETOR"));
		DESCRICAO.setCellValueFactory(new PropertyValueFactory<>("DESCRICAO"));
		
		refreshTable2();
		
		TABLE2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Servicos>() {
			
			@Override
			public void changed(ObservableValue<? extends Servicos> observable, Servicos oldValue, Servicos newValue) {
				servicoEscolhido = newValue;	
					
			}
		});
	}
    @FXML
    private void cadastrar(ActionEvent event) {
    	
    	Funcionario Funcionario = ViewFuncionario.inserir(TXTNOME.getText(), TXTCPF.getText(), TXTPIS.getText() );
    	Servicos Servicos = ViewServicos.inserir(TXTSETOR.getText(), TXTDESCRICAO.getText() );
    	ServicosBanco.inserirServicos(Servicos);
        Funcionario.setServicos(Servicos);
    	FuncionarioDAO.inserirFuncionario(Funcionario);
    	
    	refreshTable();
    	refreshTable2();
    }
       
    @FXML
    private void refreshTable() {
		listaFuncionario = new FuncionarioDAO().listarFuncionarios();
		ObservableList<Funcionario> observableList = FXCollections.observableArrayList(listaFuncionario);
		TABLE.setItems(observableList);
		TABLE.refresh();
	
	}  
    
    @FXML
    private void refreshTable2() {
		listaServicos = new ServicosDAO().listarServicos();
		ObservableList<Servicos> observableListServico = FXCollections.observableArrayList(listaServicos);
		TABLE2.setItems(observableListServico);
		TABLE2.refresh();
	
	}  
    
    @FXML
	 private void deletar() {
    		FuncionarioDAO.excluirFuncionario(FuncionarioEscolhido);
			ServicosBanco.excluirServicos(servicoEscolhido);
    		refreshTable();	 
    		refreshTable2();
	 }
    
    @FXML
	 private void editarFuncionario() {
		 TXTNOME.setText(FuncionarioEscolhido.getNome());
		 TXTCPF.setText(FuncionarioEscolhido.getCPF());
		 TXTPIS.setText(FuncionarioEscolhido.getPIS());	
		 
		 FuncionarioDAO.alterarFuncionario(FuncionarioEscolhido);
		 refreshTable();
		 refreshTable2();
	 }
    
    @FXML
    private void editar() {
    	FuncionarioEscolhido.setNome(TXTNOME.getText());
    	FuncionarioEscolhido.setCPF(TXTCPF.getText());
    	FuncionarioEscolhido.setPIS(TXTPIS.getText());	
    	 
    	FuncionarioDAO.alterarFuncionario(FuncionarioEscolhido);
		 refreshTable();	
	 }
    @FXML
   private  void sair(ActionEvent event ) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Sair");
    	String s ="Deseja sair?";
    	alert.setContentText(s);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if((result.isPresent()) && (result.get() == ButtonType.OK)){
    		System.exit(0);
    	
    }
    }
    @FXML
	 private void pesquisar() {
		 
    	FuncionarioDAO FuncionarioPesquisar = new FuncionarioDAO();
		
    	Funcionario pesquisar = new Funcionario();
		 
		 pesquisar = FuncionarioPesquisar.pesquisar(TXTPESQUISAR.getText());
		 
		 listaFuncionario.add(pesquisar);
		 		 
		 ObservableList<Funcionario> observableList = FXCollections.observableArrayList(pesquisar);
		 	 
		 TABLE.setItems(observableList);
		 
	 }
   

}
