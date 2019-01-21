package ru.ftob.grostore.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ftob.grostore.rest.config.RestUrlMappingConstants.HOME_MAPPING;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testContext() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void testHomeController() throws Exception {
        mockMvc.perform(get(HOME_MAPPING))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
