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
public class Params {
	
	
	  @JsonProperty
	  ("filter") 
	  private Filter filter;
		  
	  @JsonProperty("maxResults") 
	  private String maxResults;
		  
	  @JsonProperty("marketProjection")
	  private List<String> marketProjection;
	  
	
	  @JsonProperty("marketIds")
	  private List<String> marketIds;
	  
	  @JsonProperty("priceProjection") private PriceProjection priceProjection;
	 
	  
	  public Filter getFilter() { return filter; }
	  
	  public void setFilter(Filter filter) { this.filter = filter; }


	public List<String> getMarketIds() {
		return marketIds;
	}

	public void setMarketIds(List<String> marketIds) {
		this.marketIds = marketIds;
	}

	public PriceProjection getPriceProjection() { return priceProjection; }
	  
	  public void setPriceProjection(PriceProjection priceProjection) {
	  this.priceProjection = priceProjection; }
	 
	  
	  public String getMaxResults() { return maxResults; }
	  
	  public void setMaxResults(String maxResults) { this.maxResults = maxResults;
	  }
	  
	  public List<String> getMarketProjection() { return marketProjection; }
	  
	  public void setMarketProjection(List<String> marketProjection) {
		  if(this.marketProjection != null) {
			  this.marketProjection = marketProjection;
	  } else {
		  this.marketProjection = new ArrayList<>(marketProjection); 
	  	}
	 }

}
