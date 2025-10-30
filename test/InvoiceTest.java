import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Basic JUnit tests for Invoice class
 * Author: Tika Khadka
 */
public class InvoiceTest {
    
    @Test
    public void testNewInvoice() {
        Invoice invoice = new Invoice();
        assertEquals(0.00, invoice.getTotal(), 0.01);
        assertTrue(invoice.getLineItems().isEmpty());
    }
    
    @Test
    public void testAddLineItem() {
        Invoice invoice = new Invoice();
        Product product = new Product("Apple", 1.00);
        LineItem item = new LineItem(2, product);
        invoice.addLineItem(item);
        
        List<LineItem> items = invoice.getLineItems();
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }
    
    @Test
    public void testGetTotalWithOneItem() {
        Invoice invoice = new Invoice();
        Product product = new Product("Apple", 2.50);
        LineItem item = new LineItem(3, product);
        invoice.addLineItem(item);
        
        assertEquals(7.50, invoice.getTotal(), 0.01);
    }
    
    @Test
    public void testGetTotalWithMultipleItems() {
        Invoice invoice = new Invoice();
        
        Product apple = new Product("Apple", 1.00);
        Product banana = new Product("Banana", 0.50);
        
        LineItem item1 = new LineItem(2, apple);   // 2.00
        LineItem item2 = new LineItem(4, banana);  // 2.00
        
        invoice.addLineItem(item1);
        invoice.addLineItem(item2);
        
        assertEquals(4.00, invoice.getTotal(), 0.01);
    }
    
    @Test
    public void testGetTotalWithZeroItems() {
        Invoice invoice = new Invoice();
        assertEquals(0.00, invoice.getTotal(), 0.01);
    }
    
    @Test
    public void testMultipleLineItems() {
        Invoice invoice = new Invoice();
        
        Product product1 = new Product("Item1", 10.00);
        Product product2 = new Product("Item2", 5.00);
        Product product3 = new Product("Item3", 2.50);
        
        invoice.addLineItem(new LineItem(1, product1));
        invoice.addLineItem(new LineItem(2, product2));
        invoice.addLineItem(new LineItem(4, product3));
        
        assertEquals(3, invoice.getLineItems().size());
        assertEquals(30.00, invoice.getTotal(), 0.01); // 10 + 10 + 10 = 30
    }
}
