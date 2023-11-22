package br.com.curso.dao;

import br.com.curso.model.Profile;
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
public class ProfileDAO implements GenericDAO {
    
    private Connection conexao;
    
    public ProfileDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Profile oProfile= (Profile) objeto;
        Boolean retorno=false;
        if (oProfile.getId() == 0) {
            retorno = this.inserir(oProfile);
        } else{
            retorno = this.alterar(oProfile);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Profile oProfile = (Profile) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into profile (username, email, passcode) values (?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProfile.getUsername());
            stmt.setString(2, oProfile.getEmail());
            stmt.setString(3, oProfile.getPasscode());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar o Usuário! Erro: " + ex.getMessage());
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
        Profile oProfile = (Profile) objeto;
        PreparedStatement stmt = null;
        String sql = "update profile set username=?, email=?, passcode=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProfile.getUsername());
            stmt.setString(2, oProfile.getEmail());
            stmt.setString(3, oProfile.getPasscode());
            stmt.setInt(4, oProfile.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar a Usuário! Erro: " + ex.getMessage());
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
        
        String sql = "delete from profile where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir a Usuario! Erro: " + ex.getMessage());
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
        Profile oProfile = null;
        String sql = "select * from profile where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oProfile = new Profile();
                oProfile.setId(rs.getInt("id"));
                oProfile.setUsername(rs.getString("username"));
                oProfile.setEmail(rs.getString("email"));   
                oProfile.setPasscode(rs.getString("passcode"));  
            }
            return oProfile;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Usuario! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select id, username, email, passcode from profile order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Profile oProfile = new Profile();
                oProfile.setId(rs.getInt("id"));
                oProfile.setUsername(rs.getString("username"));
                oProfile.setEmail(rs.getString("email"));
                oProfile.setPasscode(rs.getString("passcode"));
                resultado.add(oProfile);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Usuário! Erro: " +ex.getMessage());
        }
        return resultado;
    }
    
}



