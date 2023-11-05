/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PessoaControle;

import PessoaModelo.PessoaModelo;

// Classe Principal da Interface
public interface IPessoaControle {

    //Metodo Adicionar Pessoa, faz adiçao da Varival String nome no banco de dados e os dados binario da Foto
        void adicionarPessoa(String nome, byte[] imagemBytes);

    //Metodo Atualizar Pessoa
        void atualizarPessoa(int id, String novoNome, byte[] novaFoto);

    //Metodo Remover Pessoa, faz a pesquisa pelo id
        void removerPessoa(int id);

        PessoaModelo buscarPorNome(String nome);

    //Metodo Buscar Pessoa, faz a busca pela String Nome
        PessoaModelo buscarPessoa(String nome);
}
