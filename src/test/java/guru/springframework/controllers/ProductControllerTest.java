package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @WithMockUser(username = "MockUser")
    void list() throws Exception {
        // Given
        given(productServMock.listAllProducts()).willReturn(List.of(new Product(),new Product()));

        // When, Then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(content().string(containsString("<span>Welcome <span>MockUser</span></span>")))
                .andExpect(view().name("products"))
                .andDo(print());
    }

    @Test
    void showProduct() {
    }

    @Test
    void edit() {
    }

    @Test
    @WithMockUser(username = "MockUser")
    void newProduct() throws Exception {
        // When, Then
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(content().string(containsString("<span>Welcome <span>MockUser</span></span>")))
                .andExpect(content().string(containsString("Product Create/Update")))
                .andExpect(view().name("productform"))
                .andDo(print());
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