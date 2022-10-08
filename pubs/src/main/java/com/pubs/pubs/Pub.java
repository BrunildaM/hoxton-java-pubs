package com.pubs.pubs;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "pub")
public class Pub {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(nullable = false)
    public String name;
    public String city;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "Pub_DRINK", joinColumns = { @JoinColumn(name = "pub_id") }, inverseJoinColumns = {
            @JoinColumn(name = "drink_id") })
    public Set<Drink> drinks = new HashSet<>();

    public Pub() {
    }

}
