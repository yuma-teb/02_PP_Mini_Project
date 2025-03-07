package view;

import Utils.Helper;
import controller.ProductController;
import controller.RowController;
import model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class ProductView {
    ProductController productController = new ProductController();
    RowController rowController = new RowController();
    private int limit = rowController.get();
    private boolean isExit = false;
    private int pageNum = 1;
    private int totalPage =(int) Math.ceil(productController.getAllProduct().size() / limit);

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
                System.out.println("Thank you😁🙏🏻");
                isExit=true;
                break;
            default:
                handlePagination(choice.toLowerCase());
                break;
        }
    }

    private void handlePagination(String choice) {
        switch (choice) {
            case "n":
                pageNum++;
                if(pageNum>totalPage){
                    pageNum=totalPage;
                    Helper.printErrorMsg("You are already on last page");
                    Helper.pressKeyToContinue("Press any key to continue");
                }
                break;
            case "p":
                pageNum--;
                if(pageNum==0){
                    pageNum=1;
                    Helper.printErrorMsg("You are already on the fist page");
                    Helper.pressKeyToContinue("Press any key to continue");
                }
                break;
            case "f":
                pageNum = 1;
                break;
            case "l":
                pageNum =totalPage;
                break;
            case "g":
                do{
                    int gotoPage = Integer.parseInt(Helper.getAndValidate("Enter page number: ","Page Number cannot be empty","-?\\d+","Wrong input format. Please input a number"));
                    if(gotoPage<0){
                        Helper.printErrorMsg("Page number cannot be negative");
                        continue;
                    }
                    else if(gotoPage>totalPage||gotoPage==0){
                        Helper.printErrorMsg("Total page is "+totalPage+", You can't enter number 0 or more than total pages");
                        continue;
                    }
                    pageNum=gotoPage;
                    break;
                }while (true);
                break;

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
        totalPage = (allProduct.size() + limit - 1) / limit;
        Table table = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        //when trigger format table it will add set column width and add header
        Helper.formatTable(table);
        Helper.renderData(table, allProduct, pageNum, limit);
        Helper.addFooter(table, pageNum, totalPage, allProduct.size());
        System.out.println(table.render());
    }


}
