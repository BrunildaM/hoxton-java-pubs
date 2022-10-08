package com.pubs.pubs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "drink")
public class Drink {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(nullable = false)
    public String name;
    public String kind;
    public Boolean isAlcoholic;

    @JsonIgnore
    @ManyToMany(mappedBy = "drink")
    public Set<Pub> pubs = new HashSet<>();

    public Drink() {
    }
}


@RestController
class BeerController {
    @Autowired
    public DrinkRepo drinkRepo;

    @GetMapping("/drinks")
    public List<Drink> getAllDrinks() {
        return drinkRepo.findAll();
    }

    @PostMapping("/drinks")
    public Drink createDrink(@RequestBody Drink drinkData) {
        return drinkRepo.save(drinkData);
    }
}

interface DrinkRepo extends JpaRepository<Drink, Integer> {

}

