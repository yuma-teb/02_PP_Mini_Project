package view;

import Utils.Helper;
import controller.ProductController;
import controller.RowController;
import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;

public class ProductView {
    ProductController productController = new ProductController();
    RowController rowController = new RowController();
    private int limit = rowController.get();
    private boolean isExit = false;
    int id = productController.getAllProduct().isEmpty()?0:productController.getAllProduct().getLast().getId();

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
createProduct();
                break;
            case "r":
                break;
            case "u":
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
        System.out.println("ID:" + (id + 1));
        id++;
        String name = Helper.getAndValidate("Input product name: ", "Product name cannot be empty");
        double price;
        do {
            price = Double.parseDouble(Helper.getAndValidate("Enter price: ", "Price cannot be empty", "^-?\\d+(\\.\\d+)?$", "Wrong input format! Please input a valid price"));
            if (price < 0) {
                Helper.printErrorMsg("Price cannot be a negative number");
                continue;
            }else if(price==0){
                Helper.printErrorMsg("Price cannot be 0");
                continue;
            }
            break;
        } while (true);
        int qty;
        do {
            qty = Integer.parseInt(Helper.getAndValidate("Enter quantity: ", "Quantity cannot be empty", "^-?\\d+", "Wrong input format! Please input a valid quantity"));
            if (qty < 0) {
                Helper.printErrorMsg("Quantity cannot be a negative number");
                continue;
            }else if(qty==0){
                Helper.printErrorMsg("Quantity cannot be 0");
                continue;
            }
            break;
        } while (true);
        productController.add(new Product(id,name,price, qty, LocalDate.now()));
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


}
