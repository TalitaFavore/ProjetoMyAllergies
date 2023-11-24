package br.com.curso.model;

/**
 *
 * @author Talita
 */
public class Ingredient {
    private int id;
    private String nameIngredient;
    
    public Ingredient() {
        this.id = 0;
        this.nameIngredient= "";
    }

    public Ingredient(int id, String nameIngredient) {
        this.id = id;
        this.nameIngredient = nameIngredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }
}
