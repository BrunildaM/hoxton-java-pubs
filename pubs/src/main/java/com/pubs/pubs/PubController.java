package com.pubs.pubs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public class PubController {
    @RestController
class PubController {
    @Autowired
    public PubRepo pubRepo;

    @Autowired
    public DrinkRepo drinkRepo;

    @GetMapping("/pubs")
    public List<Pub> getAllPubs() {
        return pubRepo.findAll();
    }

    @PostMapping("/pubs")
    public Pub createPub(@RequestBody Pub pubData) {
        return pubRepo.save(pubData);
    }

    @PostMapping("/pubBeers")
    public Message createPubBeers(@RequestBody PubAndBeerIds body) {
        Pub pub = pubRepo.findById(body.pubId).get();
        Drink drink = drinkRepo.findById(body.drinkId).get();

        pub.drinks.add(drink);
        pubRepo.save(pub);
        return new Message("Drink added successfully.");
    }
}

class PubAndBeerIds {
    public Integer pubId;
    public Integer beerId;

}

class Message {
    public String message;

    public Message(String message) {
        this.message = message;
    }
}

interface PubRepo extends JpaRepository<Pub, Integer> {

    List<Pub> findAll();
}
    
}
