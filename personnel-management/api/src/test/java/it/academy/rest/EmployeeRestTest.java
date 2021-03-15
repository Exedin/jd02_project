package it.academy.rest;

import it.academy.RestTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = RestTestConfiguration.class)
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

        System.out.println("==========================================================");
        System.out.println(mvcResult.getResponse().getStatus());
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println("==========================================================");
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void readDepartment() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(get("/departments/1")).andReturn();

        System.out.println("==========================================================");
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println("==========================================================");
        assertEquals(200, mvcResult.getResponse().getStatus());

        final MvcResult mvcResult1 = mockMvc
                .perform(get("/departments/2")).andReturn();
    }
    @Test
    public void readDepartmentWithBadId() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(get("/departments/2")).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }



}