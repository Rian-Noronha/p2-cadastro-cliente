package p2.main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroClientesSwing extends JFrame {

    private static final long serialVersionUID = 1L;
	private List<Cliente> clientes;
    private DefaultListModel<Cliente> listModel;
    private JList<Cliente> clientesList;

    public CadastroClientesSwing() {
        clientes = new ArrayList<>();
        listModel = new DefaultListModel<>();

        setTitle("Cadastro de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        clientesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(clientesList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Adicionar");
        JButton updateButton = new JButton("Atualizar");
        JButton deleteButton = new JButton("Excluir");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCliente();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String endereco = JOptionPane.showInputDialog("Digite o endereço do cliente:");

        Cliente cliente = new Cliente(nome, endereco);
        clientes.add(cliente);
        listModel.addElement(cliente);
    }

    private void atualizarCliente() {
        int selectedIndex = clientesList.getSelectedIndex();

        if (selectedIndex != -1) {
            Cliente cliente = clientes.get(selectedIndex);

            String nome = JOptionPane.showInputDialog("Digite o novo nome do cliente:", cliente.getNome());
            String endereco = JOptionPane.showInputDialog("Digite o novo endereço do cliente:", cliente.getEndereco());

            cliente = new Cliente(nome, endereco);
            clientes.set(selectedIndex, cliente);
            listModel.set(selectedIndex, cliente);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
        }
    }

    private void excluirCliente() {
        int selectedIndex = clientesList.getSelectedIndex();

        if (selectedIndex != -1) {
            clientes.remove(selectedIndex);
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
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