/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.TipoProduto;
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
public class TipoProdutoDAO implements GenericDAO{

    private Connection conexao;

    public TipoProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        TipoProduto oTipoProduto = (TipoProduto) objeto;
            Boolean retorno = false;
                if (oTipoProduto.getIdTipoProduto()== 0) {
                    retorno = this.inserir(oTipoProduto);
                } else {
                    retorno = this.alterar(oTipoProduto);
                }
                  return retorno;    
    }

    @Override
    public Boolean inserir(Object objeto) {
        TipoProduto oTipoProduto = (TipoProduto) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into TipoProduto (descricao) values(?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoProduto.getDescricao());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao cadastrar o TipoProduto: "+ex.getMessage());
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
    TipoProduto oTipoProduto = (TipoProduto) objeto;
        PreparedStatement stmt = null;
        String sql = "update TipoProduto set descricao=? where idTipoProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoProduto.getDescricao());
            stmt.setInt(2, oTipoProduto.getIdTipoProduto());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar o Tipo Produto! Erro:");
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
        int idTipoProduto = numero;
            PreparedStatement stmt = null;
        
            String sql = "delete from TipoProduto where idTipoProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipoProduto);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Tipo Produto! Erro: "
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
    int idTipoProduto = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoProduto oTipoProduto = null;
        String sql = "select * from TipoProduto where idTipoProduto=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipoProduto);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oTipoProduto = new TipoProduto();
                oTipoProduto.setIdTipoProduto(rs.getInt("idTipoProduto"));
                oTipoProduto.setDescricao(rs.getString("descricao"));
            }
            return oTipoProduto;
        } catch (Exception ex){
            System.out.println("Problemas ao carregar TipoProduto!"+ex.getMessage());
            return false;
        }     
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from TipoProduto order by idTipoProduto";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                TipoProduto oTipoProduto = new TipoProduto();
                oTipoProduto.setIdTipoProduto(rs.getInt("idTipoProduto"));
                oTipoProduto.setDescricao(rs.getString("descricao"));
            resultado.add(oTipoProduto);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Tipo Produto! Erro: " +ex.getMessage());
        }
        return resultado;    
    }
}
