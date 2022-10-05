package test;

import main.model.stock.AAPL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class APPLTest {
    AAPL aapl;

    @Before
    public void setup() {
        aapl = new AAPL(400.0);
    }

    @Test
    public void testSetValue() {
        Assert.assertTrue(400 == aapl.getPriceOfShare());
    }

    @Test
    public void testChangeValue() {
        aapl.setPrice(500);
        Assert.assertTrue(500 == aapl.getPriceOfShare());
    }

}
