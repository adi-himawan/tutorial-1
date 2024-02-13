package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;
    Model model;
    Product product;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        product = new Product();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String output = productController.createProductPage(model);
        assertEquals("createProduct", output);
    }

    @Test
    void testCreateProductPost() {
        when(productService.create(product)).thenReturn(product);
        String output = productController.createProductPost(product, model);
        assertEquals("redirect:list", output);
    }

    @Test
    public void testEditProductPage() {
        String productId = "testProductId";
        when(productService.findById(productId)).thenReturn(product);

        productController.editProductPage(productId, model);
        verify(model).addAttribute(eq("product"), eq(product));
    }

    @Test
    public void testEditProductPost() {
        String productId = "testProductId";

        productController.editProductPost(productId, product, model);
        verify(productService).edit(eq(productId), eq(product));
    }

    @Test
    public void testDeleteProductPost() {
        String productId = "testProductId";

        productController.deleteProductPost(productId);
        verify(productService).delete(eq(productId));
    }

    @Test
    void testProductListPage() {
        when(productService.findAll()).thenReturn(null);
        String output = productController.productListPage(model);
        assertEquals("productList", output);
    }
}
