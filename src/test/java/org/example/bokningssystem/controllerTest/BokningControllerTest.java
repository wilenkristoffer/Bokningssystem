package org.example.bokningssystem.controllerTest;

import org.example.bokningssystem.controller.BokningController;
import org.example.bokningssystem.dtos.DetailedBokningDto;
import org.example.bokningssystem.dtos.KundDto;
import org.example.bokningssystem.dtos.RumDto;
import org.example.bokningssystem.repo.KundRepo;
import org.example.bokningssystem.repo.RumRepo;
import org.example.bokningssystem.services.BokningService;
import org.example.bokningssystem.services.KundService;
import org.example.bokningssystem.services.RumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class BokningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BokningService bokningService;

    @MockBean
    private KundService kundService;

    @MockBean
    private RumService rumService;
    @Autowired
    private RumRepo rumRepo;

    @Autowired
    private KundRepo kundRepo;

    @Test
    void testHandleBooking() {

        List<DetailedBokningDto> mockBokningar = new ArrayList<>();
        List<KundDto> mockKunder = new ArrayList<>();
        List<RumDto> mockRummen = new ArrayList<>();

        when(bokningService.getAllBookings()).thenReturn(mockBokningar);
        when(kundService.getAllKundSimple()).thenReturn(mockKunder);
        when(rumService.getAllRumSimple()).thenReturn(mockRummen);

        Model model = mock(Model.class);


        String viewName = new BokningController(bokningService, kundService, rumService, rumRepo, kundRepo).handleBooking(model);


        assertEquals("handleBooking.html", viewName);


        verify(model).addAttribute("bokningar", mockBokningar);
        verify(model).addAttribute("kunder", mockKunder);
        verify(model).addAttribute("rummen", mockRummen);
        verify(model).addAttribute("pageTitle", "Alla befintliga bokningar");
        verify(model).addAttribute("tableHeadings", Arrays.asList("Kundens namn", "Rumsnamn", "Startdatum", "Slutdatum"));
        verify(model).addAttribute("emptyListMessage", "Inga bokningar hittades");
        verify(model).addAttribute("customerPageTitle", "Alla kunder");
        verify(model).addAttribute("customerEmptyListMessage", "Inga kunder hittades");
        verify(model).addAttribute("roomPageTitle", "Alla rum");
        verify(model).addAttribute("roomEmptyListMessage", "Inga rum hittades");
    }
}
