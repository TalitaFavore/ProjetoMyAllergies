package br.com.curso.dao;

import br.com.curso.model.Allergy;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */

public class AllergyDAO implements GenericDAO {
    
    private Connection conexao;
    
    public AllergyDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Allergy oAllergy= (Allergy) objeto;
        Boolean retorno=false;
        if (oAllergy.getId() == 0) {
            retorno = this.inserir(oAllergy);
        } else{
            retorno = this.alterar(oAllergy);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Allergy oAllergy = (Allergy) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into allergy (nameAllergy) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oAllergy.getNameAllergy());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar a Alergia! Erro: " + ex.getMessage());
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
        Allergy oAllergy = (Allergy) objeto;
        PreparedStatement stmt = null;
        String sql = "update allergy set nameAllergy=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oAllergy.getNameAllergy());
            stmt.setInt(2, oAllergy.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar a Alergia! Erro: " + ex.getMessage());
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
        
        String sql = "delete from allergy where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir a Alergia! Erro: " + ex.getMessage());
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
        Allergy oAllergy = null;
        String sql = "select id, nameallergy from allergy where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oAllergy = new Allergy();
                oAllergy.setId(rs.getInt("id"));
                oAllergy.setNameAllergy(rs.getString("nameAllergy"));
            }
            return oAllergy;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Alergia! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select id, nameAllergy from allergy order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Allergy oAllergy = new Allergy();
                oAllergy.setId(rs.getInt("id"));
                oAllergy.setNameAllergy(rs.getString("nameAllergy"));
                resultado.add(oAllergy);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Alergia! Erro: " +ex.getMessage());
        }
        return resultado;
    }
}
 




   
