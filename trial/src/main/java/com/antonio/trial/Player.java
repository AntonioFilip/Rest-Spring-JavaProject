package com.antonio.trial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class Player {
    private @Id @GeneratedValue Long id;
    private String name;
    private String height;
    private String position;
    private String firstName;
    private String lastName;

    Player() {
    }

    Player(String firstName, String lastName, String height, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        String[] tokens = name.split(" ");
        this.firstName = tokens[0];
        this.lastName = tokens[1];
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,this.firstName,this.lastName,this.height,this.position);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Player))
            return false;
        Player player = (Player) o;
        return Objects.equals(this.id, player.id) && Objects.equals(this.firstName, player.firstName)
                && Objects.equals(this.height, player.height) && Objects.equals(this.lastName, player.lastName);
    }

    @Override
    public String toString() {
        return "Player{" + this.id + " ; " + this.firstName + " ; " + this.lastName + " ; " + this.height + " ; " + this.position + "}";
    }
}
