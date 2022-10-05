package main.model.account;

import main.model.stock.Stock;
import java.util.HashMap;
import java.util.Objects;

import static main.model.account.Account.AccountType.PERSONAL;

public abstract class Account {
    public enum AccountType {
        PERSONAL, TFSA
    }
    private AccountType accountType;
    private double cash;
    private HashMap<Stock, Integer> portfolio;
    

    public Account(AccountType AccountType, double cash) {
        if (AccountType == null || cash < 0) {
            throw new IllegalArgumentException("Account type cannot be blank, and starting balance must be great than $0");
        }
        this.accountType = AccountType;
        this.cash = cash;
        this.portfolio = new HashMap<>();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        if (accountType == null) {
            throw new IllegalArgumentException("Account type cannot be blank.");
        }
        this.accountType = accountType;
    }

    public double getCash() {
        return this.cash;
    }

    public boolean setCash(double cash) {
        if (getCash() + cash < 0) {
            throw new IllegalArgumentException("Cash in account cannot be less than $0");
        }
        this.cash = getCash() + cash;
        return true;
    }

    public HashMap<Stock, Integer> getPortfolio() {
        return this.portfolio;
    }

    public int getNumberOfStocks(Stock stock) {
        if (getPortfolio().get(stock) == null) {
            return 0;
        }
        return getPortfolio().get(stock);
    }

    public void setPortfolio(Stock stock, Integer numberOfShares) {
        if (portfolio.get(stock) == null) {
            portfolio.put(stock, numberOfShares);
        }else {
            portfolio.put(stock, portfolio.get(stock) + numberOfShares);
            //portfolio.put(new Stock(stock), portfolio.get(stock) + numberOfShares);
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Account Type: " + accountType + "\n" +
                "Cash: " + this.cash + "\n" +
                "Portfolio: " + this.portfolio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.cash, cash) == 0 && accountType == account.accountType && Objects.equals(portfolio, account.portfolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.accountType, this.cash, this.portfolio);
    }

    public void remove(double moneyToRemove) {
        this.cash = this.cash - moneyToRemove;
    }

    public abstract void deposit(double money);
    
    public abstract boolean action(String buyOrSell, Stock stock, int quantity);
    }