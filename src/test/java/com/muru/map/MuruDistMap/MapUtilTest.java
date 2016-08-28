package com.muru.map.MuruDistMap;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muru.map.MuruDistMap.utils.MapDistanceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MapDistanceUtil.class})
public class MapUtilTest {

    @Autowired
    private MapDistanceUtil util;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

	 @Test
	 public void getOriginsTest() throws Exception {
		 	String reqString = "{\"locations\":{\"origins\":[{\"o\":\"brea ca\"},{\"o\":\"cypress ca\"},{\"o\":\"buena park ca\"}], \"dest\":[{\"d\":\"fullerton ca\"}]}}";			
		 	String expResult = "brea ca|cypress ca|buena park ca";
		 	Map<String,Object> locations = new ObjectMapper().readValue(reqString, 
		 			new TypeReference<Map<String, Object>>(){});
	        String result = util.getOriginsFromRequest(locations);
	        assertEquals(expResult, result);
	    }

	 @Test
	 public void getDestinationTest() throws Exception {
		 	String reqString = "{\"locations\":{\"origins\":[{\"o\":\"brea ca\"}], \"dest\":[{\"d\":\"fullerton ca\"},{\"d\":\"cypress ca\"},{\"d\":\"buena park ca\"}]}}";			
		 	String expResult = "fullerton ca|cypress ca|buena park ca";
		 	Map<String,Object> locations = new ObjectMapper().readValue(reqString, 
		 			new TypeReference<Map<String, Object>>(){});
	        String result = util.getDestinationsFromRequest(locations);
	        assertEquals(expResult, result);
	    }

}
