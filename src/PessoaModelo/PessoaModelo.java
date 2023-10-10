/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PessoaModelo;

import PessoaControle.IPessoaControle;
import PessoaPersistencia.IPessoaDao;
import PessoaPersistencia.PessoaDao;
import TelaPessoa.PessoaTela;

/**
 *
 * @author Igor
 */
public class PessoaModelo {
    private String nome;
    private int id;
    
    public PessoaModelo(int id,String nome){
        this.id = id;
        this.nome=nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
