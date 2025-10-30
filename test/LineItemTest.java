import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic JUnit tests for LineItem class
 * Author: Tika Khadka
 */
public class LineItemTest {
    
    @Test
    public void testNewLineItem() {
        Product product = new Product("Apple", 1.50);
        LineItem item = new LineItem(3, product);
        assertEquals(3, item.getQuantity());
        assertEquals(product, item.getProduct());
    }
    
    @Test
    public void testGetTotal() {
        Product product = new Product("Apple", 2.00);
        LineItem item = new LineItem(5, product);
        assertEquals(10.00, item.getTotal(), 0.01);
    }
    
    @Test
    public void testGetTotalWithZeroQuantity() {
        Product product = new Product("Apple", 1.50);
        LineItem item = new LineItem(0, product);
        assertEquals(0.00, item.getTotal(), 0.01);
    }
    
    @Test
    public void testGetTotalWithDecimalPrice() {
        Product product = new Product("Banana", 0.75);
        LineItem item = new LineItem(4, product);
        assertEquals(3.00, item.getTotal(), 0.01);
    }
}
