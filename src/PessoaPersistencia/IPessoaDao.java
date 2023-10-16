/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PessoaPersistencia;

import PessoaControle.IPessoaControle;
import PessoaControle.PessoaControle;
import PessoaModelo.PessoaModelo;
import PessoaPersistencia.PessoaDao;
import TelaPessoa.PessoaTela;

import java.util.List;

public interface IPessoaDao {
    PessoaModelo adicionarPessoa(String nome, byte[] imagemBytes);
    boolean atualizarPessoa(int id, String novoNome);
    boolean removerPessoa(int id);
    List<PessoaModelo> listarPessoa();
    List<String> obterTodasPessoas();
    int obterIdPessoaPeloIndice(int indice);
}
