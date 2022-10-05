package main.model.stock;

import java.util.Objects;

public class TSLA extends Stock {

    protected String name = "TSLA";

    public TSLA(double priceOfShare) {
        super(priceOfShare);
    }

    public TSLA(TSLA source) {
        super(source);
        this.name = source.name;
    }

    public String getName() {
        return name;
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
        TSLA tsla = (TSLA) o;
        return Objects.equals(name, tsla.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
