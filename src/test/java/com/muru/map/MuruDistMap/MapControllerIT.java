package com.muru.map.MuruDistMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.muru.map.MuruDistMap.Controllers.MapController;
import com.muru.map.MuruDistMap.Service.GoogleAPIInt;
import com.muru.map.MuruDistMap.utils.MapDistanceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={MapController.class,GoogleAPIInt.class,MapDistanceUtil.class,RestTemplate.class})
@TestExecutionListeners(inheritListeners = false, listeners = 
						{DependencyInjectionTestExecutionListener.class,
						DirtiesContextTestExecutionListener.class})
public class MapControllerIT {
	
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Test
    public void getDistanceSuccess() throws Exception {
    	    	
    	String reqString = "{\"locations\":{\"origins\":[{\"o\":\"brea ca\"}], \"dest\":[{\"d\":\"fullerton ca\"}]}}";

        ResultActions s = mockMvc.perform(post("/getDistance/")
        		.content(reqString.getBytes()).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.rows[0].elements[0].status", is("OK")))
                .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void getDistanceError() throws Exception {
    	    	
    	String reqString = "{\"locations\":{\"origins\":[{\"o\":\"india\"}], \"dest\":[{\"d\":\"fullerton ca\"}]}}";

        ResultActions s = mockMvc.perform(post("/getDistance/")
        		.content(reqString.getBytes()).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.rows[0].elements[0].status", is("ZERO_RESULTS")))
                .andDo(MockMvcResultHandlers.print());
    }


}
