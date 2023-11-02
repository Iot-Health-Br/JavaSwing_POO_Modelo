package TelaPessoa;

import PessoaModelo.PessoaModelo;
import PessoaPersistencia.IPessoaDao;
import PessoaPersistencia.PessoaDao;
import PessoaControle.PessoaControle;
import PessoaControle.IPessoaControle;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TelaPessoa {
    private JPanel panelMain;
    private JButton btn_Salvar;
    private JTextField txt_Nome;
    private JButton excluirButton;
    private JButton btn_Alterar;
    private JLabel Panel_Logo;
    private JTable tabelaPessoas;
    private JButton btn_Add;
    byte[] imagemBytes = null;

    IPessoaDao pessoaDao = new PessoaDao();

    public TelaPessoa() {

        CriarTabela();
        fieldLogo();
        txt_Nome.setText("");

        btn_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    // Abre no diretorio especifico
                    fc.setCurrentDirectory(new File("./src/PessoasFotos"));

                    // Abre a janela do diretorio do arquivo
                    //fc.showOpenDialog(this);
                    fc.showOpenDialog(null);
                    // Salva o arquivo selecionado em um arquivo novo
                    File arquivo = fc.getSelectedFile();

                    // Salva o binario da imagem e fecha o InputStream
                    try (FileInputStream inputStream = new FileInputStream(arquivo)) {
                        imagemBytes = inputStream.readAllBytes();}

                    // Salva o Caminho do diretorio
                    String nomeDoArquivo = arquivo.getPath();

                    ImageIcon iconLogo = new ImageIcon(nomeDoArquivo);
                    iconLogo.setImage(iconLogo.getImage().getScaledInstance(
                            Panel_Logo.getWidth(),Panel_Logo.getHeight(),1));
                    Panel_Logo.setSize(300, 400);
                    Panel_Logo.setIcon(iconLogo);
                }
                catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro);
                }
            }
        });
        btn_Salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txt_Nome.getText().equals("")){
                    String nomePessoa = txt_Nome.getText().toUpperCase(); // Supondo que jTextFieldNomePessoa seja o campo de entrada para o nome da Pessoa
                    IPessoaDao pessoaDao = new PessoaDao();
                    IPessoaControle pessoaControle = new PessoaControle(pessoaDao, (DefaultTableModel) tabelaPessoas.getModel());
                    pessoaControle.adicionarPessoa(nomePessoa,imagemBytes);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Campos vazios !");}
            }
        });
        btn_Alterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tabelaPessoas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int selectedRow = tabelaPessoas.getSelectedRow();
                if (selectedRow != -1) {
                    String pessoaSelecionada = (String) tabelaPessoas.getValueAt(selectedRow, 1);
                    txt_Nome.setText(pessoaSelecionada);}
                String nome = txt_Nome.getText().toUpperCase();

                IPessoaDao pessoaDao = new PessoaDao();
                DefaultTableModel tableModel = (DefaultTableModel) tabelaPessoas.getModel();
                PessoaControle controle = new PessoaControle(pessoaDao, tableModel);

                PessoaModelo foto = controle.buscarPorNome(nome);

                if (foto != null) {
                    ImageIcon image = new ImageIcon(foto.getImagemBytes());
                    Panel_Logo.setIcon(image);
                    Panel_Logo.setSize(300, 400);}
                else {
                    JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");}
            }
        });
    }

    public void CriarTabela(){
        tabelaPessoas.setModel(new DefaultTableModel(
                null,
                new String[]{"id","nome"}
        ));
        TableColumnModel column = tabelaPessoas.getColumnModel();
        column.getColumn(0).setMinWidth(10);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        column.getColumn(1).setCellRenderer(centerRenderer);


        DefaultTableModel tableModel = (DefaultTableModel) tabelaPessoas.getModel();
        // Limpar dados existentes na tabela
        tableModel.setRowCount(0);
        // Obter lista de Pessoas do banco de dados
        List<PessoaModelo> pessoas = pessoaDao.listarPessoa();
        // Preencher tabela com os dados das Pessoas
        for (PessoaModelo pessoa : pessoas) {
            Object[] rowData = {pessoa.getId(), pessoa.getNome(), pessoa.getImagemBytes()};
            tableModel.addRow(rowData);}
    }

    public void fieldLogo(){
        Panel_Logo.setMinimumSize(new Dimension(300,300));
        Panel_Logo.setHorizontalAlignment(JLabel.CENTER);
        TitledBorder border = BorderFactory.createTitledBorder("Título da Borda");
        Panel_Logo.setBorder(border);
    }

    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Tela de Cadastro");
                frame.setContentPane(new TelaPessoa().panelMain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
    }
}
