package br.com.curso.model;

/**
 *
 * @author Talita
 */
public class Intensity {
    private int id;
    private String nameIntensity;
    
    public Intensity() {
        this.id = 0;
        this.nameIntensity = "";
    }

    public Intensity(int id, String nameIntensity) {
        this.id = id;
        this.nameIntensity = nameIntensity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameIntensity() {
        return nameIntensity;
    }

    public void setNameIntensity(String nameIntensity) {
        this.nameIntensity = nameIntensity;
    }
}

