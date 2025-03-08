package controller;

import model.Product;
import model.ProductRepository;
import view.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
  private ProductRepository productRepository = new ProductRepository();
  private List<Product> savedProduct = new ArrayList<>();
  private List<Product> updatedProduct = new ArrayList<>();

  // get all products
  public List<Product> getAllProduct() {
    return productRepository.get();
  }

  // get product by id
  public Product getProduct(int id) {
    return productRepository.get(id);
  }

  // get product by name
  public Product getProduct(String id) {
    return productRepository.get(id);
  }

  // add product to add state
  public void add(Product product) {

  }

  // save product to database
  public void adding() {

  }

  // add product to update state
  public void update(Product product) {

  }

  // update product to database
  public void updating() {

  }

  // delete product
  public void delete() {

  }

  // backup (option ba)
  public void backup() {

  }

  // restore (option re)
  public void restore() {

  }

  // for option (Un)
  public List<Product> getSavedProduct() {
    return savedProduct;
  }

  // for option (Un)
  public List<Product> getUpdatedProduct() {
    return updatedProduct;
  }
}
