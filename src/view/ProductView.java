package view;

import Utils.Helper;
import controller.ProductController;
import controller.RowController;
import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;

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
                break;
            case "un":
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

    }

    // update product by id
    private void update() {
        int id = Integer.parseInt(Helper.getAndValidate("Input ID to update: ", "Input ID cannot be empty", "^\\d+$", "Id must be input as number" ));
        Product product = productController.getProduct(id);
        showAProduct(product);

        System.out.println(product.toString());
    }

    //save data to database (option un)
    private void checkUnsaved() {

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

    private void showAProduct (Product product) {

        Table table = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        //when trigger format table it will add set column width and add header
        Helper.formatTable(table);
        Helper.renderData(table,product);
        System.out.println(table.render());
    }
}
