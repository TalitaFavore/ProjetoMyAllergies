package br.com.curso.model;

/**
 *
 * @author Talita
 */
public class Product {
    private int id;
    private String barcode;
    private String brand;
    private String nameProduct;
    
    public Product() {
        this.id = 0;
        this.barcode = "";
        this.brand = "";
        this.nameProduct = "";
    }

    public Product(int id, String barcode, String brand, String nameProduct) {
        this.id = id;
        this.barcode = barcode;
        this.brand = brand;
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }   
}
