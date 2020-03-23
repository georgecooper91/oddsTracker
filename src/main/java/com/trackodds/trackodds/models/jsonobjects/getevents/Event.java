package com.trackodds.trackodds.models.jsonobjects.getevents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Event {
	
	@JsonProperty("marketCount")
	private int marketCount;
	
	@JsonProperty("event")
	private EventInfo eventInfo;

	public int getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}
	
	

}
