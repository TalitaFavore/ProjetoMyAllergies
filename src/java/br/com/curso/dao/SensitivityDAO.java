package br.com.curso.dao;

import br.com.curso.model.Sensitivity;
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
public class SensitivityDAO implements GenericDAO {
    
    private Connection conexao;
    
    public SensitivityDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Sensitivity oSensitivity= (Sensitivity) objeto;
        Boolean retorno=false;
        if (oSensitivity.getId() == 0) {
            retorno = this.inserir(oSensitivity);
        } else{
            retorno = this.alterar(oSensitivity);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Sensitivity oSensitivity = (Sensitivity) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into sensitivity (nameSensitivity) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oSensitivity.getNameSensitivity());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar a Sensibilidade! Erro: " + ex.getMessage());
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
        Sensitivity oSensitivity = (Sensitivity) objeto;
        PreparedStatement stmt = null;
        String sql = "update sensitivity set nameSensitivity=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oSensitivity.getNameSensitivity());
            stmt.setInt(2, oSensitivity.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar a Sensibilidade! Erro: " + ex.getMessage());
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
        
        String sql = "delete from sensitivity where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir a Sensibilidade! Erro: " + ex.getMessage());
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
        Sensitivity oSensitivity = null;
        String sql = "select id, nameSensitivity from sensitivity where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oSensitivity = new Sensitivity();
                oSensitivity.setId(rs.getInt("id"));
                oSensitivity.setNameSensitivity(rs.getString("nameSensitivity"));
            }
            return oSensitivity;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Sensibilidade! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select id, nameSensitivity from sensitivity order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Sensitivity oSensitivity = new Sensitivity();
                oSensitivity.setId(rs.getInt("id"));
                oSensitivity.setNameSensitivity(rs.getString("nameSensitivity"));
                resultado.add(oSensitivity);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Sensibilidade! Erro: " +ex.getMessage());
        }
        return resultado;
    }
}

