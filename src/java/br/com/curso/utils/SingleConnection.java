/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Aluno
 */
public class SingleConnection {
    private static Connection conexao = null;
    private static String servidor = "jdbc:postgresql://localhost:5432/AplEstoque?autoReconnect=true";
    private static String usuario = "postgres";
    private static String senha = "mazin123";
    
    static {
        try {
            conectar();
        }catch (Exception ex){
            System.out.println("Erro ao Conectar ao Banco de dados");
            ex.printStackTrace();
        }
    }
    
    public SingleConnection()throws Exception{
        conectar();
    }
    
    public static void conectar() throws Exception {
        try {
         if (conexao == null) {
             Class.forName("org.postgresql.Driver");
             conexao = DriverManager.getConnection(servidor, usuario, senha);
             conexao.setAutoCommit (false);
         }
        }catch (Exception ex) {
            throw new Exception (ex.getMessage());
        }
    }
    
    public static Connection getConnection(){
        return conexao;
    }
}
