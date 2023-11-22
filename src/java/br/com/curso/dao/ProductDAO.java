package br.com.curso.dao;

import br.com.curso.model.Product;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Talita
 */
public class ProductDAO implements GenericDAO {
    
    private Connection conexao;
    
    public ProductDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Product oProduct= (Product) objeto;
        Boolean retorno=false;
        if (oProduct.getId() == 0) {
            retorno = this.inserir(oProduct);
        } else{
            retorno = this.alterar(oProduct);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Product oProduct = (Product) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into product (barcode, brand, nameProduct) values (?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduct.getBarcode());
            stmt.setString(2, oProduct.getBrand());
            stmt.setString(3, oProduct.getNameProduct());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar o Produto! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();       
            } catch(SQLException e) {
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Product oProduct = (Product) objeto;
        PreparedStatement stmt = null;
        String sql = "update product set barcode=?, brand=?, nameProduct=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduct.getBarcode());
            stmt.setString(2, oProduct.getBrand());
            stmt.setString(3, oProduct.getNameProduct());
            stmt.setInt(4, oProduct.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar o Produto! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();       
            } catch(SQLException e) {
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int id = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from product where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Produto! Erro: " + ex.getMessage());
            try{
                conexao.rollback();       
            } catch(SQLException e) {
                System.out.println("Erro rollback: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int id = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Product oProduct = null;
        String sql = "select * from product where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oProduct = new Product();
                oProduct.setId(rs.getInt("id"));
                oProduct.setBarcode(rs.getString("barcode"));
                oProduct.setBrand(rs.getString("brand"));
                oProduct.setNameProduct(rs.getString("nameProduct"));
            }
            return oProduct;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar o Produto! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select id, barcode, brand, nameProduct from product order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Product oProduct = new Product();
                oProduct.setId(rs.getInt("id"));
                oProduct.setBarcode(rs.getString("barcode"));
                oProduct.setBrand(rs.getString("brand")); 
                oProduct.setNameProduct(rs.getString("nameProduct"));
                resultado.add(oProduct);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Produto! Erro: " +ex.getMessage());
        }
        return resultado;
    }
}