package org.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "SpecialOffers")
public class SpecialOffer {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String offerTitle;
    private LocalDate startTime;
    private LocalDate endTime;

    @ManyToOne
    private Pizza pizza;

    // COSTRUTTORI

    // GETTER AND SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }


    // METODI
}
