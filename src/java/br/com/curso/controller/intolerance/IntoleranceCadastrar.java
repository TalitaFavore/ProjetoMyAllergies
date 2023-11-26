package br.com.curso.controller.intolerance;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.IntoleranceDAO;
import br.com.curso.model.Intolerance;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Talita
 */
@WebServlet(name = "IntoleranceCadastrar", urlPatterns = {"/IntoleranceCadastrar"})
public class IntoleranceCadastrar extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nameIntolerance = request.getParameter("nameIntolerance");
        String mensagem = null;
        
        Intolerance oIntolerance = new Intolerance();
        oIntolerance.setId(id);
        oIntolerance.setNameIntolerance(nameIntolerance);
        try{
            GenericDAO dao = new IntoleranceDAO();
            if (dao.cadastrar(oIntolerance)) {
                mensagem = "Alergia cadastrada com sucesso!";
            } else{
                mensagem = "Problemas ao cadastrar Intolerância. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("IntoleranceListar");
        } catch(Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Intolerância! Erro: " + ex.getMessage());
        }
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
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
