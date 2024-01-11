package org.learning.springlamiapizzeriacrud.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
@Entity
@Table(name = "pizza")
public class Pizza {
    // ATTRIBUTI
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Integer id;
    @NotEmpty(message = "The field name cannot be empty")
    @Column(nullable = false)
    private String name;
    @DecimalMin(value = "0.01", message = "The price must be greater than 0")
    @NotNull(message = "The field price cannot be empty")
    @Column(nullable = false)
    private BigDecimal price;
    @Lob
    private String description;
    private String image;

    // COSTRUTTORE

    public Pizza(Integer id, String name, BigDecimal price, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Pizza() {
        this.id = null;
        this.name = null;
        this.price = null;
        this.description = null;
        this.image = null;
    }

    // GETTER AND SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    // METODI
}
