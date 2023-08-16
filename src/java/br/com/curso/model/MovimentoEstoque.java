/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

/**
 *
 * @author 55179
 */
public class MovimentoEstoque {
    private int idMovimento;
    private String entradaSaida;
    private String documento;
    private String data;
    private int quantidade;
    private double valorMovimento;
    private Produto Produto;
    private TipoMovimento TipoMovimento;
    private Funcionario Funcionario;

    public MovimentoEstoque() {
        this.idMovimento = 0;
        this.entradaSaida = "";
        this.documento = "";
        this.data = "";
        this.quantidade = 0;
        this.valorMovimento = 0;
        this.Produto = new Produto();
        this.TipoMovimento = new TipoMovimento();
        this.Funcionario = new Funcionario();
    }

    public MovimentoEstoque(int idMovimento, String entradaSaida, String documento, String data, int quantidade, double valorMovimento, Produto Produto, TipoMovimento TipoMovimento, Funcionario Funcionario) {
        this.idMovimento = idMovimento;
        this.entradaSaida = entradaSaida;
        this.documento = documento;
        this.data = data;
        this.quantidade = quantidade;
        this.valorMovimento = valorMovimento;
        this.Produto = Produto;
        this.TipoMovimento = TipoMovimento;
        this.Funcionario = Funcionario;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public String getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(String entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(double valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto Produto) {
        this.Produto = Produto;
    }

    public TipoMovimento getTipoMovimento() {
        return TipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento TipoMovimento) {
        this.TipoMovimento = TipoMovimento;
    }

    public Funcionario getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(Funcionario Funcionario) {
        this.Funcionario = Funcionario;
    }

    
}
