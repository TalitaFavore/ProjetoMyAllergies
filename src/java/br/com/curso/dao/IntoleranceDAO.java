package br.com.curso.dao;

import br.com.curso.model.Intolerance;
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
public class IntoleranceDAO implements GenericDAO {
    
    private Connection conexao;
    
    public IntoleranceDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Intolerance oIntolerance= (Intolerance) objeto;
        Boolean retorno=false;
        if (oIntolerance.getId() == 0) {
            retorno = this.inserir(oIntolerance);
        } else{
            retorno = this.alterar(oIntolerance);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Intolerance oIntolerance = (Intolerance) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into intolerance (nameIntolerance) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oIntolerance.getNameIntolerance());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar a Intolerância! Erro: " + ex.getMessage());
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
        Intolerance oIntolerance = (Intolerance) objeto;
        PreparedStatement stmt = null;
        String sql = "update intolerance set nameIntolerance=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oIntolerance.getNameIntolerance());
            stmt.setInt(2, oIntolerance.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar a Intolerância! Erro: " + ex.getMessage());
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
        
        String sql = "delete from intolerance where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir a Intolerância! Erro: " + ex.getMessage());
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
        Intolerance oIntolerance = null;
        String sql = "select id, nameIntolerance from intolerance where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oIntolerance = new Intolerance();
                oIntolerance.setId(rs.getInt("id"));
                oIntolerance.setNameIntolerance(rs.getString("nameIntolerance"));
            }
            return oIntolerance;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Intolerância! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select id, nameIntolerance from intolerance order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Intolerance oIntolerance = new Intolerance();
                oIntolerance.setId(rs.getInt("id"));
                oIntolerance.setNameIntolerance(rs.getString("nameIntolerance"));
                resultado.add(oIntolerance);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Intolerância! Erro: " +ex.getMessage());
        }
        return resultado;
    }
}
