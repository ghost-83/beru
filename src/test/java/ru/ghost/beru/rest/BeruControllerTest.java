package ru.ghost.beru.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BeruControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAntGridUnauthorized() throws Exception {
        mockMvc.perform(get("/api/get-matrix"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user")
    void getAntGridOk() throws Exception {
        mockMvc.perform(get("/api/get-matrix"))
                .andExpect(status().isOk());
    }
}