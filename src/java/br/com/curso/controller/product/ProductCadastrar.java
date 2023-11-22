package br.com.curso.controller.product;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.ProductDAO;
import br.com.curso.model.Product;
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
@WebServlet(name = "ProductCadastrar", urlPatterns = {"/ProductCadastrar"})
public class ProductCadastrar extends HttpServlet {

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
        String barcode = request.getParameter("barcode");
        String brand = request.getParameter("brand");
        String nameProduct = request.getParameter("nameProduct");
        String mensagem = null;
        
        Product oProduct = new Product();
        oProduct.setId(id);
        oProduct.setNameProduct(nameProduct);
        oProduct.setBarcode(barcode);
        oProduct.setBrand(brand);
        try{
            GenericDAO dao = new ProductDAO();
            if (dao.cadastrar(oProduct)) {
                mensagem = "Produto cadastrado com sucesso!";
            } else{
                mensagem = "Problemas ao cadastrar Produto. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            response.sendRedirect("ProductListar");
        } catch(Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Produto! Erro: " + ex.getMessage());
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
