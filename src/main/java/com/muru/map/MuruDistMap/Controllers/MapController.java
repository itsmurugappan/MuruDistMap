package com.muru.map.MuruDistMap.Controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.muru.map.MuruDistMap.Service.GoogleAPIInt;


@RestController
public class MapController {
	
	@Autowired
	GoogleAPIInt googleAPIInt;
	
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@RequestMapping(value = "/getDistance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getDistance(@RequestBody Map<String, Object> locations)
	{
		logger.debug("Getting Distance for" + locations.toString());
		String distanceMaps =  googleAPIInt.postRequesttoGoogle(locations);
		logger.debug("Returning Distance for" + distanceMaps);
		return distanceMaps;
	}

}
