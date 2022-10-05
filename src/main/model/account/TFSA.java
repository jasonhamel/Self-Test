package main.model.account;

import main.model.account.impl.Taxable;
import main.model.stock.Stock;

import java.util.Objects;

public class TFSA extends Account implements Taxable{
    private final double TAX_RATE_BUY_SELL = 0.01;
    public TFSA(double cash) {
        super(Account.AccountType.TFSA, cash);
    }

    @Override
    public double tax(double income) {
        return income * TAX_RATE_BUY_SELL;
    }
    
    @Override
    public boolean action(String buyOrSell, Stock stock, int quantity) {
        if (buyOrSell.equalsIgnoreCase("buy")) {
            if (stock.getPriceOfShare() * quantity > getCash() - tax(quantity * stock.getPriceOfShare())) {
                return false;
            }
            remove(tax(stock.getPriceOfShare() * quantity));
            setPortfolio(stock, quantity);
            remove(stock.getPriceOfShare() * quantity);
        } else if (buyOrSell.equalsIgnoreCase("sell")) {
            if (getNumberOfStocks(stock) < quantity) {
                return false;
            }
            remove(tax(stock.getPriceOfShare() * quantity));
            deposit(stock.getPriceOfShare() * quantity);
            setPortfolio(stock, -quantity);
        }
        return false;
    }

    @Override
    public void deposit(double money) {
        setCash(money);
    }

    @Override
    public String toString() {
        if (getPortfolio().isEmpty()) {
            return "\tThe TFSA account has $" + getCash() + " in cash, and no stocks." + "\n";
        } else {
            String string = "";
            for (Stock key : getPortfolio().keySet()) {
                string += "\tNumber of Shares of " + key + ": " + getPortfolio().get(key) + "\n";
            }
            return "\tThe TFSA account has $" + getCash() + " in cash, and the following stocks." + "\n" +
                    string + "\n";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TFSA tfsa = (TFSA) o;
        return Double.compare(tfsa.TAX_RATE_BUY_SELL, TAX_RATE_BUY_SELL) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), TAX_RATE_BUY_SELL);
    }
}
