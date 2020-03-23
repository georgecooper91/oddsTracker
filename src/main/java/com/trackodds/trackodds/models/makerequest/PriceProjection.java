package com.trackodds.trackodds.models.makerequest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class PriceProjection {
	
	@JsonProperty("priceData")
	private List<String> priceData;
	
	@JsonProperty("virtualise")
	private String virtualise;

	public List<String> getPriceData() {
		return priceData;
	}

	public void setPriceData(List<String> priceData) {
		if(this.priceData != null) {
			this.priceData = priceData;
		} else {
			this.priceData = new ArrayList<>(priceData);
		}
	}

	public String getVirtualise() {
		return virtualise;
	}

	public void setVirtualise(String virtualise) {
		this.virtualise = virtualise;
	}
}
