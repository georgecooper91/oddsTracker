package com.trackodds.trackodds.models.makerequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Filter {
	
	/*
	 * @JsonProperty("eventTypeIds") private List<String> eventTypeId;
	 * 
	 * @JsonProperty("marketCountries") private List<String> marketCountries;
	 */
	
//	@JsonProperty("locale")
//	private String locale;
	
	Map<String, Object> filters = new HashMap<>();
	
	@JsonAnyGetter
	public Map<String, Object> getFilters(){
		return filters;
	}

	/*
	 * public List<String> getEventTypeId() { return eventTypeId; }
	 * 
	 * public void setEventTypeId(List<String> eventTypeId) { if(eventTypeId !=
	 * null) { this.eventTypeId = eventTypeId; } else { this.eventTypeId = new
	 * ArrayList<>(eventTypeId); } }
	 * 
	 * public List<String> getMarketCountries() { return marketCountries; }
	 * 
	 * public void setMarketCountries(List<String> marketCountries) {
	 * if(this.marketCountries != null) { this.marketCountries = marketCountries; }
	 * else { this.marketCountries = marketCountries; }
	 * 
	 * }
	 */

//	public String getLocale() {
//		return locale;
//	}
//
//
//	public void setLocale(String locale) { this.locale = locale; }

	
	

}
