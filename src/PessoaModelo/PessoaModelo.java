/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PessoaModelo;

import java.util.Arrays;

/**
 *
 * @author Igor
 */
public class PessoaModelo {
    private String nome;
    private int id;
    private byte[] imagemBytes;
    
    public PessoaModelo(int id,String nome, byte[] imagemBytes){
        this.id = id;
        this.nome=nome;
        this.imagemBytes = imagemBytes;
    }
    public int getId() {
        return id;}
    public void setId(int id) {
        this.id = id;}
    public String getNome() {
        return nome;}
    public void setNome(String nome) {
        this.nome = nome;}
    public byte[] getImagemBytes() {
        return imagemBytes;}
    public void setImagemBytes(byte[] imagemBytes) {
        this.imagemBytes = imagemBytes;}

    // Verificar a real nescessidade do tostring
    @Override
    public String toString() {
        return "PessoaModelo{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", imagemBytes=" + Arrays.toString(imagemBytes) +
                '}';
    }
}
