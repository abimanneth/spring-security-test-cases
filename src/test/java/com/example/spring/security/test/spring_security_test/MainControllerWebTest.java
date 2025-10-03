package com.example.spring.security.test.spring_security_test;

import com.example.spring.security.test.spring_security_test.config.SecurityConfig;
import com.example.spring.security.test.spring_security_test.controller.MainController;
import com.example.spring.security.test.spring_security_test.service.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MainController.class)
@Import({SecurityConfig.class})
public  class MainControllerWebTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    AdminService adminService;

    @Test
    @DisplayName("Any one can access this public page.")
    void publicAccessible_returnsOk() throws Exception {
        mockMvc
                .perform(get("/api/v1/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Public"));
    }

    @Test
    @DisplayName("Access the admin page without proper authentication")
    public void adminAccessible_returnsOk() throws Exception {
        mockMvc
                .perform(get("/api/v1/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Admin"));
    }

    @Test
    @DisplayName("Access the admin page without proper authenticatin redirected to login page.")
    void adminAccess_returns3xx() throws Exception {
        mockMvc
                .perform(get("/api/v1/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
// .andExpect(status().is3xxRedirection())
// .andExpect(redirectedUrl("/user/new-page"));