package view;

import Utils.Helper;
import controller.ProductController;
import controller.RowController;
import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class ProductView {
    ProductController productController = new ProductController();
    RowController rowController = new RowController();
    private int limit = rowController.get();
    private boolean isExit = false;

    public void start() {
        do {
            displayMenu();
            handleUserChoice();
        } while (!isExit);
    }

    //display menu
    private void displayMenu() {
        showProduct();
        System.out.println(" ".repeat(26) + "-".repeat(10) + "Menu" + "-".repeat(10));
        System.out.println("\tN. Next Page\t\tP. Previous Page\t\tF. First Page\t\tL. Last Page\t\tG. Goto");
        System.out.println("\nW) Write\t\tR) Read\t\t\tU) Update\t\tD) Delete\t\tS) Search (name)\t\tSe) Set rows");
        System.out.println("sa) Save\t\tUn) Unsaved\t\tBa) Backup\t\tRe) Restore\t\tE) Exit");
    }

    //handle user choice
    private void handleUserChoice() {
        String choice = Helper.getAndValidate("=>Chooise an option(): ", "Choice cannot be empty");
        switch (choice.toLowerCase()) {
            case "w":
                break;
            case "r":
                break;
            case "u":
                update();
                break;
            case "d":
                break;
            case "s":
                break;
            case "se":
                break;
            case "sa":
                save();
                break;
            case "un":
                checkUnsaved();
                break;
            case "ba":
                break;
            case "re":
                break;
            case "e":
                break;
            default:
                handlePagination(choice.toLowerCase());
                break;
        }
    }

    private void handlePagination(String choice) {
        switch (choice) {
            case "n":
                break;
            case "p":
                break;
            case "f":
                break;
            case "l":
                break;
            case "g":
                break;
            default:

        }
    }

    //create product (option w)
    private void createProduct() {

    }

    //get product by id (option r)
    private void getProductById() {

    }

    // get product by name (option s)
    private void getProductByName() {

    }

    // get product by name (option d)
    private void deleteProduct() {

    }

    //set rows (option se)
    private void setRow() {

    }

    //save data to database (option sa)
    private void save() {
        String message = Helper.returnStringColor("'si'", Helper.GREEN) + " for saving insert product and " + Helper.returnStringColor("'su'", Helper.GREEN) + " for saving update products or " + Helper.returnStringColor("'b'", Helper.RED) + " for back to menu \n Enter your option: ";
        String option = Helper.getAndValidate(message, "Choice cannot be empty", "(?i)^(si|su|b)$", Helper.returnStringColor("Please enter a correct option", Helper.RED));

        switch (option.toLowerCase()) {
            case "si":
                if(productController.getSavedProduct().isEmpty()) {
                    System.out.println(Helper.returnStringColor("There is no insert products", Helper.RED));
                    return;
                }

                productController.adding();
                System.out.println(Helper.returnStringColor("Products insert successfully", Helper.GREEN));
                Helper.pressEnterToContinue();
                break;
            case "su":
                if(productController.getUpdatedProduct().isEmpty()) {
                    System.out.println(Helper.returnStringColor("There is no update products", Helper.RED));
                    return;
                }

                productController.updating();
                System.out.println(Helper.returnStringColor("Product update successfully", Helper.GREEN));
                Helper.pressEnterToContinue();
                break;
            case "b":
                break;
        }
    }

    // update product by id
    private void update() {
        Product product = null;
        int id = 0;
        while (product == null) {
            id = Integer.parseInt(Helper.getAndValidate("Input ID to update: ", "Input ID cannot be empty", "^\\d+$", "Id must be input as number", 3));

            if(id == -1) {
                return;
            }

            product = productController.getProduct(id);
        }
        showProduct(product);
         breakWhile:
        while (true) {
            System.out.println(product.toString());
            String option = Helper.getAndValidate("Choose an option from 1 - 5: ", "Choice cannot be empty", "^[1-5]$", "Option must be input as number from 1 -5");


            String name;
            String unitPrice;
            String qty;

            switch (Integer.valueOf(option)) {
                case 1:
                    name = Helper.getAndValidate("Enter name: ", "Name cannot be empty");
                    product.setName(name);
                    break;
                case 2:
                    unitPrice = Helper.getAndValidate("Enter price: ", "price cannot be empty", "^\\d+(\\.\\d{1,2})?$", "Price must be input as number");
                    product.setUnitPrice(Double.parseDouble(unitPrice));
                    break;
                case 3:
                    qty = Helper.getAndValidate("Enter qty: ", "qty cannot be empty", "^[1-9]\\d*$", " Qty must be input as number");
                    product.setQty(Integer.parseInt(qty));
                    break;
                case 4:
                    name = Helper.getAndValidate("Enter name: ", "Name cannot be empty");
                    unitPrice = Helper.getAndValidate("Enter price: ", "price cannot be empty", "^\\d+(\\.\\d{1,2})?$", "Price must be input as number");
                    qty = Helper.getAndValidate("Enter qty: ", "qty cannot be empty", "^[1-9]\\d*$", " Qty must be input as number");

                    product.setName(name);
                    product.setUnitPrice(Double.parseDouble(unitPrice));
                    product.setQty(Integer.parseInt(qty));
                    break;
                case 5:
                    break  breakWhile;
            }
        }
        productController.update(product);

    }

    //save data to database (option un)
    private void checkUnsaved() {
        String message = Helper.returnStringColor("'ui'", Helper.GREEN) + " for saving insert product and " + Helper.returnStringColor("'uu'", Helper.GREEN) + " for saving update product or " + Helper.returnStringColor("'b'", Helper.RED) + " for back to menu \n Enter your option: ";
        String option = Helper.getAndValidate(message, "Choice cannot be empty", "(?i)^(ui|uu|b)$", Helper.returnStringColor("Please enter a correct option", Helper.RED));

        switch (option.toLowerCase()) {
            case "ui":
                if(productController.getSavedProduct().isEmpty()) {
                    System.out.println(Helper.returnStringColor("There is no insert products", Helper.RED));
                    return;
                }
                showProduct(productController.getSavedProduct());
                Helper.pressEnterToContinue();
                break;
            case "uu":
                if(productController.getUpdatedProduct().isEmpty()) {
                    System.out.println(Helper.returnStringColor("There is no update products", Helper.RED));
                    return;
                }
                showProduct(productController.getUpdatedProduct());
                Helper.pressEnterToContinue();
                break;
            case "b":
                break;
        }
    }

    //backup (option ba)
    private void backup() {

    }

    //restore (option re)
    private void restore() {

    }

    //show all products
    private void showProduct() {
        List<Product> allProduct = productController.getAllProduct();

        Table table = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        //when trigger format table it will add set column width and add header
        Helper.formatTable(table);
        Helper.renderData(table, allProduct, limit);
        Helper.addFooter(table, 1, 4, allProduct.size());
        System.out.println(table.render());
    }

    private void showProduct (Product product) {
        Table table = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        //when trigger format table it will add set column width and add header
        Helper.formatTable(table);
        Helper.renderData(table, product);
        System.out.println(table.render());
    }

    private void showProduct (List<Product> products) {
        Table table = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        //when trigger format table it will add set column width and add header
        Helper.formatTable(table);
        Helper.renderData(table, products, products.size());
        System.out.println(table.render());
    }
}
