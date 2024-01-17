package org.learning.springlamiapizzeriacrud.controller;
import jakarta.validation.Valid;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/specialoffers")
public class SpecialOfferController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, Model model) {
        // verifico se la Pizza esiste su database
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            // estraggo la Pizza dall'Optional
            Pizza pizzaOffer = result.get();
            // passo al Model la Pizza come attributo
            // preparo la SpecialOffer per precaricare il form
            SpecialOffer newSpecialOffer = new SpecialOffer();
            // precarico i campi
            newSpecialOffer.setPizza(pizzaOffer);
            newSpecialOffer.setStartTime(LocalDate.now());
            newSpecialOffer.setEndTime(LocalDate.now().plusDays(30));
            model.addAttribute("pizza", pizzaOffer);
            model.addAttribute("specialOffer", newSpecialOffer);
            // restituisco il template
            return "specialOffers/create";
        } else {
            // se l'Optional è vuoto sollevo una eccezione HTTP 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pizza with id " + pizzaId + " not found");
        }
    }
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("SpecialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, Model model) {
        // valido l'oggetto

        // se ci sono errori ritorno il template del form
        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", formSpecialOffer.getPizza() );
            return "specialOffers/create";
        }
        if (formSpecialOffer.getEndTime() != null && formSpecialOffer.getEndTime().isBefore(formSpecialOffer.getStartTime())){
            formSpecialOffer.setEndTime(formSpecialOffer.getStartTime().plusDays(30));
        }
        model.addAttribute("specialoffer", formSpecialOffer);
        // se non ci sono errori lo salvo su database
        SpecialOffer storedSpecialOffer = specialOfferRepository.save(formSpecialOffer);
        // faccio una redirect alla pagina di dettaglio del libro
        return "redirect:/pizza/show/" + storedSpecialOffer.getPizza().getId();
    }

    // metodo per restituire la pagina col form di modifica
    @GetMapping("/edit/{id}") // http://localhost:8080/specialoffers/edit/1
    public String edit(@PathVariable Integer id, Model model) {
        // recupero lo Special Offer con quell'id da database
        Optional<SpecialOffer> result = specialOfferRepository.findById(id);
        // se è presente precarico il form con lo specialOffer
        if (result.isPresent()) {
            // lo passo come attributo al Model
            model.addAttribute("specialoffer", result.get());
            // restituisco il template
            return "specialOffers/edit";
        } else {
            // altrimenti sollevo un'eccezione HTTP 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Special Offer with id " + id + " not found");
        }
    }

    // metodo per ricevere il submit del form di edit
    @PostMapping("/edit/{id}") // http://localhost:8080/specialoffers/edit/1
    public String update(@PathVariable Integer id,
                         @Valid @ModelAttribute("specialoffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        // valido la Special Offer
        if (bindingResult.hasErrors()) {
            // se ci sono errori ricarico la pagina col form di edit
            return "specialOffers/edit";
        }
        // se non ci sono errori lo salvo su db
        SpecialOffer updatedSpecialOffer = specialOfferRepository.save(formSpecialOffer);
        // redirect al dettaglio del libro prestato
        return "redirect:/pizza/show/" + updatedSpecialOffer.getPizza().getId();
    }

    // metodo per cancellare lo special offer preso per id
    @PostMapping("/delete/{id}") // /specialoffers/delete/1
    public String delete(@PathVariable Integer id) {
        // verifico se il Borrowing con l'id passato esiste
        Optional<SpecialOffer> result = specialOfferRepository.findById(id);
        // se esiste lo elimino da db
        if (result.isPresent()) {
            SpecialOffer SpecialOfferToDelete = result.get();
            specialOfferRepository.delete(SpecialOfferToDelete);
            return "redirect:/pizza/show/" + SpecialOfferToDelete.getPizza().getId();
        } else {
            // se non esiste sollevo un'eccezione HTTP 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Special offer with id " + id + " not found");
        }

    }





}

