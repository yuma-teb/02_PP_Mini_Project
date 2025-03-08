package controller;

import model.Product;
import model.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductController {
    private ProductRepository productRepository = new ProductRepository();
    private List<Product> savedProduct = new ArrayList<>();
    private List<Product> updatedProduct = new ArrayList<>();

    //get all products
    public List<Product> getAllProduct() {
        return productRepository.get();
    }

    //get product by id
    public Product getProduct(int id) {
        return productRepository.get(id);
    }

    //get product by name
    public Product getProduct(String id) {
        return  productRepository.get(Integer.parseInt(id));
    }

    //add product to add state
    public void add(Product product) {

    }

    //save product to database
    public void adding() {
        productRepository.saveProducts(savedProduct);
    }

    //add product to update state
    public void update(Product product) {
        Optional<Product> existingProduct = updatedProduct.stream()
                .filter(p -> p.getId() == product.getId())
                .findFirst();

        if (existingProduct.isPresent()) {
            int index = updatedProduct.indexOf(existingProduct.get());
            updatedProduct.set(index, product);
        } else {
            updatedProduct.add(product);
        }
    }

    //update product to database
    public void updating() {
        productRepository.updateProducts(updatedProduct);
    }

    //update product to database
    public List<Product> search(String searchTerm) {
        return productRepository.get(searchTerm);
    }

    //delete product
    public void delete() {

    }

    //backup (option ba)
    public void backup() {

    }

    //restore (option re)
    public void restore() {

    }

    //for option (Un)
    public List<Product> getSavedProduct() {
        return savedProduct;
    }

    //for option (Un)
    public List<Product> getUpdatedProduct() {
        return updatedProduct;
    }
}
