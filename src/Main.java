import query.*;
import view.ProductView;

public class Main {
  public static void main(String[] args) {
    System.out.println(new SelectQueryBuilder(TableName.products).where("id", 10).buildQuery());

    QueryBuilder query = new InsertQueryBuilder(TableName.products)
        .setValue("name", "kon khmer")
        .setValue("stock", 20)
        .setValue("id", 10);

    System.out.println(query.buildQuery());
    System.out.println(query.getParameters());

    System.out.println(new InsertQueryBuilder(TableName.products)
        .setValue("name", "kon khmer")
        .setValue("stock", 20)
        .setValue("id", 10)
        .buildQuery());
    System.out.println(new UpdateQueryBuilder(TableName.products)
        .setValue("name", "kon khmer")
        .setValue("stock", 20)
        .where("id", 10)
        .buildQuery());

    ProductView productView = new ProductView();
    productView.start();
  }
}
