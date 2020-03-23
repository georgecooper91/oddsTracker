package com.trackodds.trackodds.models.jsonobject.marketcatalogue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Runners {
	
	@JsonProperty("selectionId")
	private long selectionId;
	
	@JsonProperty("runnerName")
	private String runnerName;

	@JsonProperty("lastPriceTraded")
	private double lastPriceTraded;

	@JsonProperty("ex")
	private Ex ex;

	public double getLastPriceTraded() {
		return lastPriceTraded;
	}

	public void setLastPriceTraded(double lastPriceTraded) {
		this.lastPriceTraded = lastPriceTraded;
	}

	public Ex getEx() {
		return ex;
	}

	public void setEx(Ex ex) {
		this.ex = ex;
	}

	public long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(long selectionId) {
		this.selectionId = selectionId;
	}

	public String getRunnerName() {
		return runnerName;
	}

	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}
	
	
}
