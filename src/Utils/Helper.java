package Utils;

import model.Product;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

public interface Helper {
    String RESET = "\u001b[0m";
    String GREEN = "\u001b[32m";
    String BLUE = "\u001b[34m";
    String RED = "\u001b[31m";
    String YELLOW = "\u001B[33m";
    CellStyle align = new CellStyle(CellStyle.HorizontalAlign.center);

    //table related method
    static void formatTable(Table table) {
        table.setColumnWidth(0, 10, 10);
        table.setColumnWidth(1, 20, 20);
        table.setColumnWidth(2, 15, 15);
        table.setColumnWidth(3, 10, 10);
        table.setColumnWidth(4, 20, 20);
        addHeader(table);
    }

    static void addHeader(Table table) {
        List<String> headers = List.of("ID", "Name", "Unit Price", "Qty", "Import Date");
        headers.forEach(s -> {
            table.addCell(BLUE + s + RESET, align);
        });
    }

    static void renderData(Table table, Product product) {
        table.addCell(GREEN + product.getId() + RESET, align);
        table.addCell(product.getName(), align);
        table.addCell(product.getUnitPrice(), align);
        table.addCell(product.getQty(), align);
        table.addCell(product.getImportDate(), align);
    }

    static void renderData(Table table, List<Product> products, int pageNum, int limit) {
       if(pageNum!=0){
           products.stream().skip((pageNum - 1) * limit).limit(limit).forEach(product -> {
               table.addCell(GREEN + product.getId() + RESET, align);
               table.addCell(product.getName(), align);
               table.addCell(product.getUnitPrice(), align);
               table.addCell(product.getQty(), align);
               table.addCell(product.getImportDate(), align);
           });
       }
    }

    static void renderData(Table table, List<Product> products, int limit) {
        products.stream().limit(limit).forEach(product -> {
            table.addCell(GREEN + product.getId() + RESET, align);
            table.addCell(product.getName(), align);
            table.addCell(product.getUnitPrice(), align);
            table.addCell(product.getQty(), align);
            table.addCell(product.getImportDate(), align);
        });
    }

    static void addFooter(Table table, int currentPage, int totalPage, int totalRecord) {
        table.addCell("Page : " + YELLOW + currentPage + RESET + " of " + RED + totalPage + RESET, align, 2);
        table.addCell("Total Record : " + GREEN + totalRecord + RESET, align, 3);
    }

    //Validate empty space
    static String getAndValidate(String prompt, String emptyMsg) {
        do {
            String input;
            Scanner sc = new Scanner(System.in);
            System.out.print(prompt);
            if (!(input = sc.nextLine()).trim().isEmpty()) {
                return input;
            }
            printErrorMsg(emptyMsg);
        } while (true);

    }

    //Validate regex
    static String getAndValidate(String prompt, String emptyMsg, String regex, String regexMsg) {
        do {
            String input = getAndValidate(prompt, emptyMsg);
            if (input.matches(regex)) {
                return input;
            }
            printErrorMsg(regexMsg);
        } while (true);
    }


    //print message
    static void printSuccessMsg(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    //error message
    static void printErrorMsg(String msg) {
        System.out.println(RED + msg + RESET);
    }

    //press key to continue
    static void pressKeyToContinue(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.println(YELLOW + msg + "......" + RESET);
        sc.nextLine();
    }

    // ask to continue
    static boolean userDecision(String message) {
        String input = getAndValidate(message, "Input cannot be empty", "^[yYnN]$", "Invalid input. Please input yY or nN");
        return input.equalsIgnoreCase("y");
    }

    static String returnStringColor(String message, String color) {
        return color + message + RESET;
    }
    // three attempt input
    static String getAndValidate(String prompt, String emptyMsg, String regex, String regexMsg, int maxAttempts) {
        Scanner sc = new Scanner(System.in);

        String input;
        int attempts = 0;

        while (true) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                printErrorMsg(emptyMsg);
            } else if (!input.matches(regex)) {
                printErrorMsg(regexMsg);
            } else {
                return input;
            }

            attempts++;

            if (attempts == maxAttempts) {
                boolean isContinue = userDecision("Do you want to continues? (y/n): ");
                    if (!isContinue) {
                        input = "-1";
                        break;
                }
            }
        }

        return input;
    }

    static void pressEnterToContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.println(YELLOW + "Press Enter to continue..." + RESET);
        sc.nextLine();
    }
}
