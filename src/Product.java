/**
 * Product class for Invoice application
 * Name: Tika Khadka
 * 
 * Represents a product with name and unit price
 */
public class Product {
    private String name;
    private double unitPrice;
    
    /**
     * Constructor to create a product
     * Input: name - product name, unitPrice - price per unit
     * Output: none
     */
    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }
    
    /**
     * Gets the product name
     * Input: none
     * Output: String representing the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the unit price
     * Input: none
     * Output: double representing the unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }
}
