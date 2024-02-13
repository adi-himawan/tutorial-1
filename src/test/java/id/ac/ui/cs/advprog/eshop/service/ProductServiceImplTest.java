package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
    }

    Product createProduct(String productId, String productName, int productQuantity) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }

    @Test
    void testCreateProduct() {
        Product product = createProduct("A100", "Sikat Gigi", 50);

        when(productRepository.create(product)).thenReturn(product);
        Product savedProduct = productServiceImpl.create(product);

        assertEquals(product.getProductId(), savedProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct("A100", "Sikat Gigi", 50);
        Product product2 = createProduct("B100", "Pasta Gigi", 30);

        productList.add(product1);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> output = productServiceImpl.findAll();

        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), output.get(i));
        }
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        Product product = createProduct("A100", "Sikat Gigi", 50);
        
        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product savedProduct = productServiceImpl.findById(product.getProductId());

        assertEquals(product.getProductName(), savedProduct.getProductName());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProduct() {
        Product product = createProduct("A100", "Sikat Gigi", 50);

        Product updatedProduct = createProduct("A100", "Sikat Gigi", 100);
        when(productRepository.edit(product.getProductId(), updatedProduct)).thenReturn(updatedProduct);

        Product editedProduct = productServiceImpl.edit(product.getProductId(), updatedProduct);

        assertEquals(updatedProduct.getProductName(), editedProduct.getProductName());
        verify(productRepository, times(1)).edit(product.getProductId(), updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product product = createProduct("A100", "Sikat Gigi", 50);
        productServiceImpl.create(product);
        productServiceImpl.delete(product.getProductId());

        assertEquals(null, productRepository.findById(product.getProductId()));
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}