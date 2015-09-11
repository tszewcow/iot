package org.iot.server.rest;

import javafx.application.Application;
import org.iot.server.service.LocationFloorService;
import org.iot.server.to.LocationFloorTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class LocationFloorRestServiceTest {

    private LocationFloorRestService testedObject;

    private MockMvc mvc;

    @Mock
    private LocationFloorService locationFloorServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testedObject = new LocationFloorRestService(locationFloorServiceMock);
        mvc = MockMvcBuilders.standaloneSetup(new LocationFloorRestService(locationFloorServiceMock)).build();
    }


    @Test
    public void getAvailableFloorsShouldGetAvailableFloors() {
        final List<LocationFloorTo> floorsFromMock = new ArrayList<>();
        floorsFromMock.add(new LocationFloorTo("building", 2));
        when(locationFloorServiceMock.getAllAvailableFloors()).thenReturn(floorsFromMock);

        final List<LocationFloorTo> result = testedObject.getAvailableFloors();

        verify(locationFloorServiceMock).getAllAvailableFloors();
        assertEquals(floorsFromMock, result);
    }

    @Test
    public void getAllBuildingsShouldGetBuildingsWithAvailableFloors() throws Exception {
        // given when
        mvc.perform(MockMvcRequestBuilders
                .get("/services/buildings")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // then
        verify(locationFloorServiceMock).getAllBuildings();

    }
}