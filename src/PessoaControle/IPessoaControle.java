/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PessoaControle;

import PessoaControle.IPessoaControle;
import PessoaModelo.PessoaModelo;
import PessoaPersistencia.IPessoaDao;
import PessoaPersistencia.PessoaDao;
import TelaPessoa.PessoaTela;

// Classe Principal da Interface
public interface IPessoaControle {
    //Metodo Adicionar Pessoa, faz adiçao da Varival String nome no banco de dados
    void adicionarPessoa(String nome);
    //Metodo Atualizar Pessoa
    void atualizarPessoa(int id, String novoNome);
    //Metodo Remover Pessoa, faz a pesquisa pelo id
    void removerPessoa(int id);
    //Metodo Buscar Pessoa, faz a busca pela String Nome
    PessoaModelo buscarPessoa(String nome);
}
