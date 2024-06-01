package org.myorg.quickstart.entities;

public class Enriched {
    public String city;
    public String sight;

    public Enriched(String city, String sight) {
        this.city = city;
        this.sight = sight;
    }

    @Override
    public String toString() {
        return this.city + ", " + this.sight;
    }
}
