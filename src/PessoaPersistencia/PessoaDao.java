/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PessoaPersistencia;

import ConexaoBancoDeDados.DatabaseConnection;
import PessoaModelo.PessoaModelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PessoaDao implements IPessoaDao {
    private static final String TABELA_PESSOAS = "TabelaDePessoas";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_FOTO = "foto";

    public PessoaDao() {
        criarTabela();
    }

    private void criarTabela() {
        try (Connection conexao = DatabaseConnection.getConnection();
             Statement statement = conexao.createStatement()) {
             String query = String.format("CREATE TABLE IF NOT EXISTS %s (%s SERIAL PRIMARY KEY, %s VARCHAR(255)UNIQUE, %s BYTEA not null)",
             TABELA_PESSOAS, COLUNA_ID, COLUNA_NOME, COLUNA_FOTO);
             statement.executeUpdate(query);}
        
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela de Pessoa");}
    }

    public PessoaModelo adicionarPessoa(String nome, byte[] imagemBytes) {
        
        try (Connection conexao = DatabaseConnection.getConnection();

            // Verifica se a PESSOA já está cadastrada
             PreparedStatement verificacaoStatement = conexao.prepareStatement(
                    String.format("SELECT * FROM %s WHERE %s = ?", TABELA_PESSOAS, COLUNA_NOME));

            // Verifica se a FOTO DA PESSOA já está cadastrada
             PreparedStatement verificacaoStatementFoto = conexao.prepareStatement(
                     String.format("SELECT * FROM %s WHERE %s = ?", TABELA_PESSOAS, COLUNA_FOTO));
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

             // Inserir a PESSOA no banco de dados
            PreparedStatement insercaoStatement = conexao.prepareStatement(
            String.format("INSERT INTO %s (%s,%s) VALUES (?,?) ", TABELA_PESSOAS, COLUNA_NOME, COLUNA_FOTO),
            Statement.RETURN_GENERATED_KEYS)) {


                // Verificar se a (PESSOA) já está cadastrada
                verificacaoStatement.setString(1, nome);
                ResultSet resultSet = verificacaoStatement.executeQuery();

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "A pessoa já está cadastrada.");
                    return null;}

                // Verificar se a (FOTO DA PESSOA) já está cadastrada
                verificacaoStatementFoto.setBytes(1, imagemBytes);
                ResultSet resultSetFoto = verificacaoStatementFoto.executeQuery();

                if (resultSetFoto.next()) {
                    JOptionPane.showMessageDialog(null, "A foto da pessoa já está cadastrada.");
                    return null;}

                // Inserir a PESSOA no banco de dados
                insercaoStatement.setString(1, nome );
                insercaoStatement.setBytes(2, imagemBytes );
                int rowsAffected = insercaoStatement.executeUpdate();

                if (rowsAffected == 0) {
                    return null;}

                try (ResultSet generatedKeys = insercaoStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        return new PessoaModelo(id, nome, imagemBytes);}
                    else {
                        return null;}}
        } 
        catch (SQLException e) {
              e.printStackTrace();
              return null;}
    }

    public boolean atualizarPessoa(int id, String novoNome) {
        try (Connection conexao = DatabaseConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(
                 String.format("UPDATE %s SET %s = ? WHERE %s = ?", TABELA_PESSOAS, COLUNA_NOME, COLUNA_ID))) {
                    statement.setString(1, novoNome);
                    statement.setInt(2, id);
                    int rowsAffected = statement.executeUpdate();
                    return rowsAffected > 0;}
    
        catch (SQLException e) {
               e.printStackTrace();
               return false;}
    }


    public boolean removerPessoa(int id) {
        try (Connection conexao = DatabaseConnection.getConnection();
             PreparedStatement statement = conexao.prepareStatement(
             String.format("DELETE FROM %s WHERE %s = ?", TABELA_PESSOAS, COLUNA_ID))) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;}
        
        catch (SQLException e) {
               e.printStackTrace();
            return false;}
    }

    public List<PessoaModelo> listarPessoa() {
        List<PessoaModelo> pessoas = new ArrayList<>();

        try (Connection conexao = DatabaseConnection.getConnection();
             Statement statement = conexao.createStatement()) {
                String query = String.format("SELECT * FROM %s", TABELA_PESSOAS);
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                       int id = resultSet.getInt(COLUNA_ID);
                       String nome = resultSet.getString(COLUNA_NOME);
                       byte[] imagemBytes = resultSet.getBytes(COLUNA_FOTO);
                       PessoaModelo pessoa = new PessoaModelo(id, nome, imagemBytes);
                       pessoas.add(pessoa);}
            
            resultSet.close();}
        catch (SQLException e) {
            e.printStackTrace();}

    return pessoas;}
    
        public List<String> obterTodasPessoas() {
            List<String> pessoas = new ArrayList<>();
                try (Connection conexao = DatabaseConnection.getConnection();
                     Statement statement = conexao.createStatement();
                     ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s", COLUNA_NOME, TABELA_PESSOAS))) {
                        while (resultSet.next()) {
                               String pessoa = resultSet.getString(COLUNA_NOME);
                               pessoas.add(pessoa);}}
                catch (SQLException e) {
                      e.printStackTrace();}
        return pessoas;}
   

    public int obterIdPessoaPeloIndice(int indice) {
        int id = -1;
            try (Connection conexao = DatabaseConnection.getConnection();
                 PreparedStatement statement = conexao.prepareStatement(
                 String.format("SELECT %s FROM %s LIMIT 1 OFFSET ?", COLUNA_ID, TABELA_PESSOAS))){
                    statement.setInt(1, indice);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            id = resultSet.getInt(COLUNA_ID);}}}
            catch (SQLException e) {
                  e.printStackTrace();}
    return id;}
}