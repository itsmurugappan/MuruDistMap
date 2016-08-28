package com.muru.map.MuruDistMap.Service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.muru.map.MuruDistMap.Controllers.MapController;
import com.muru.map.MuruDistMap.utils.MapDistanceUtil;

@Service ("googleAPIInt")
public class GoogleAPIInt {
	
	private static final String DISTANCE_API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
			  + "{origins}&destinations={destinations}"
			  + "&key={key}";
	
	@Autowired
	MapDistanceUtil util;
	
	private static final String apiKey = System.getProperty("apiKey");
	
	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(GoogleAPIInt.class);

	public String postRequesttoGoogle( Map<String, Object> locations)
	{
		logger.debug("Connecting to Google");
		String json = null;
		try
		{
			json = restTemplate.getForObject(DISTANCE_API_URL,
					  String.class, util.getOriginsFromRequest(locations), 
					  util.getDestinationsFromRequest(locations), apiKey);
		}catch(Exception ex)
		{
			logger.error("Error Connecting to google" + ex);
			//notify support team
			throw ex;
		}
		return json;
	}
	
}
