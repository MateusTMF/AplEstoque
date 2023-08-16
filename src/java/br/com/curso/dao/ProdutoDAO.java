/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Produto;
import br.com.curso.model.TipoProduto;
import br.com.curso.model.UnidadeMedida;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 55179
 */
public class ProdutoDAO implements GenericDAO{

    private Connection conexao;

    public ProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Produto oProduto = (Produto) objeto;
            Boolean retorno = false;
                if (oProduto.getIdProduto()== 0) {
                    retorno = this.inserir(oProduto);
                } else {
                    retorno = this.alterar(oProduto);
                }
                  return retorno;    
    }

    @Override
    public Boolean inserir(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into Produto (nomeProduto, ultimoPrecoPago, saldoAtual, idTipoProduto, idUnidadeMedida) values(?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setInt(2, oProduto.getUltimoPrecoPago());
            stmt.setInt(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getTipoProduto().getIdTipoProduto());
            stmt.setInt(5, oProduto.getUnidadeMedida().getIdUnidadeMedida());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao cadastrar o Produto: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        return false;
    }

    @Override
    public Boolean alterar(Object objeto) {
    Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "update Produto set nomeProduto=?,ultimoPrecoPago=?, saldoAtual=?, idTipoProduto=?, idUnidadeMedida=? where idProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setInt(2, oProduto.getUltimoPrecoPago());
            stmt.setInt(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getTipoProduto().getIdTipoProduto());
            stmt.setInt(5, oProduto.getUnidadeMedida().getIdUnidadeMedida());
            stmt.setInt(6, oProduto.getIdProduto());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar o Produto! Erro:");
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }    
    }

    @Override
    public Boolean excluir(int numero) {
        int idProduto = numero;
            PreparedStatement stmt = null;
        
            String sql = "delete from Produto where idProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Produto! Erro: "
                    +ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e){
                System.out.println("Erro rollback"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }    
    }

    @Override
    public Object carregar(int numero) {
    int idProduto = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto oProduto = null;
        String sql = "select * from Produto where idProduto=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setUltimoPrecoPago(rs.getInt("ultimoPrecoPago"));
                oProduto.setSaldoAtual(rs.getInt("saldoAtual"));
                
                TipoProdutoDAO oTipoProdutoDAO = new TipoProdutoDAO();
                oProduto.setTipoProduto((TipoProduto) oTipoProdutoDAO.carregar(rs.getInt("idTipoProduto")));
                
                UnidadeMedidaDAO oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                oProduto.setUnidadeMedida((UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida")));
            }
            return oProduto;
        } catch (Exception ex){
            System.out.println("Problemas ao carregar Produto!"+ex.getMessage());
            return false;
        }     
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from Produto order by idProduto";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Produto oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setUltimoPrecoPago(rs.getInt("ultimoPrecoPago"));
                oProduto.setSaldoAtual(rs.getInt("saldoAtual"));
                
                
                TipoProdutoDAO oTipoProdutoDAO = null;
                try{
                    oTipoProdutoDAO = new TipoProdutoDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar Produto"+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setTipoProduto((TipoProduto) oTipoProdutoDAO.carregar(rs.getInt("idTipoProduto")));
                
                UnidadeMedidaDAO oUnidadeMedidaDAO = null;
                try{
                    oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar Produto"+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setUnidadeMedida((UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida")));
                resultado.add(oProduto);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Produto! Erro: " +ex.getMessage());
        }
        return resultado;    
    }
    
}
