package TelaPessoa;

import PessoaModelo.PessoaModelo;
import PessoaPersistencia.IPessoaDao;
import PessoaPersistencia.PessoaDao;
import PessoaControle.PessoaControle;
import PessoaControle.IPessoaControle;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TelaPessoa {
    private JPanel panelMain;
    private JButton btn_Salvar;
    private JTextField txt_Nome;
    private JButton excluirButton;
    private JButton btn_Alterar;
    private JLabel JLabelLogo;
    private JTable tabelaPessoas;
    private JButton btn_Add;
    private JPanel JPanelLogo;
    private JPanel JPanelOpção;
    private JPanel JPanelTabela;
    private JButton btn_Novo;
    private JButton btn_Buscar;
    byte[] imagemBytes = null;

    int indice=0;

    // Injeção de Dependencia do código.
    IPessoaDao pessoaDao = new PessoaDao();
    DefaultTableModel tableModel = (DefaultTableModel) tabelaPessoas.getModel();

    public TelaPessoa() {

        CriarTabela();
        atualizaForm();

        btn_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    // Abre no diretorio especifico
                    fc.setCurrentDirectory(new File("./src/PessoasFotos"));

                    // Abre a janela do diretorio do arquivo
                    fc.showOpenDialog(null);
                    // Salva o arquivo selecionado em um arquivo novo
                    File arquivo = fc.getSelectedFile();

                    // Salva o binario da imagem e fecha o InputStream
                    try (FileInputStream inputStream = new FileInputStream(arquivo)) {
                        imagemBytes = inputStream.readAllBytes();}

                    // Salva o Caminho do diretorio
                    String nomeDoArquivo = arquivo.getPath();
                    ImageIcon iconLogo = new ImageIcon(nomeDoArquivo);
                    JLabelLogo.setSize(100, 100);
                    iconLogo.setImage(iconLogo.getImage().getScaledInstance(
                            JLabelLogo.getWidth(), JLabelLogo.getHeight(),1));
                    JLabelLogo.setIcon(iconLogo);}
                catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro);}
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
                // Captura a imagem do JLabel
                ImageIcon icon = (ImageIcon) JLabelLogo.getIcon();
                BufferedImage bufferedImage = new BufferedImage(
                        icon.getIconWidth(),
                        icon.getIconHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

                // Escreve a BufferedImage em um ByteArrayOutputStream
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    ImageIO.write(bufferedImage, "png", baos);}
                catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro);}
                // Converte o ByteArrayOutputStream em um array de bytes
                imagemBytes = baos.toByteArray();

                if(!txt_Nome.getText().equals("")){
                    String nomePessoa = txt_Nome.getText().toUpperCase(); // Supondo que jTextFieldNomePessoa seja o campo de entrada para o nome da Pessoa
                    int selectedRow = tabelaPessoas.getSelectedRow();
                    if (selectedRow != -1) {
                        int idNome = (int) tabelaPessoas.getValueAt(selectedRow, 0);
                        IPessoaControle pessoaControle = new PessoaControle(pessoaDao, (DefaultTableModel) tabelaPessoas.getModel());
                        pessoaControle.atualizarPessoa(idNome,nomePessoa,imagemBytes);}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Campos vazios !");}
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txt_Nome.getText().equals("")){
                    int selectedRow = tabelaPessoas.getSelectedRow();
                    if (selectedRow != -1) {
                        int idNome = (int) tabelaPessoas.getValueAt(selectedRow, 0);
                        IPessoaControle pessoaControle = new PessoaControle(pessoaDao, (DefaultTableModel) tabelaPessoas.getModel());
                        pessoaControle.removerPessoa(idNome);
                        txt_Nome.setText("");
                        JLabelLogo.setIcon(null);}
                }
                else{
                    JOptionPane.showMessageDialog(null, "Campos vazios !");}
            }
        });
        tabelaPessoas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int selectedRow = tabelaPessoas.getSelectedRow();
                int idNome = 0;
                if (selectedRow != -1) {
                    idNome = (int) tabelaPessoas.getValueAt(selectedRow, 0);
                    String pessoaSelecionada = (String) tabelaPessoas.getValueAt(selectedRow, 1);
                    txt_Nome.setText(pessoaSelecionada);
                }

                PessoaControle controle = new PessoaControle(pessoaDao, tableModel);

                PessoaModelo foto = controle.buscarPorNome(idNome);

                if (foto != null) {
                    ImageIcon image = new ImageIcon(foto.getImagemBytes());
                    // Carregar a imagem
                    Image imag = image.getImage();
                    Image scaledImage = imag.getScaledInstance(JLabelLogo.getWidth(), JLabelLogo.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    // Definir o ícone do JLabel
                    JLabelLogo.setIcon(scaledIcon);
                } else {
                    JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
                }
            }
        });
        btn_Novo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_Nome.setText("");
                tabelaPessoas.clearSelection();
                JLabelLogo.setIcon(null);
            }
        });
        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

    public void atualizaForm(){
        txt_Nome.setText("");
        TitledBorder border = BorderFactory.createTitledBorder("Foto");
        JPanelLogo.setBorder(border);
        JPanelLogo.setPreferredSize(new Dimension(100, 100));
        JPanelLogo.setLayout(new GridBagLayout()); // Define o layout para GridBagLayout

        JLabelLogo.setPreferredSize(new Dimension(100, 100));
        JLabelLogo.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        JLabelLogo.setVerticalAlignment(SwingConstants.CENTER); // Centraliza verticalmente

        TitledBorder borderOp = BorderFactory.createTitledBorder("Opções");
        JPanelOpção.setBorder(borderOp);
        JPanelOpção.setPreferredSize(new Dimension(50, 50));

        TitledBorder Tabela = BorderFactory.createTitledBorder("Tabela");
        JPanelTabela.setBorder(Tabela);
        JPanelTabela.setPreferredSize(new Dimension(50, 50));
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
