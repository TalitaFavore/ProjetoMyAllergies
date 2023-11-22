package br.com.curso.controller.usuario;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.ProfileDAO;
import br.com.curso.model.Profile;
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
@WebServlet(name = "ProfileCadastrar", urlPatterns = {"/ProfileCadastrar"})
public class ProfileCadastrar extends HttpServlet {

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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String passcode = request.getParameter("passcode");
        String mensagem = null;
        
        Profile oProfile = new Profile();
        oProfile.setId(id);
        oProfile.setUsername(username);
        oProfile.setEmail(email);
        oProfile.setPasscode(passcode);
        try{
            GenericDAO dao = new ProfileDAO();
            if (dao.cadastrar(oProfile)) {
                mensagem = "Usuário cadastrado com sucesso!";
            } else{
                mensagem = "Problemas ao cadastrar Usuário. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("ProfileListar");
        } catch(Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Usuário! Erro: " + ex.getMessage());
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


