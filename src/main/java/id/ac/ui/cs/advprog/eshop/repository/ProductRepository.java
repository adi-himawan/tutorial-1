package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private long productCount = 0;

    public Product create(Product product) {
        product.setProductId(String.valueOf(++productCount));
        productData.add(product);
        return product;
    }

    public Product edit(String productId, Product newProduct){
        newProduct.setProductId(productId);
        Product oldProduct = findById(productId);
        int index = productData.indexOf(oldProduct);
        productData.set(index, newProduct);
        return productData.get(index);
    }

    public void delete(String productId) {
        for (Product product: productData) {
            if (productId.equals(product.getProductId())) {
                productData.remove(product);
                break;
            }
        }
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product: productData) {
            if (productId.equals(product.getProductId())) {
                return product;
            }
        }
        return null;
    }    
}
