package br.com.curso.dao;

import br.com.curso.model.Intensity;
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
public class IntensityDAO implements GenericDAO {
    
    private Connection conexao;
    
    public IntensityDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Intensity oIntensity= (Intensity) objeto;
        Boolean retorno=false;
        if (oIntensity.getId() == 0) {
            retorno = this.inserir(oIntensity);
        } else{
            retorno = this.alterar(oIntensity);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Intensity oIntensity = (Intensity) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into intensity (nameIntensity) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oIntensity.getNameIntensity());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar a Intensidade! Erro: " + ex.getMessage());
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
        Intensity oIntensity = (Intensity) objeto;
        PreparedStatement stmt = null;
        String sql = "update intensity set nameIntensity=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oIntensity.getNameIntensity());
            stmt.setInt(2, oIntensity.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar a Intensidade! Erro: " + ex.getMessage());
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
        
        String sql = "delete from intensity where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir a Intensidade! Erro: " + ex.getMessage());
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
        Intensity oIntensity = null;
        String sql = "select * from intensity where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oIntensity = new Intensity();
                oIntensity.setId(rs.getInt("id"));
                oIntensity.setNameIntensity(rs.getString("nameIntensity"));
            }
            return oIntensity;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Intensidade! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from intensity order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Intensity oIntensity = new Intensity();
                oIntensity.setId(rs.getInt("id"));
                oIntensity.setNameIntensity(rs.getString("nameIntensity"));
                resultado.add(oIntensity);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Intensidade! Erro: " +ex.getMessage());
        }
        return resultado;
    }
    
}
