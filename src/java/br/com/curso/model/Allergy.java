package br.com.curso.model;
/**
 *
 * @author Gabriel
 */
public class Allergy {
    private int id;
    private String nameAllergy;

    public Allergy() {
        this.id = 0;
        this.nameAllergy= "";
    }

    public Allergy(String nome) {
        this.nameAllergy = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAllergy() {
        return nameAllergy;
    }

    public void setNameAllergy(String nome) {
        this.nameAllergy = nome;
    }

}
