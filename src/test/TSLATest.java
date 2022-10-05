package test;

import main.model.stock.TSLA;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TSLATest {
    TSLA tsla;

    @Before
    public void setup() {
        tsla = new TSLA(100);
    }

    @Test
    public void testSetValue() {
        Assert.assertEquals(100, tsla.getPriceOfShare(), 0);
    }

    @Test
    public void testChangeValue() {
        tsla.setPrice(50);
        Assert.assertEquals(50, tsla.getPriceOfShare(), 0);
    }
}
