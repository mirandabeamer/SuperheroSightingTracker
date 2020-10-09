/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author mirandabeamer
 */
public class Superpower {
    private int superpowerId;
    @NotEmpty(message = "You must supply a value for superpower name")
    @Length(max=50, message="Superpower name must be no more than 50 characters in length")
    private String superpowerName;
    @Length(max=100, message="Description must be no more than 100 characters in length")
    private String superpowerDesc;
    private List<Superhero> superheroes;

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public String getSuperpowerName() {
        return superpowerName;
    }

    public void setSuperpowerName(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    public String getSuperpowerDesc() {
        return superpowerDesc;
    }

    public void setSuperpowerDesc(String superpowerDesc) {
        this.superpowerDesc = superpowerDesc;
    }

    public List<Superhero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(List<Superhero> superheroes) {
        this.superheroes = superheroes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.superpowerId;
        hash = 29 * hash + Objects.hashCode(this.superpowerName);
        hash = 29 * hash + Objects.hashCode(this.superpowerDesc);
        hash = 29 * hash + Objects.hashCode(this.superheroes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Superpower other = (Superpower) obj;
        if (this.superpowerId != other.superpowerId) {
            return false;
        }
        if (!Objects.equals(this.superpowerName, other.superpowerName)) {
            return false;
        }
        if (!Objects.equals(this.superpowerDesc, other.superpowerDesc)) {
            return false;
        }
        if (!Objects.equals(this.superheroes, other.superheroes)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
