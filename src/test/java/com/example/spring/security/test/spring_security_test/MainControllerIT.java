package com.example.spring.security.test.spring_security_test;

import com.example.spring.security.test.spring_security_test.service.AdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc   // <-- this wires up MockMvc with full Spring context
public class MainControllerIT {
    @Autowired
    private AdminService adminService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Any one can access the public pages")
    public void publicPages() throws Exception {
        mockMvc
                .perform(get("/api/v1/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Public"));
    }
}
