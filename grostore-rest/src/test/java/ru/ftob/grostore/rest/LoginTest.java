package ru.ftob.grostore.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessDeniedTest() throws Exception {
        mockMvc.perform(get("/me"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void requestProtectedUrlWithUser() throws Exception {
        mockMvc.perform(post("/login").with(request -> {
            request.setContent("{\"email\": \"sergeymitrichev@gmail.com\", \"password\": \"rootroot\"}".getBytes());
            return request;
        }))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
