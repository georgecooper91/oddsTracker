package com.trackodds.trackodds.models.jsonojects.getcompetitions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Competitions {
	
	@JsonProperty("marketCount")
	private int marketCount;
	
	@JsonProperty("competition")
	private Competition competition;
	
	public Competitions() {
		
	}
	
	
	public int getMarketCount() {
		return marketCount;
	}
	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}
	public Competition getCompetition() {
		return competition;
	}
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

}
