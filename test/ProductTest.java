import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic JUnit tests for Product class
 * Author: Tika Khadka
 */
public class ProductTest {
    
    @Test
    public void testNewProduct() {
        Product product = new Product("Apple", 1.50);
        assertEquals("Apple", product.getName());
        assertEquals(1.50, product.getUnitPrice(), 0.01);
    }
    
    @Test
    public void testProductWithZeroPrice() {
        Product product = new Product("Free Sample", 0.0);
        assertEquals("Free Sample", product.getName());
        assertEquals(0.0, product.getUnitPrice(), 0.01);
    }
    
    @Test
    public void testProductWithHighPrice() {
        Product product = new Product("Expensive Item", 999.99);
        assertEquals("Expensive Item", product.getName());
        assertEquals(999.99, product.getUnitPrice(), 0.01);
    }
}
