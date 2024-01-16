package org.learning.springlamiapizzeriacrud.controller;
import org.learning.springlamiapizzeriacrud.model.Pizza;
import org.learning.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.springlamiapizzeriacrud.repository.PizzaRepository;
import org.learning.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String store(SpecialOffer formSpecialOffer) {
        // valido l'oggetto

        // se ci sono errori ritorno il template del form

        // se non ci sono errori lo salvo su database
        SpecialOffer storedSpecialOffer = specialOfferRepository.save(formSpecialOffer);
        // faccio una redirect alla pagina di dettaglio del libro
        return "redirect:/pizza/show/" + storedSpecialOffer.getPizza().getId();
    }
}

