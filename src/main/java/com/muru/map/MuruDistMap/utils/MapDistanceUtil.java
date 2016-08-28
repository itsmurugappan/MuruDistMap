package com.muru.map.MuruDistMap.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service ("mapDistanceUtil")
public class MapDistanceUtil {
	private static final Logger logger = LoggerFactory.getLogger(MapDistanceUtil.class);

	@SuppressWarnings("unchecked")
	public String getOriginsFromRequest(Map<String, Object> locations)
	{
		logger.debug("parsing origin data from request");
		String originString = null;
		try{
			HashMap<String, Object> locs2 = (HashMap<String, Object>) locations.get("locations");
			ArrayList<HashMap<String, String>> origins = (ArrayList<HashMap<String, String>>) locs2.get("origins");
			originString = origins.stream().map((o) -> {return (String)o.get("o");}).collect(Collectors.joining("|"));
		}catch(Exception ex)
		{
			logger.debug("Error parsing origin data from request" + ex);
			throw ex;
		}
		return originString;
	}
	
	@SuppressWarnings("unchecked")
	public String getDestinationsFromRequest(Map<String, Object> locations)
	{
		logger.debug("parsing destination data from request");
		String destString = null;
		try{
			HashMap<String, Object> locs2 = (HashMap<String, Object>) locations.get("locations");
			ArrayList<HashMap<String, String>> dests = (ArrayList<HashMap<String, String>>) locs2.get("dest");
			destString = dests.stream().map((d) -> {return (String)d.get("d");}).collect(Collectors.joining("|"));			
		}catch(Exception ex)
		{
			logger.debug("Error parsing destination data from request" + ex);
			throw ex;			
		}
		return destString;
	}
}
