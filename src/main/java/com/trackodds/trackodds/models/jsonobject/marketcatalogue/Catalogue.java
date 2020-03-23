package com.trackodds.trackodds.models.jsonobject.marketcatalogue;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalogue {
	
	@JsonProperty("result")
	private List<Market> market;

	public List<Market> getMarket() {
		return market;
	}

	public void setMarket(List<Market> market) {
		if(this.market != null){
			this.market = market;
		} else {
			this.market = new ArrayList<>(market);
		}
	}
	
	

}
