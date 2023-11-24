package br.com.curso.model;

/**
 *
 * @author Talita
 */
public class Sensitivity {
    private int id;
    private String nameSensitivity;
    
    public Sensitivity() {
        this.id = 0;
        this.nameSensitivity= "";
    }

    public Sensitivity(int id, String nameSensitivity) {
        this.id = id;
        this.nameSensitivity = nameSensitivity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSensitivity() {
        return nameSensitivity;
    }

    public void setNameSensitivity(String nameSensitivity) {
        this.nameSensitivity = nameSensitivity;
    }
}
