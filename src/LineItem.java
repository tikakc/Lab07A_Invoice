/**
 * LineItem class for Invoice application
 * Name: Tika Khadka
 * 
 * Represents a line item with quantity and product reference
 */
public class LineItem {
    private int quantity;
    private Product product;
    
    /**
     * Constructor to create a line item
     * Input: quantity - number of items, product - the product being purchased
     * Output: none
     */
    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
    
    /**
     * Calculates the total for this line item
     * Input: none
     * Output: double representing quantity times unit price
     */
    public double getTotal() {
        return quantity * product.getUnitPrice();
    }
    
    /**
     * Gets the quantity
     * Input: none
     * Output: int representing the quantity
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Gets the product
     * Input: none
     * Output: Product object
     */
    public Product getProduct() {
        return product;
    }
}
