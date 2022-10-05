package test;

import main.model.stock.GOOG;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GOOGTest {
    GOOG goog;

    @Before
    public void setup() {
        goog = new GOOG(500);
    }

    @Test
    public void testSetValue() {
        Assert.assertEquals(500, goog.getPriceOfShare(), 0);
    }

    @Test
    public void testChangeValue() {
        goog.setPrice(550);
        Assert.assertEquals(550, goog.getPriceOfShare(), 0);
    }
}
