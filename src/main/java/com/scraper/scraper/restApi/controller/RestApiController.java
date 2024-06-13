package com.scraper.scraper.restApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.scraper.scraper.restApi.model.CoffeeBean;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/coffees")
public class RestApiController {

    private List<CoffeeBean> coffees = new ArrayList();

    public RestApiController() {
        coffees.addAll(List.of(
                new CoffeeBean("no_1"),
                new CoffeeBean("no_2"),
                new CoffeeBean("no_3"),
                new CoffeeBean("no_4")));
    }

    @GetMapping
    Iterable<CoffeeBean> getCoffees() {
        return coffees;

    }

    @GetMapping("/{id}")
    Optional<CoffeeBean> getCoffeeById(@PathVariable String id) {
        for (CoffeeBean c : coffees) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    CoffeeBean postCoffee(@RequestBody CoffeeBean coffee) {
        coffees.add(coffee);

        return coffee;
    }

    @PutMapping("/{id}")
    CoffeeBean putCoffee(@PathVariable String id, @RequestBody CoffeeBean coffee) {
        int coffeeIndex = -1;

        for (CoffeeBean c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }

}
