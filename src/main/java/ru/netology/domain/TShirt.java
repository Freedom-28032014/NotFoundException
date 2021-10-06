package ru.netology.domain;

import java.util.Objects;

public class TShirt extends Product {
    private String creator;



    public TShirt(int id, String name, int price, String creator) {
        super(id, name, price);
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TShirt tShirt = (TShirt) o;
        return Objects.equals(creator, tShirt.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creator);
    }
}

