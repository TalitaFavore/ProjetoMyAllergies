/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.model.Allergy;
import br.com.curso.model.Ingredient;
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
public class IngredientDAO implements GenericDAO {
    
    private Connection conexao;
    
    public IngredientDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Ingredient oIngredient= (Ingredient) objeto;
        Boolean retorno=false;
        if (oIngredient.getId() == 0) {
            retorno = this.inserir(oIngredient);
        } else{
            retorno = this.alterar(oIngredient);
        }
        return retorno;
    }
    
    @Override
    public Boolean inserir(Object objeto) {
    Ingredient oIngredient = (Ingredient) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into ingredient (nameIngredient) values (?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oIngredient.getNameIngredient());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try{
                System.out.println("Problemas ao cadastrar o Ingrediente! Erro: " + ex.getMessage());
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
        Ingredient oIngredient = (Ingredient) objeto;
        PreparedStatement stmt = null;
        String sql = "update ingredient set nameIngredient=? where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oIngredient.getNameIngredient());
            stmt.setInt(2, oIngredient.getId());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
             try{
                System.out.println("Problemas ao alterar o Ingrediente! Erro: " + ex.getMessage());
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
        
        String sql = "delete from ingredient where id=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Ingrediente! Erro: " + ex.getMessage());
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
        Ingredient oIngredient = null;
        String sql = "select id, nameIngredient from ingredient where id=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            while(rs.next()) {
                oIngredient = new Ingredient();
                oIngredient.setId(rs.getInt("id"));
                oIngredient.setNameIngredient(rs.getString("nameIngredient"));
            }
            return oIngredient;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Ingrediente! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select id, nameIngredient from ingredient order by id";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()) {
                Ingredient oIngredient = new Ingredient();
                oIngredient.setId(rs.getInt("id"));
                oIngredient.setNameIngredient(rs.getString("nameIngredient"));
                resultado.add(oIngredient);
            }
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Ingrediente! Erro: " +ex.getMessage());
        }
        return resultado;
    }
}
