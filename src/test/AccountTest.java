package test;

import main.model.account.Personal;
import main.model.account.TFSA;
import main.model.account.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import main.model.stock.AAPL;

public class AccountTest {
    Account[] accounts;
    AAPL aapl;
    @Before
    public void setup() {
        accounts = new Account[]{
                new Personal(5000),
                new TFSA(4000)
        };
    }

    /*
        PERSONAL TESTS
     */

    @Test
    public void testDepositPersonal() {
        accounts[0].deposit(5000);
        Assert.assertEquals(10000, accounts[0].getCash(), 0.0);
    }

    @Test
    public void testRemoveMoniesPersonal() {
        accounts[0].remove(4000);
        Assert.assertEquals(1000, accounts[0].getCash(), 0.0);
    }

    @Test
    public void testBuyPersonal() {
        aapl = new AAPL(100);
        accounts[0].action("buy", aapl, 4);
        Assert.assertEquals(4, accounts[0].getNumberOfStocks(aapl));
        Assert.assertEquals(4600, accounts[0].getCash(), 0);
    }

    @Test
    public void testSellPersonal() {
        aapl = new AAPL(100);
        accounts[0].action("buy", aapl, 2);
        accounts[0].action("sell", aapl, 1);
        Assert.assertEquals(1, accounts[0].getNumberOfStocks(aapl));
    }

    @Test
    public void testOnlySellMaxSharesPersonal() {
        aapl = new AAPL(100);
        accounts[0].action("buy", aapl, 5);
        accounts[0].action("sell", aapl, 7);
        Assert.assertEquals(5, accounts[0].getNumberOfStocks(aapl));
    }

    @Test
    public void testOnlyBuyIfAffordablePersonal() {
        aapl = new AAPL(1000);
        Assert.assertEquals(0, accounts[0].getNumberOfStocks(aapl));
        accounts[0].action("buy", aapl, 3);
        Assert.assertEquals(3, accounts[0].getNumberOfStocks(aapl));
        accounts[0].action("buy", aapl, 1);
        Assert.assertEquals(4, accounts[0].getNumberOfStocks(aapl));
        accounts[0].action("buy", aapl, 2);
        Assert.assertEquals(4, accounts[0].getNumberOfStocks(aapl));
    }

    @Test
    public void testInterestPersonal() {
        aapl =new AAPL(100);
        accounts[0].action("buy", aapl, 5);
        accounts[0].action("sell", aapl, 4);
        Assert.assertEquals(4880,  accounts[0].getCash(), 0);
    }

    /*
        TFSA TESTS
     */

    @Test
    public void testDepositTFSA() {
        accounts[1].deposit(6000);
        Assert.assertEquals(10000, accounts[1].getCash(), 0);
    }

    @Test
    public void testRemoveMoniesTFSA() {
        accounts[1].remove(3000);
        Assert.assertEquals(1000, accounts[1].getCash(), 0);
    }

    @Test
    public void testBuyTFSA() {
        aapl = new AAPL(100);
        accounts[1].action("buy", aapl, 20);
        Assert.assertEquals(20, accounts[1].getNumberOfStocks(aapl));
        Assert.assertEquals(1980, accounts[1].getCash(), 0);
    }

    @Test
    public void testSellTFSA() {
        aapl = new AAPL(100);
        accounts[1].action("buy", aapl, 20);
        accounts[1].action("sell", aapl, 10);
        Assert.assertEquals(10, accounts[1].getNumberOfStocks(aapl));
        Assert.assertEquals(2970, accounts[1].getCash(), 0);
    }

    @Test
    public void testOnlySellMaxShares() {
        aapl = new AAPL(100);
        accounts[1].action("buy", aapl, 20);
        accounts[1].action("sell", aapl, 40);
        Assert.assertEquals(20, accounts[1].getNumberOfStocks(aapl));
    }

    @Test
    public void testOnlyBuyIfAffordableTFSA() {
        aapl = new AAPL(1000);
        Assert.assertEquals(0, accounts[1].getNumberOfStocks(aapl));
        accounts[1].action("buy", aapl, 3);
        Assert.assertEquals(3, accounts[1].getNumberOfStocks(aapl));
        accounts[1].action("buy", aapl, 1);
        Assert.assertEquals(3, accounts[1].getNumberOfStocks(aapl));

    }

    @Test
    public void testInterestTFSA() {
        aapl = new AAPL(100);
        accounts[1].action("buy", aapl, 10);
        Assert.assertEquals(2990, accounts[1].getCash(), 0);
        accounts[1].action("sell", aapl, 9);
        Assert.assertEquals(3881, accounts[1].getCash(), 0);
    }

    @Test
    public void testAccountRetentionAfterPriceChange() {
        aapl = new AAPL(100);
        accounts[0].action("buy", aapl, 3);
        Assert.assertEquals(3, accounts[0].getNumberOfStocks(aapl));
        //int numberofShares = accounts[0].getNumberOfStocks(aapl);
        aapl.setPrice(50);
        //accounts[0].setPortfolio(aapl, numberofShares);
        Assert.assertEquals(3, accounts[0].getNumberOfStocks(aapl));
    }
}