/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

/**
 *
 * @author 55179
 */
public class Produto {
    private int idProduto;
    private String nomeProduto;
    private int ultimoPrecoPago;
    private int saldoAtual;
    private TipoProduto TipoProduto;
    private UnidadeMedida UnidadeMedida;

    public Produto() {
        this.idProduto = 0;
        this.nomeProduto = "";
        this.ultimoPrecoPago = 0;
        this.saldoAtual = 0;
        this.TipoProduto = new TipoProduto();
        this.UnidadeMedida = new UnidadeMedida();
    }

    public Produto(int idProduto, String nomeProduto, int ultimoPrecoPago, int saldoAtual, TipoProduto TipoProduto, UnidadeMedida UnidadeMedida) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.ultimoPrecoPago = ultimoPrecoPago;
        this.saldoAtual = saldoAtual;
        this.TipoProduto = TipoProduto;
        this.UnidadeMedida = UnidadeMedida;
    }

    public Produto(int id_Produto, String string) {
        this.idProduto = id_Produto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getUltimoPrecoPago() {
        return ultimoPrecoPago;
    }

    public void setUltimoPrecoPago(int ultimoPrecoPago) {
        this.ultimoPrecoPago = ultimoPrecoPago;
    }

    public int getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(int saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public TipoProduto getTipoProduto() {
        return TipoProduto;
    }

    public void setTipoProduto(TipoProduto TipoProduto) {
        this.TipoProduto = TipoProduto;
    }

    public UnidadeMedida getUnidadeMedida() {
        return UnidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida UnidadeMedida) {
        this.UnidadeMedida = UnidadeMedida;
    }

    
}
