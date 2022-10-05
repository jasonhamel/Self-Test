package test;

import main.model.stock.FB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FBTest {
    FB fb;

    @Before
    public void setup() {
        fb = new FB(1);
    }

    @Test
    public void testSetValue() {
        Assert.assertEquals(1, fb.getPriceOfShare(), 0);
    }

    @Test
    public void testChangeValue() {
        fb.setPrice(0);
        Assert.assertEquals(0, fb.getPriceOfShare(), 0);
    }
}
