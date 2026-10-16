package com.jonak.java17sb3.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jonak.java17sb3.service.WelcomeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private WelcomeService welcomeService;

  @Test
  public void welcome() throws Exception {
    when(welcomeService.welcome(anyString())).thenReturn("result");

    this.mockMvc
        .perform(get("/welcome").param("name", anyString()).accept(MediaType.ALL))
        .andExpect(status().isOk())
        .andExpect(content().string("result"));
  }

  @Test
  void testWelcomeNoObserve() throws Exception {
    // Setup
    when(welcomeService.welcomeWithNoObserver("name")).thenReturn("result");

    // Run the test and verify the results
    mockMvc
        .perform(get("/welcome/noObserve").param("name", "name").accept(MediaType.ALL_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().string("result"));
  }
}
