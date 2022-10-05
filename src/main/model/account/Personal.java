package main.model.account;
import main.model.account.impl.Taxable;
import main.model.stock.Stock;

import java.util.Objects;

public class Personal extends Account implements Taxable{
    private final double TAX_RATE_SELL = 0.95;

    public Personal(double cash) {
        super(Account.AccountType.PERSONAL, cash);
    }


    @Override
    public double tax(double income) {
        return income * TAX_RATE_SELL;
    }

    @Override
    public void deposit(double money) {
        setCash(money);
        
    }

    @Override
    public boolean action(String buyOrSell, Stock stock, int quantity) {
        if (buyOrSell.equalsIgnoreCase("buy")) {
            if (stock.getPriceOfShare() * quantity > getCash()) {
                return false;
            }
            setPortfolio(stock, quantity);
            remove(quantity * stock.getPriceOfShare());
            return true;
        } else if (buyOrSell.equalsIgnoreCase("sell")) {
            if (getNumberOfStocks(stock) < quantity) {
                return false;
            }
            deposit(tax(quantity * stock.getPriceOfShare()));
            setPortfolio(stock, -quantity);
            return true;
        }
            return false;
    }
    @Override
    public String toString() {
        if (getPortfolio().isEmpty()) {
            return "\tThe Personal account has $" + getCash() + " in cash, and no stocks." + "\n";
        } else {
            String string = "";
            for (Stock key : getPortfolio().keySet()) {
                string += "\tNumber of Shares of " + key + ": " + getPortfolio().get(key) + "\n";
            }
            return "\tThe Personal account has $" + getCash() + " in cash, and the following stocks." + "\n" +
                    string + "\n";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Personal personal = (Personal) o;
        return Double.compare(personal.TAX_RATE_SELL, TAX_RATE_SELL) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), TAX_RATE_SELL);
    }
}
