package Forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import Banco.FireBird;
import Locadora.CLIENTES;
import Locadora.FILMES;
import Locadora.RESERVA;
import Locadora.UTIL;

public class frmReserva extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel lblCodigo;
	private JLabel lblIdReserva;
	private JLabel lblCodCliente;
	private JLabel lblIdCliente;
	private JLabel lblEnderecoFixo;
	private JLabel lblEndereco;
	private JLabel lblNomeFixo;
	private JLabel lblNome;
	private JLabel lblCpfFixo;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JLabel lblFone;
	private JLabel lblFilmes;
	private JLabel lblSituacao;
	private JLabel lblSituacaoGeral;
	private JTextField txtIdFilmes;
	private JButton btnAcrescentarFilme;
	private JButton btnExcluirFilme;
	private JButton btnPesquisarFilmes;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JButton btnPesquisarCodCliente;
	private JComboBox cmbSituacao;
	private JTable table;
	private JFrame frame;
	private RESERVA reserva;
	private TableColumn sportColumn;
	
	
	private static frmReserva instance = null;
	
	public static frmReserva getInstance()
	{
		if(instance==null)
			instance = new frmReserva();
		instance.frame.requestFocus();
		return instance;
	}
	public static frmReserva getInstance(RESERVA reserva)
	{
		if(instance==null)
			instance = new frmReserva();
		instance.frame.requestFocus();
		instance.reserva = reserva;
		instance.setCliente(reserva.cliente);
		instance.lblIdReserva.setText(String.valueOf(reserva.id_reserva));
		instance.btnPesquisarFilmes.setEnabled(false);
		instance.btnPesquisarCodCliente.setEnabled(false);
		instance.btnAcrescentarFilme.setEnabled(false);
		instance.btnExcluirFilme.setEnabled(false);
		instance.lblSituacaoGeral.setText(reserva.situacao);
		instance.showFilmes();
		
		return instance;
	}
	public static frmReserva retornaClienteSelecionado(CLIENTES cliente)
	{
		if(instance==null)
			instance = new frmReserva();
		instance.frame.requestFocus();
		instance.setCliente(cliente);
		return instance;
	}
	public static frmReserva retornaFilmeSelecionado(int id_filmes)
	{
		if(instance==null)
			instance = new frmReserva();
		instance.frame.requestFocus();
		instance.txtIdFilmes.setText(String.valueOf(id_filmes));
		return instance;
	}
	
	
	private void setCliente(CLIENTES cliente_)
	{
		if(cliente_==null)
			return;
		reserva.cliente = cliente_;
		lblIdCliente.setText(String.valueOf(reserva.cliente.id_clientes));
		lblNome.setText(reserva.cliente.nome);
		lblCpf.setText(reserva.cliente.cpf);
		lblFone.setText(reserva.cliente.fone);
		lblEndereco.setText(reserva.cliente.endereco);
	}

	private frmReserva() 
	{
		reserva = new RESERVA();
		reserva.id_reserva = FireBird.getInstance().getId("reserva");
		lblCodigo = new JLabel("Cod");
		lblIdReserva = new JLabel(String.valueOf(reserva.id_reserva));
		lblCodCliente = new JLabel("C�d Cliente");
		lblIdCliente = new JLabel();
		btnPesquisarCodCliente = new JButton("...");
		lblEnderecoFixo = new JLabel("Endere�o");
		lblEndereco = new JLabel();
		lblNomeFixo = new JLabel("Nome");
		lblNome = new JLabel();
		lblCpfFixo = new JLabel("CPF");
		lblCpf = new JLabel();
		lblTelefone = new JLabel("Telefone");
		lblFone = new JLabel();
		lblFilmes = new JLabel("Filmes");
		txtIdFilmes = new JTextField(5);
		btnAcrescentarFilme = new JButton("+");
		btnExcluirFilme = new JButton("-");
		table = new JTable(9,3);
		btnPesquisarFilmes = new JButton("p");
		btnCancelar = new JButton("Cancelar");
		btnConfirmar = new JButton("Confirmar");
		lblSituacao = new JLabel("Situa��o");
		lblSituacaoGeral = new JLabel();
		
		
		table.getColumnModel().getColumn(0).setHeaderValue("C�digo");
		table.getColumnModel().getColumn(1).setHeaderValue("Nome");
		table.getColumnModel().getColumn(2).setHeaderValue("Situa��o");
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setPreferredScrollableViewportSize(new Dimension(490, 50));
		
		sportColumn = table.getColumnModel().getColumn(2);
		cmbSituacao = new JComboBox();
		cmbSituacao.addItem("AGUARDANDO");
		cmbSituacao.addItem("CONCLUIDO");
		cmbSituacao.setEditable(false);
		cmbSituacao.setSelectedIndex(0);
		sportColumn.setCellEditor(new DefaultCellEditor(cmbSituacao));
		
        table.setFillsViewportHeight(true);
        table.setSelectionMode(WHEN_FOCUSED);
        JScrollPane jp = new JScrollPane(table);  
        jp.setViewportView(table);  
        jp.setAutoscrolls(true);  
        jp.setEnabled(true);
        jp.setWheelScrollingEnabled(true);
        table.setAutoscrolls(true);
        table.getTableHeader().setBounds(15, 175, 490,15);
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        add(jp);
		add(table);
		add(table.getTableHeader());
		table.setOpaque(true);
		add(lblSituacaoGeral);

		setPreferredSize(new Dimension(461, 312));
		setLayout(null);
		
		btnCancelar.addActionListener(this);
		btnConfirmar.addActionListener(this);
		btnPesquisarCodCliente.addActionListener(this);
		btnPesquisarFilmes.addActionListener(this);
		cmbSituacao.addActionListener(this);

		add(lblCodigo);
		add(lblIdReserva);
		add(lblCodCliente);
		add(lblIdCliente);
		add(btnPesquisarCodCliente);
		add(lblEnderecoFixo);
		add(lblEndereco);
		add(lblNomeFixo);
		add(lblNome);
		add(lblCpfFixo);
		add(lblCpf);
		add(lblTelefone);
		add(lblFone);
		add(lblFilmes);
		add(txtIdFilmes);
		add(btnPesquisarFilmes);
		add(btnCancelar);
		add(btnConfirmar);
		add(lblSituacao);
		add(btnAcrescentarFilme);
		add(btnExcluirFilme);
		
		btnExcluirFilme.addActionListener(this);
		btnAcrescentarFilme.addActionListener(this);

		lblCodigo.setBounds(15, 15, 50, 25);
		lblIdReserva.setBounds(65, 15, 70, 25);
		lblCodCliente.setBounds(15, 45, 75, 25);
		lblIdCliente.setBounds(90, 45, 60, 25);
		btnPesquisarCodCliente.setBounds(155, 45, 30, 25);
		lblEnderecoFixo.setBounds(15, 110, 65, 25);
		lblEndereco.setBounds(85, 110, 225, 25);
		lblNomeFixo.setBounds(15, 75, 45, 25);
		lblNome.setBounds(85, 80, 230, 25);
		lblCpfFixo.setBounds(325, 80, 30, 25);
		lblCpf.setBounds(360, 80, 95, 25);
		lblTelefone.setBounds(325, 110, 30, 25);
		lblFone.setBounds(360, 110, 95, 25);
		lblFilmes.setBounds(15, 145, 60, 25);
		txtIdFilmes.setBounds(65, 145, 60, 25);
		btnPesquisarFilmes.setBounds(130, 145, 30, 25);
		btnAcrescentarFilme.setBounds(165, 145, 45, 25);
		btnExcluirFilme.setBounds(215, 145, 45, 25);
		btnExcluirFilme.setForeground(new Color(255,0,0));
		btnAcrescentarFilme.setForeground(new Color(255,0,0));
		
		
		table.setBounds(15, 190, 425, 80);
		
		btnConfirmar.setBounds(85, 280, 100, 25);
		btnCancelar.setBounds(255, 280, 100, 25);
		lblSituacao.setBounds(140, 15, 65, 25);
		lblSituacaoGeral.setBounds(210, 15, 150, 25);
		frmShow();
	}

	private void frmShow() 
	{
		frame = new JFrame("CONTROLE DE RESERVA");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		UTIL.setCenterScreen(frame);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() 
		{  
			public void windowClosed(WindowEvent e) 
			{  
				frmReserva.instance=null;
			}  
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnCancelar)
			onPressed_btnCancelar();
		else if(e.getSource() == btnConfirmar)
			onPressed_btnConfirmar();
		else if(e.getSource() == btnPesquisarCodCliente)
			onPressed_btnPesquisarCodCliente();
		else if(e.getSource() == btnPesquisarFilmes)
			onPressed_btnPesquisarFilmes();
		else if(e.getSource() == btnAcrescentarFilme)
			onPressed_AcrescentaFilmes();
		else if(e.getSource() == btnExcluirFilme)
			onPressed_ExcluirFilmes();
		else if(e.getSource() == cmbSituacao)
			onChange_cmbSituacao();
	}
	private void onPressed_AcrescentaFilmes()
	{
		
		if(reserva.filmes.size()>=5)
		{
			JOptionPane.showMessageDialog(this,"Sua lista de filmes est� cheia!");
			return;
		}
		if(lblNome.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Cliente n�o selecionado.");
			return;
		}
		int id_filme = UTIL.getInt(txtIdFilmes.getText(),-1);
		if(id_filme == -1)
		{
			JOptionPane.showMessageDialog(null,"Filme n�o encontrado.");
			return;
		}
		if (!FireBird.getInstance().existe_filme(id_filme))
		{
			JOptionPane.showMessageDialog(null,"Filme n�o encontrado.");
			return;
		}
		if (FireBird.getInstance().existeFilmeDisponivel(id_filme))
		{
			JOptionPane.showMessageDialog(null,"Este filme est� disponivel no momento.");
			return;
		}
		FILMES filme = FireBird.getInstance().selectBuscaFilme(id_filme);
		if(filme==null)
		{
			JOptionPane.showMessageDialog(null,"Filme n�o encontrado.");
			return;
		}
		reserva.filmes.add(filme);
		showFilmes();
		txtIdFilmes.setText("");
	}
	private void onChange_cmbSituacao()
	{
		try
		{
			int id_filmes = (Integer)table.getModel().getValueAt(table.getSelectionModel().getLeadSelectionIndex(),0);
			String situacao = (String)table.getModel().getValueAt(table.getSelectionModel().getLeadSelectionIndex(),2);
			int size = reserva.filmes.size();
			for(int i=0; i<size;i++)
			{
				FILMES f = reserva.filmes.get(i);
				if(id_filmes  == f.id_filmes)
				{
					reserva.situacao_individual[i] = situacao.toUpperCase();
					break;
				}
			}
		}
		catch(Exception exc)
		{
			try
			{
				table.getModel().setValueAt("",table.getSelectionModel().getLeadSelectionIndex(),0);
			}
			catch(Exception excc){}
		}
		showFilmes();
	}
	private void onPressed_ExcluirFilmes()
	{
		try
		{
			int id = (Integer)table.getModel().getValueAt(table.getSelectionModel().getLeadSelectionIndex(),0);
			FILMES filme = FireBird.getInstance().selectBuscaFilme(id);
			int size = reserva.filmes.size();
			for(int i=0; i<size;i++)
			{
				FILMES f = reserva.filmes.get(i);
				if(filme.id_filmes == f.id_filmes)
				{
					reserva.filmes.remove(i);
					break;
				}
			}
			//if(!FireBird.getInstance().excluir(id_pd_itens_pd_locacao, "itens_pd_locacao"))
			//	JOptionPane.showMessageDialog(null, "erro id_pd_itens_pd_locacao " + id_pd_itens_pd_locacao);
		}
		catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null,"Filme n�o selecionado.");
		}
		showFilmes();
	}
	private void showFilmes()
	{
		int size = reserva.filmes.size();
		int rows = table.getRowCount(),totalCol = 2;
		for(int count = 0; count < rows; count++)
		{
			for(int i=0; i < totalCol;i++)
				table.getModel().setValueAt("", count, i);
		}
		for(int i=0;i <size;i++)
		{
			int id_filmes  	= reserva.filmes.get(i).id_filmes;
			String nome  	= reserva.filmes.get(i).nome;
			table.getModel().setValueAt(id_filmes, i, 0);
			table.getModel().setValueAt(nome, i, 1);
			if(reserva.situacao_individual[i]==null)
				reserva.situacao_individual[i] = "AGUARDANDO";
			table.getModel().setValueAt(reserva.situacao_individual[i], i, 2);
		}
	}
	private void onPressed_btnCancelar()
	{
		frame.dispose();
	}
	private void onPressed_btnConfirmar()
	{
		int size =reserva.filmes.size();
		if(lblNome.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Cliente n�o selecionado.");
			return;
		}
		if(size==0)
		{
			JOptionPane.showMessageDialog(this,"Sua lista de filmes est� vazia!");
			return;
		}
		for(int j=0; j<size; j++)
		{
			try
			{
				String situacao = (String)table.getModel().getValueAt(j,2);
				if(situacao==null)
					throw(new Exception());
				reserva.situacao_individual[j] = situacao;
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Por favor informe a situa��o do filme "  + reserva.filmes.get(j).nome);
				return;
			}
		}
		//reserva.data 
		if(FireBird.getInstance().insertOrUpdate(reserva))
		{
			this.frame.dispose();
			frmBuscaGenerica.RefreshPesquisa(frmBuscaGenerica.BUSCA_RESERVA);
		}
	}
	
	private void onPressed_btnPesquisarCodCliente()
	{
		frmBuscaGenerica.getInstance(frmBuscaGenerica.BUSCA_CLIENTES_PARA_RESERVA);
	}
	private void onPressed_btnPesquisarFilmes()
	{
		frmBuscaGenerica.getInstance(frmBuscaGenerica.BUSCA_FILMES_PARA_RESERVAS);
	}
	

}