package com.trackodds.trackodds.models.jsonobject.marketcatalogue;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Market {
	
	@JsonProperty("marketId")
	private String marketId;
	
	@JsonProperty("marketName")
	private String marketName;
	
	@JsonProperty("marketStartTime")
	private String marketStartTime;
	
	@JsonProperty("totalMatched")
	private double totalMatched;
	
	@JsonProperty("runners")
	private List<Runners> runners;

	@JsonProperty("status")
	private String status;

	@JsonProperty("totalAvailable")
	private double totalAvailable;

	private LocalDateTime marketOpenned;

	public double getTotalAvailable() {
		return totalAvailable;
	}

	public void setTotalAvailable(double totalAvailable) {
		this.totalAvailable = totalAvailable;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getMarketStartTime() {
		return marketStartTime;
	}

	public void setMarketStartTime(String marketStartTime) {
		this.marketStartTime = marketStartTime;
	}

	public double getTotalMatched() {
		return totalMatched;
	}

	public void setTotalMatched(double totalMatched) {
		this.totalMatched = totalMatched;
	}

	public List<Runners> getRunners() {
		return runners;
	}

	public void setRunners(List<Runners> runners) {
		if(this.runners != null){
			this.runners = runners;
		} else {
			this.runners = new ArrayList<>(runners);

		}
	}
	
	

}
