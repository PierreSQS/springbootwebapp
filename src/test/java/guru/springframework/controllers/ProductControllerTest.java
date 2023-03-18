package guru.springframework.controllers;

import guru.springframework.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productServMock;

    @BeforeEach
    void setUp() {
    }

    @Test
    void setProductService() {
    }

    @Test
    void list() {
    }

    @Test
    void showProduct() {
    }

    @Test
    void edit() {
    }

    @Test
    void newProduct() {
    }

    @Test
    void saveProduct() {
    }

    @Test
    void delete() {
    }

    @Test
    void login() {
    }
}