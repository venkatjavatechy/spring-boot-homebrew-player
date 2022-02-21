package com.venkatjavatechy.homebrewplayer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FormulaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test()
    public void getFormulaByNameTest() throws Exception {
        mockMvc.perform(get("/homebrew/formula?name=wget").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("wget"))
                .andExpect(jsonPath("$.desc").isNotEmpty())
                .andExpect(jsonPath("$.versions").isNotEmpty())
                .andExpect(jsonPath("$.dependencies").isNotEmpty())
                .andExpect(jsonPath("$.deprecation_date").isEmpty());
    }

    @Test()
    public void getFormulaByNameWithExceptionTest() throws Exception {
        mockMvc.perform(get("/homebrew/formula?name=wget123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()).andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.dateTime").isNotEmpty());
    }
}