/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.movimentoEstoque;

import br.com.curso.dao.FuncionarioDAO;
import br.com.curso.dao.ProdutoDAO;
import br.com.curso.dao.TipoMovimentoDAO;
import br.com.curso.model.MovimentoEstoque;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 55179
 */
@WebServlet(name = "MovimentoEstoqueNovo", urlPatterns = {"/MovimentoEstoqueNovo"})
public class MovimentoEstoqueNovo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception{
        response.setContentType("text/html;charset=UTF-8");
        MovimentoEstoque oMovimentoEstoque = new MovimentoEstoque();
        request.setAttribute("movimentoEstoque", oMovimentoEstoque);
        ProdutoDAO oProdutoDAO = new ProdutoDAO();
        request.setAttribute("produtos", oProdutoDAO.listar());
        TipoMovimentoDAO oTipoMovimentoDAO = new TipoMovimentoDAO();
        request.setAttribute("tipoMovimentos",  oTipoMovimentoDAO.listar());
        FuncionarioDAO oFuncionarioDAO = new FuncionarioDAO();
        request.setAttribute("funcionarios", oFuncionarioDAO.listar());
        request.getRequestDispatcher("/cadastros/movimentoEstoque/movimentoEstoqueCadastrar.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MovimentoEstoqueNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MovimentoEstoqueNovo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
