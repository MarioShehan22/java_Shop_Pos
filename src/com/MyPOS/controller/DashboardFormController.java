package com.MyPOS.controller;

import com.MyPOS.view.tm.BillTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.util.Optional;

public class DashboardFormController {
    public TableView<BillTm> jTable1;
    public AnchorPane dashboardContext1;
    public AnchorPane dashboardContext2;
    public TableColumn<BillTm, Integer> colId;
    public TableColumn<BillTm,String> colItem;
    public TableColumn<BillTm,Integer> colQty;
    public TableColumn<BillTm,Double> colPrice;
    public TextArea billTextArea;
    public Label total;
    public TextField cash;
    public Label balance;
    public int qty1 = 0;
    public int qty2 = 0;
    public int qty3 = 0;
    public int qty4 = 0;
    public int qty5 = 0;
    public int qty6 = 0;
    public int qty7 = 0;
    public int qty8 = 0;
    public int qty9 = 0;
    double totalValue = 0.0;

    private ObservableList<BillTm> items = FXCollections.observableArrayList();

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        billTextArea.setWrapText(true);
    }

    public void addtables(int id, String name, int qty, double price) {
        boolean found = false;      // Check if the item already exists (assuming name is unique identifier)
        items = jTable1.getItems();
        double totPrice = price * qty;
        for (BillTm item : items) {
            if (item.getItem().equals(name)) {
                found = true;       // Update existing item quantity and price
                item.setQty(qty);
                item.setPrice(totPrice);
                System.out.println(totPrice);
                jTable1.refresh();
                break;              // Exit loop after finding the item
            }
        }
        if (!found) {
            BillTm newItem = new BillTm(id, name, qty, price);// Create a BillTm object and add it if not found
            items.add(newItem);
        }
        cal();
        Bill();
    }

    public void itemBtn5(MouseEvent mouseEvent) {
        ++qty5;
        addtables(5, "Mini Bucket  ", qty5, 8.19);
    }

    public void itemBtn6(MouseEvent mouseEvent) {
        ++qty6;
        addtables(6, "Col Burger", qty6, 3.99);
    }

    public void itemBtn4(MouseEvent mouseEvent) {
        ++qty4;
        addtables(4, "Dinner Plate  ",qty4, 9.29);
    }

    public void itemBtn3(MouseEvent mouseEvent) {
        ++qty3;
        addtables(3, "2pc Combo  ",qty3, 7.99);
    }

    public void itemBtn1(MouseEvent mouseEvent) {
        ++qty1; // Increment quantity
        System.out.println(qty1);
        addtables(1, "ZRW Combo  ", qty1, 2.99);
    }

    public void itemBtn2(MouseEvent mouseEvent) {
        ++qty2;
        addtables(2, "1pc Combo  ", qty2, 2.59);
    }

    public void itemBtn7(MouseEvent mouseEvent) {
        ++qty7;
        addtables(7, "1pc Rice Plate", qty7, 12.99);
    }

    public void itemBtn8(MouseEvent mouseEvent) {
        ++qty8;
        addtables(8, "Lil' Combo  ", qty8, 13.99);
    }

    public void itemBtn9(MouseEvent mouseEvent) {
        ++qty9;
        addtables(9, "Rice Wrap  ", qty9, 4.99);
    }
    public void Bill() {
        try {
            billTextArea.setText("                         Shehan Mario FKD \n");
            billTextArea.setText(billTextArea.getText() + "                         589/ King Road, \n");
            billTextArea.setText(billTextArea.getText() + "                         Colombo, Sri lanka, \n");
            billTextArea.setText(billTextArea.getText() + "                         +9411 123456789, \n");
            billTextArea.setText(billTextArea.getText() + "-----------------------------------------------------------------\n");
            billTextArea.setText(billTextArea.getText() + "  Item \t\t\t\tQty\t\tPrice" +"\n");
            billTextArea.setText(billTextArea.getText() + "-----------------------------------------------------------------\n");
            for (BillTm item : items) {
                String name = item.getItem();
                String qty = String.valueOf(item.getQty());
                String price = String.valueOf(item.getPrice());
                billTextArea.setText(billTextArea.getText() + "  " + name + "\t\t\t\t" + qty + "\t\t" + price + "\n");
            }
            billTextArea.setText(billTextArea.getText() + "-----------------------------------------------------------------\n");
            billTextArea.setText(billTextArea.getText() + "Sub Total : " + total.getText() +"\n");
            billTextArea.setText(billTextArea.getText() + "Cash      : " + cash.getText() +"\n");
            billTextArea.setText(billTextArea.getText() + "Balance   : " + balance.getText() +"\n");
            billTextArea.setText(billTextArea.getText() + "-----------------------------------------------------------------\n");
            billTextArea.setText(billTextArea.getText() + "                     Thanks For Your Business...!"+"\n");
            billTextArea.setText(billTextArea.getText() + "-----------------------------------------------------------------\n");
            billTextArea.setText(billTextArea.getText() +  "\n");
            billTextArea.setText(billTextArea.getText() +  "\n");
            billTextArea.setText(billTextArea.getText() +  "\n");
            billTextArea.setText(billTextArea.getText() + "Software by Shehan Mario" + "\n");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR); // Use JavaFX Alert
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void cal() {
        // Calculate total table values
        totalValue = 0.0; // Reset total before recalculating
        for (BillTm item : items) {
            double itemPrice = item.getPrice(); // Assuming a getter method for price exists
            totalValue += itemPrice; // Add individual item price to total
        }
        System.out.println("Total Price: " + totalValue); // Print total price for verification
        // Update the total label on the UI (assuming you have a setter or binding)
        //total.setText(String.valueOf(totalValue));
        String formattedTotal = String.format("%.2f", totalValue); // Format to two decimal places
        total.setText(formattedTotal);
    }

    public void pay() {
        String totalText = total.getText();// Check if total or pay fields are empty or null
        String payText = cash.getText();
        if (totalText.isEmpty() || payText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);// Use JavaFX Alert
            alert.setHeaderText("Input Error");
            alert.setContentText("Please enter valid values for Total and Pay.");
            alert.showAndWait();
            return;
        }

        try {
            double total = Double.parseDouble(totalText);
            double paid = Double.parseDouble(payText);
            double balance1 = paid - total;
            String formattedBalance = String.format("%.2f", balance1); // Format balance using String.format()
            balance.setText(formattedBalance);// Call Bill method (assuming it's ready with JavaFX components)
            Bill();// Call Bill method (assuming it's ready with JavaFX components)
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);// Handle invalid number format
            alert.setHeaderText("Input Error");
            alert.setContentText("Please enter valid numerical values for Total and Pay.");
            alert.showAndWait();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        BillTm selectedItem = jTable1.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Confirmation (optional)
            boolean confirmed = confirmDelete(selectedItem);
            if (confirmed) {
                items.remove(selectedItem);
                int itemId = selectedItem.getId();
                switch (itemId) {
                    case 1:
                        qty1=0;
                        break;
                    case 2:
                        qty2=0;
                        break;
                    case 3:
                        qty3=0;
                        break;
                    case 4:
                        qty4=0;
                        break;
                    case 5:
                        qty5=0;
                        break;
                    case 6:
                        qty6=0;
                        break;
                    case 7:
                        qty7=0;
                        break;
                    case 8:
                        qty8=0;
                        break;
                    case 9:
                        qty9=0;
                        break;
                    default:
                        System.out.println("Unexpected item ID: " + itemId);
                }
                // Refresh the TableView
                jTable1.refresh();
                // Recalculate total after item deletion (assuming you have a cal() method)
                cal();
                jTable1.refresh();// Update the TableView (optional, depending on your implementation)
            }
        } else {
            // No item selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }
    private boolean confirmDelete(BillTm item) {
        // Implement your confirmation logic here (optional)
        // You can use Alert with confirmation buttons
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm Delete");
        alert.setContentText("Are you sure you want to delete " + item.getItem() + "?");
        ButtonType confirmButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == confirmButton;
    }

    public void payAndPrintOnAction(ActionEvent actionEvent) {
        pay();
        Bill();
//        try {
//            pay();
//            Bill();
//            //billTextArea.print();
//        } catch (PrinterException e) {
//            System.out.println(e);
//        }
    }
}
