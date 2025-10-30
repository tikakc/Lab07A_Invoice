import java.util.ArrayList;
import java.util.List;

/**
 * Invoice class for Invoice application
 * Name: Tika Khadka
 * 
 * Represents an invoice containing multiple line items
 */
public class Invoice {
    private List<LineItem> lineItems;
    
    /**
     * Constructor to create an invoice
     * Input: none
     * Output: none
     */
    public Invoice() {
        lineItems = new ArrayList<>();
    }
    
    /**
     * Adds a line item to the invoice
     * Input: lineItem - the LineItem to add
     * Output: none
     */
    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }
    
    /**
     * Calculates the total amount due for the invoice
     * Input: none
     * Output: double representing the sum of all line item totals
     */
    public double getTotal() {
        double total = 0.0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }
    
    /**
     * Gets the list of line items
     * Input: none
     * Output: List of LineItem objects
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
