package br.com.curso.model;

/**
 *
 * @author Talita
 */
public class Intolerance {
    private int id;
    private String nameIntolerance;
    
    public Intolerance() {
        this.id = 0;
        this.nameIntolerance= "";
    }

    public Intolerance(int id, String nameIntolerance) {
        this.id = id;
        this.nameIntolerance = nameIntolerance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameIntolerance() {
        return nameIntolerance;
    }

    public void setNameIntolerance(String nameIntolerance) {
        this.nameIntolerance = nameIntolerance;
    }
}
