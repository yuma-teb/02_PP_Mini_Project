import Utils.QueryBuilder;
import view.ProductView;

public class Main {
    public static void main(String[] args) {
        System.out.println(new QueryBuilder().select().from("hiii").where("a = 10").build());
        ProductView productView = new ProductView();
        productView.start();
    }
}