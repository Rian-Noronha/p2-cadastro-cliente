package p2.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CadastroClientesSwing extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;

	public CadastroClientesSwing() {
		tableModel = new DefaultTableModel(new Object[] { "Nome", "Sobrenome", "CPF", "Endereço", "Gênero" }, 0);

		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);

		initComponents();
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JButton addButton = new JButton("Adicionar");
		JButton updateButton = new JButton("Atualizar");
		JButton deleteButton = new JButton("Excluir");

		addButton.addActionListener(e -> cadastrarCliente());

		updateButton.addActionListener(e -> atualizarCliente());

		deleteButton.addActionListener(e -> excluirCliente());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		add(panel);
	}

	private void cadastrarCliente() {
		String nome = JOptionPane.showInputDialog("Nome:");
		String sobrenome = JOptionPane.showInputDialog("Sobrenome:");
		String cpf = JOptionPane.showInputDialog("CPF:");
		String endereco = JOptionPane.showInputDialog("Endereço:");

		if (validarCampos(nome, sobrenome, cpf, endereco)) {
			Cliente cliente = new Cliente(nome, sobrenome, cpf, endereco);
			pegarEnumGeneroUsuario(cliente);
			Object[] rowData = { cliente.getNome(), cliente.getSobrenome(), cliente.getCpf(), cliente.getEndereco(),
					cliente.getGenero() };
			tableModel.addRow(rowData);
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void atualizarCliente() {
		int selectedIndex = table.getSelectedRow();

		if (selectedIndex != -1) {
			String nome = JOptionPane.showInputDialog("Novo nome:", table.getValueAt(selectedIndex, 0));
			String sobrenome = JOptionPane.showInputDialog("Novo sobrenome:", table.getValueAt(selectedIndex, 1));
			String cpf = JOptionPane.showInputDialog("Novo CPF:", table.getValueAt(selectedIndex, 2));
			String endereco = JOptionPane.showInputDialog("Novo endereço:", table.getValueAt(selectedIndex, 3));

			if (validarCampos(nome, sobrenome, cpf, endereco)) {
				Cliente cliente = new Cliente(nome, sobrenome, cpf, endereco);
				pegarEnumGeneroUsuario(cliente);
				Genero genero = cliente.getGenero();

				tableModel.setValueAt(nome, selectedIndex, 0);
				tableModel.setValueAt(sobrenome, selectedIndex, 1);
				tableModel.setValueAt(cpf, selectedIndex, 2);
				tableModel.setValueAt(endereco, selectedIndex, 3);
				tableModel.setValueAt(genero, selectedIndex, 4);
			} else {
				JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
		}
	}

	private void excluirCliente() {
		int selectedIndex = table.getSelectedRow();

		if (selectedIndex != -1) {
			tableModel.removeRow(selectedIndex);
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
		}
	}

	private void pegarEnumGeneroUsuario(Cliente cliente) {
		String[] opcoes = { "Masculino", "Feminino", "Outro" };
		int escolha = JOptionPane.showOptionDialog(null, "Selecione o gênero:", "Gênero", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

		switch (escolha) {
		case 0:
			cliente.setGenero(Genero.MASCULINO);
			break;
		case 1:
			cliente.setGenero(Genero.FEMININO);
			break;
		case 2:
			cliente.setGenero(Genero.OUTRO);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Nenhum gênero selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean validarCampos(String nome, String sobrenome, String cpf, String endereco) {
		return nome != null && sobrenome != null && cpf != null && endereco != null && !nome.isEmpty()
				&& !sobrenome.isEmpty() && !cpf.isEmpty() && !endereco.isEmpty();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CadastroClientesSwing().setVisible(true);
			}
		});
	}
}