/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PessoaControle;

import PessoaControle.IPessoaControle;
import PessoaModelo.PessoaModelo;
import PessoaPersistencia.IPessoaDao;
import PessoaPersistencia.PessoaDao;
import TelaPessoa.PessoaTela;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PessoaControle implements IPessoaControle {
    private IPessoaDao pessoaDao;
    private DefaultTableModel tableModel;

    public PessoaControle(IPessoaDao pessoaDao, DefaultTableModel tableModel) {
        this.pessoaDao = pessoaDao;
        this.tableModel = tableModel;}

    public void adicionarPessoa(String nome, byte[] imagemBytes) {
        PessoaModelo pessoa = pessoaDao.adicionarPessoa(nome,imagemBytes);
        if (pessoa != null) {
            tableModel.addRow(new Object[]{pessoa.getId(), pessoa.getNome(), pessoa.getImagemBytes()});}
        else {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar Pessoa");}}

    public void atualizarPessoa(int id, String novoNome) {
        if (pessoaDao.atualizarPessoa(id, novoNome)) {
            int rowIndex = getRowIndexById(id);
                if (rowIndex != -1) {
                    tableModel.setValueAt(novoNome, rowIndex, 1);}} 
        else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Pessoa");}}

    public void removerPessoa(int id) {
        if (pessoaDao.removerPessoa(id)) {
            int rowIndex = getRowIndexById(id);
                if (rowIndex != -1) {
                    tableModel.removeRow(rowIndex);}} 
        else {
            JOptionPane.showMessageDialog(null, "Erro ao remover Pessoa");}}
    
    public PessoaModelo buscarPessoa(String nome, byte[] imagemBytes) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String rowNome = (String) tableModel.getValueAt(i, 1);
                if (rowNome.equals(nome)) {
                    int id = (int) tableModel.getValueAt(i, 0);
                    String url = (String) tableModel.getValueAt(i, 2);
                    return new PessoaModelo(id, nome, imagemBytes);}}
    return null;}

    private int getRowIndexById(int id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
                int rowId = (int) tableModel.getValueAt(i, 0);
                if (rowId == id) {
                return i;}}
    return -1;}
}
