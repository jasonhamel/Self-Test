package main.model.stock;

import java.util.Objects;

public class AAPL extends Stock{
    protected String name = "AAPL";

    public AAPL(double priceOfShare) {
        super(priceOfShare);
    }

    public AAPL(AAPL source) {
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
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AAPL aapl = (AAPL) o;
        return Objects.equals(name, aapl.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
