/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Funcionario;
import br.com.curso.model.MovimentoEstoque;
import br.com.curso.model.Produto;
import br.com.curso.model.TipoMovimento;
import br.com.curso.utils.SingleConnection;
import br.com.curso.dao.ProdutoDAO;
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
public class MovimentoEstoqueDAO implements GenericDAO{
    
    private Connection conexao;

    public MovimentoEstoqueDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        MovimentoEstoque oMovimentoEstoque = (MovimentoEstoque) objeto;
            Boolean retorno = false;
                if (oMovimentoEstoque.getIdMovimento()== 0) {
                    retorno = this.inserir(oMovimentoEstoque);
                } else {
                    retorno = this.alterar(oMovimentoEstoque);
                }
                  return retorno;    
    }

    @Override
    public Boolean inserir(Object objeto) {
        MovimentoEstoque oMovimentoEstoque = (MovimentoEstoque) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into MovimentoEstoque (entradaSaida, documento, data, quantidade, valorMovimento, idProduto, idTipoMovimento, idFuncionario) values(?,?,?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMovimentoEstoque.getEntradaSaida());
            stmt.setString(2, oMovimentoEstoque.getDocumento());
            stmt.setString(3, oMovimentoEstoque.getData());
            stmt.setInt(4, oMovimentoEstoque.getQuantidade());
            stmt.setDouble(5, oMovimentoEstoque.getValorMovimento());
            stmt.setInt(6, oMovimentoEstoque.getProduto().getIdProduto());
            stmt.setInt(7, oMovimentoEstoque.getTipoMovimento().getIdTipoMovimento());
            stmt.setInt(8, oMovimentoEstoque.getFuncionario().getIdFuncionario());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao cadastrar o Movimento de Estoque: "+ex.getMessage());
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
    MovimentoEstoque oMovimentoEstoque = (MovimentoEstoque) objeto;
        PreparedStatement stmt = null;
        String sql = "update MovimentoEstoque set entradaSaida=?,documento=?, data=?, quantidade=?, valorMovimento=?, idProduto=?, idTipoMovimento=?, idFuncionario=? where idMovimento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMovimentoEstoque.getEntradaSaida());
            stmt.setString(2, oMovimentoEstoque.getDocumento());
            stmt.setString(3, oMovimentoEstoque.getData());
            stmt.setInt(4, oMovimentoEstoque.getQuantidade());
            stmt.setDouble(5, oMovimentoEstoque.getValorMovimento());
            stmt.setInt(6, oMovimentoEstoque.getProduto().getIdProduto());
            stmt.setInt(7, oMovimentoEstoque.getTipoMovimento().getIdTipoMovimento());
            stmt.setInt(8, oMovimentoEstoque.getFuncionario().getIdFuncionario());
            stmt.setInt(9, oMovimentoEstoque.getIdMovimento());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar o Movimento de Estoque! Erro:");
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
        int idMovimento = numero;
            PreparedStatement stmt = null;
        
            String sql = "delete from MovimentoEstoque where idMovimento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMovimento);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Movimento do Estoque! Erro: "
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
    int idMovimento = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MovimentoEstoque oMovimentoEstoque = null;
        String sql = "select * from MovimentoEstoque where idMovimento=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMovimento);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oMovimentoEstoque = new MovimentoEstoque();
                oMovimentoEstoque.setIdMovimento(rs.getInt("idMovimento"));
                oMovimentoEstoque.setEntradaSaida(rs.getString("entradaSaida"));
                oMovimentoEstoque.setDocumento(rs.getString("documento"));
                oMovimentoEstoque.setData(rs.getString("data"));
                oMovimentoEstoque.setQuantidade(rs.getInt("quantidade"));
                oMovimentoEstoque.setValorMovimento(rs.getInt("valorMovimento"));
                
                ProdutoDAO oProdutoDAO = new ProdutoDAO();
                oMovimentoEstoque.setProduto((Produto) oProdutoDAO.carregar(rs.getInt("idProduto")));
                
                TipoMovimentoDAO oTipoMovimentoDAO = new TipoMovimentoDAO();
                oMovimentoEstoque.setTipoMovimento((TipoMovimento) oTipoMovimentoDAO.carregar(rs.getInt("idTipoMovimento")));
                
                FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
                oMovimentoEstoque.setFuncionario((Funcionario) oFuncionarioDAO.carregar(rs.getInt("idFuncionario")));
            }
            return oMovimentoEstoque;
        } catch (Exception ex){
            System.out.println("Problemas ao carregar Movimento de Estoque!"+ex.getMessage());
            return false;
        }     
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from MovimentoEstoque order by idMovimento";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                MovimentoEstoque oMovimentoEstoque = new MovimentoEstoque();
                oMovimentoEstoque.setIdMovimento(rs.getInt("idMovimento"));
                oMovimentoEstoque.setEntradaSaida(rs.getString("entradaSaida"));
                oMovimentoEstoque.setDocumento(rs.getString("documento"));
                oMovimentoEstoque.setData(rs.getString("data"));
                oMovimentoEstoque.setQuantidade(rs.getInt("quantidade"));
                oMovimentoEstoque.setValorMovimento(rs.getInt("valorMovimento"));
                
                ProdutoDAO oProdutoDAO = null;
                
                try{
                    oProdutoDAO = new ProdutoDAO();
                }catch (Exception ex) {
                    System.out.println("Erro buscar Movimento"+ex.getMessage());
                    ex.printStackTrace();
                }
                oMovimentoEstoque.setProduto((Produto) oProdutoDAO.carregar(rs.getInt("idProduto")));
                
                TipoMovimentoDAO oTipoMovimentoDAO = null;
                try{
                    oTipoMovimentoDAO = new TipoMovimentoDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar Movimento"+ex.getMessage());
                    ex.printStackTrace();
                }
                oMovimentoEstoque.setTipoMovimento((TipoMovimento) oTipoMovimentoDAO.carregar(rs.getInt("idTipoMovimento")));
                
                FuncionarioDAO oFuncionarioDAO = null;
                try{
                    oFuncionarioDAO = new FuncionarioDAO();
                }catch (Exception ex){
                     System.out.println("Erro buscar Movimento"+ex.getMessage());
                    ex.printStackTrace();
                }
                oMovimentoEstoque.setFuncionario((Funcionario) oFuncionarioDAO.carregar(rs.getInt("idFuncionario")));
                resultado.add(oMovimentoEstoque);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Movimento de Estoque! Erro: " +ex.getMessage());
        }
        return resultado;    
    }
    
}
