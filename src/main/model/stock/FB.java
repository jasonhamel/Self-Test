package main.model.stock;

import java.util.Objects;

public class FB extends Stock{
    protected String name = "FB";

    public FB(double priceOfShare) {
        super(priceOfShare);
    }

    public FB(FB source) {
        super(source);
        this.name = source.name;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FB fb = (FB) o;
        return Objects.equals(name, fb.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
