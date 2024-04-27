package org.example.bokningssystem;

import org.example.bokningssystem.controller.KundController;
import org.example.bokningssystem.dtos.DetailedKundDto;
import org.example.bokningssystem.services.KundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

@WebMvcTest(KundController.class)
public class KundControllerTest {

    @MockBean
    private KundService kundService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void handleCustomer() throws Exception {
        when(kundService.getAllKunder()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("handleCustomer.html"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("kunder"))
                .andExpect(MockMvcResultMatchers.model().attribute("pageTitle", "Alla befintliga kunder"))
                .andExpect(MockMvcResultMatchers.model().attribute("emptyListMessage", "Inga kunder hittades"));
    }


    @Test
    void createCustomer() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/customerCreated")
                        .param("namn", "Test Kund")
                        .param("email", "test@example.com")
                        .param("telefon", "123456789")
                        .param("personnummer", "123456-7890"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/customer"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("successMessage"));

        verify(kundService).addKund(any(DetailedKundDto.class));
    }

    @Test
    public void modifyCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/modifyCustomer")
                        .param("id", "1")
                        .param("namn", "Modified Kund")
                        .param("email", "modified@example.com")
                        .param("telefonNr", "987654321")
                        .param("personnummer", "987654-3210"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/customer"));

        verify(kundService, times(1)).modifyKund(any(DetailedKundDto.class));
    }
    @Test
    public void deleteCustomerByIdTest() throws Exception {
        Long customerId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/customer/deleteById/{id}", customerId))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/customer"));

        verify(kundService, times(1)).deleteCustomer(customerId);
    }


}




