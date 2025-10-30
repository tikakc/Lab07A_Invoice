import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * InvoiceGUI class for Invoice application
 * Name: Tika Khadka
 * 
 * Creates GUI interface for entering and displaying invoice data
 */
public class InvoiceGUI extends JFrame implements ActionListener {
    private JTextField productNameField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextArea displayArea;
    private JButton addItemButton;
    private JButton displayInvoiceButton;
    private JButton clearButton;
    private JButton quitButton;
    
    private Invoice invoice;
    private DecimalFormat currencyFormat;
    
    /**
     * Constructor to create the GUI
     * Input: none
     * Output: none
     */
    public InvoiceGUI() {
        invoice = new Invoice();
        currencyFormat = new DecimalFormat("$#,##0.00");
        createGUI();
    }
    
    /**
     * Creates the GUI components and layout
     * Input: none
     * Output: none
     */
    private void createGUI() {
        setTitle("Invoice Application");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                quitApplication();
            }
        });
        setLayout(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Display area
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Invoice Display"));
        add(scrollPane, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Creates the input panel with fields and buttons
     * Input: none
     * Output: JPanel containing input components
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Enter Line Item"));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Product name
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Product Name:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        productNameField = new JTextField(20);
        panel.add(productNameField, gbc);
        
        // Unit price
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Unit Price:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        unitPriceField = new JTextField(20);
        panel.add(unitPriceField, gbc);
        
        // Quantity
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        quantityField = new JTextField(20);
        panel.add(quantityField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addItemButton = new JButton("Add Item");
        displayInvoiceButton = new JButton("Display Invoice");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        
        addItemButton.addActionListener(this);
        displayInvoiceButton.addActionListener(this);
        clearButton.addActionListener(this);
        quitButton.addActionListener(this);
        
        buttonPanel.add(addItemButton);
        buttonPanel.add(displayInvoiceButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    /**
     * Handles button click events
     * Input: ActionEvent from button clicks
     * Output: none
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItemButton) {
            addLineItem();
        } else if (e.getSource() == displayInvoiceButton) {
            displayInvoice();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == quitButton) {
            quitApplication();
        }
    }
    
    /**
     * Adds a line item to the invoice from input fields
     * Input: none
     * Output: none
     */
    private void addLineItem() {
        try {
            String productName = productNameField.getText().trim();
            double unitPrice = Double.parseDouble(unitPriceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            
            if (productName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a product name.");
                return;
            }
            
            Product product = new Product(productName, unitPrice);
            LineItem lineItem = new LineItem(quantity, product);
            invoice.addLineItem(lineItem);
            
            displayArea.append("Added: " + quantity + " x " + productName + 
                             " @ " + currencyFormat.format(unitPrice) + " each\n");
            
            clearFields();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity.");
        }
    }
    
    /**
     * Displays the complete invoice in the text area
     * Input: none
     * Output: none
     */
    private void displayInvoice() {
        displayArea.setText("");
        
        // Get current date
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMM dd, yyyy");
        String currentDate = dateFormat.format(new java.util.Date());
        
        displayArea.append("Invoice: " + currentDate + "\n\n");
        
        if (invoice.getLineItems().isEmpty()) {
            displayArea.append("No items in invoice.\n");
            return;
        }
        
        // Header row
        displayArea.append(String.format("%-15s %3s %8s %8s\n", "Item", "Qty", "Price", "Total"));
        displayArea.append("-------------------------------------\n");
        
        // Line items
        for (LineItem item : invoice.getLineItems()) {
            displayArea.append(String.format("%-15s %3d %8s %8s\n",
                                           item.getProduct().getName(),
                                           item.getQuantity(),
                                           currencyFormat.format(item.getProduct().getUnitPrice()),
                                           currencyFormat.format(item.getTotal())));
        }
        
        displayArea.append("\n");
        displayArea.append(String.format("AMOUNT DUE: %s\n", currencyFormat.format(invoice.getTotal())));
    }
    
    /**
     * Clears all input fields
     * Input: none
     * Output: none
     */
    private void clearFields() {
        productNameField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
        productNameField.requestFocus();
    }
    
    /**
     * Handles quit application with confirmation prompt
     * Input: none
     * Output: none
     */
    private void quitApplication() {
        int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to quit the Invoice Application?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Main method to start the application
     * Input: args - command line arguments
     * Output: none
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InvoiceGUI();
            }
        });
    }
}
