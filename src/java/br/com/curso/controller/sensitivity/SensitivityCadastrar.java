package br.com.curso.controller.sensitivity;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.SensitivityDAO;
import br.com.curso.model.Sensitivity;
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
@WebServlet(name = "SensitivityCadastrar", urlPatterns = {"/SensitivityCadastrar"})
public class SensitivityCadastrar extends HttpServlet {

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
        String nameSensitivity = request.getParameter("nameSensitivity");
        String mensagem = null;
        
        Sensitivity oSensitivity = new Sensitivity();
        oSensitivity.setId(id);
        oSensitivity.setNameSensitivity(nameSensitivity);
        try{
            GenericDAO dao = new SensitivityDAO();
            if (dao.cadastrar(oSensitivity)) {
                mensagem = "Sensitivity cadastrada com sucesso!";
            } else{
                mensagem = "Problemas ao cadastrar Sensitivity. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("SensitivityListar");
        } catch(Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Sensitivity! Erro: " + ex.getMessage());
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
