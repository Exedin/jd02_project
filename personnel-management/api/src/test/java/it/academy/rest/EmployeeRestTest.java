package it.academy.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.academy.RestConfigurationTest;
import it.academy.model.Employee;
import jdk.jfr.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ContextConfiguration(classes = RestConfigurationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class EmployeeRestTest {


    public static final Logger log = Logger.getLogger(EmployeeRestTest.class.getName());

    @Autowired
    WebApplicationContext context;


    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void readAllEmployeeWithoutDepartment() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(get("/employees")).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void readEmployee() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(get("/employees/1")).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        final MvcResult mvcResult1 = mockMvc
                .perform(get("/employees/2")).andReturn();
    }
    @Test
    public void readEmployeeWithBadId() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(get("/employees/2")).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void deleteEmployee() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(delete("/employees/1")).andReturn();
        assertEquals(204, mvcResult.getResponse().getStatus());
    }
    @Test
    public void deleteEmployeeWithBadId() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(delete("/employees/2")).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }
    @Test
    public void createEmployee() throws Exception {
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(new Employee());

        final MvcResult mvcResult = mockMvc
                .perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
    }
    @Test
    public void createEmployeeWithBadArgs() throws Exception {


        final MvcResult mvcResult = mockMvc
                .perform(post("/employees")
                )
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }


}