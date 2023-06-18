package guru.springframework.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationTest {

    @Autowired
    MockMvc mockMvc;

    // TODO implement Roles properly
    @Disabled("Roles muss be properly implemented first")
    @Test
    void validAdmin() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("admin"))
                .andExpect(authenticated().withRoles("ADMIN"));
    }
}
