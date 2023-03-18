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

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productServMock;

    Product product1, product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductId(String.valueOf(1));
        product1.setPrice(BigDecimal.valueOf(11L));
        product1.setDescription("Test Product1");

        product2 = new Product();
        product2.setProductId(String.valueOf(2));
        product2.setPrice(BigDecimal.valueOf(22L));
        product2.setDescription("Test Product2");
    }

    @Test
    void setProductService() {
    }

    @Test
    @WithMockUser(username = "MockUser")
    void list() throws Exception {
        // Given
        given(productServMock.listAllProducts()).willReturn(List.of(product1,product2));

        // When, Then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(content().string(containsString("<span>Welcome <span>MockUser</span></span>")))
                .andExpect(content().string(containsString("<td>Test Product1</td>")))
                .andExpect(content().string(containsString("<td>Test Product2</td>")))
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
    @WithMockUser(username = "MockUser")
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/product/delete/{id}",1).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"))
                .andDo(print());

        verify(productServMock).deleteProduct(any());
    }

    /**
     * This is an example which shows that we are using the Default Config
     * the test fails because the request is redirected to the Default login Page
     * thus the test fails
     */
    @Test
    @WithMockUser(username = "MockUser")
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }
}