package org.learning.springlamiapizzeriacrud.model;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class Pizza {

    // ATTRIBUTI

    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    // COSTRUTTORE

    public Pizza(String name, String description, String image, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
    public Pizza() {
        // Costruttore senza parametri
        this.name = null;
        this.description = null;
        this.image = null;
        this.price = null;
    }

    // GETTER AND SETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // METODI
}
